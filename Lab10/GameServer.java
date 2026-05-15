package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameServer {
    public static final int PORT = 8100;
    private boolean running = true;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private List<Question> questions = new ArrayList<>();

    private final List<ClientThread> clients = new ArrayList<>();
    private boolean gameActive = false;
    private Question currentQuestion = null;
    private long questionStartTime = 0;

    public GameServer() {
        loadQuestions("questions.txt");
        threadPool = Executors.newFixedThreadPool(10);

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Serverul de Quiz a pornit pe portul " + PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket, this);

                synchronized(clients) {
                    clients.add(client);
                }

                threadPool.submit(client);
            }
        } catch (IOException e) {
            if (running) System.err.println("Eroare la server: " + e.getMessage());
        } finally {
            stopServer();
        }
    }

    private void loadQuestions(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) questions.add(new Question(parts[0].trim(), parts[1].trim()));
            }
            System.out.println("S-au incarcat " + questions.size() + " intrebari!");
        } catch (IOException e) {
            System.err.println("Eroare la incarcarea intrebarilor: " + e.getMessage());
        }
    }

    public synchronized void removeClient(ClientThread client) {
        clients.remove(client);
    }

    public synchronized void broadcast(String message) {
        for (ClientThread client : clients) {
            client.sendMessage(message);
        }
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public synchronized void submitAnswer(ClientThread client, String answer) {
        if (!gameActive || currentQuestion == null) return;

        long timeTaken = System.currentTimeMillis() - questionStartTime;
        if (currentQuestion.checkAnswer(answer)) {
            client.addScore(timeTaken);
            client.sendMessage(">> CORECT! (+1 punct, timp: " + timeTaken + " ms)");
        } else {
            client.sendMessage(">> GRESIT!");
        }
    }

    public synchronized void startGame() {
        if (gameActive) return;
        if (questions.isEmpty()) {
            broadcast("Eroare: Nu exista intrebari in fisier!");
            return;
        }

        gameActive = true;

        new Thread(() -> {
            broadcast("\n=== JOCUL INCEPE IN 3 SECUNDE ===");
            try { Thread.sleep(3000); } catch (InterruptedException e) {}

            for (int i = 0; i < questions.size(); i++) {
                currentQuestion = questions.get(i);

                synchronized(clients) {
                    for (ClientThread c : clients) c.setAnswered(false);
                }

                questionStartTime = System.currentTimeMillis();
                broadcast("\nIntrebarea " + (i + 1) + ": " + currentQuestion.getText() + " (Ai 10 secunde!)");

                try { Thread.sleep(10000); } catch (InterruptedException e) {}

                broadcast("--- Timpul a expirat! Trecem mai departe. ---");
            }

            currentQuestion = null;
            gameActive = false;
            announceWinner();

        }).start();
    }

    private void announceWinner() {
        broadcast("\n====== JOCUL S-A TERMINAT ======");

        synchronized(clients) {
            clients.sort((c1, c2) -> {
                if (c1.getScore() != c2.getScore()) {
                    return Integer.compare(c2.getScore(), c1.getScore());
                }
                return Long.compare(c1.getTotalResponseTime(), c2.getTotalResponseTime());
            });

            broadcast("--- CLASAMENT FINAL ---");
            for (int i = 0; i < clients.size(); i++) {
                ClientThread c = clients.get(i);
                broadcast((i + 1) + ". " + c.getPlayerName() + " -> " + c.getScore() + " pct (Timp total reacție: " + c.getTotalResponseTime() + " ms)");
            }
        }
    }

    public void stopServer() {
        this.running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) serverSocket.close();
            if (threadPool != null) {
                threadPool.shutdown();
                if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) threadPool.shutdownNow();
            }
        } catch (IOException | InterruptedException e) {
            if (threadPool != null) threadPool.shutdownNow();
        }
    }

    public static void main(String[] args) {
        new GameServer();
    }
}