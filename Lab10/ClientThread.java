package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;
    private PrintWriter out;

    private String playerName = "Anonim";
    private int score = 0;
    private long totalResponseTime = 0;
    private boolean answeredCurrentQuestion = false;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void sendMessage(String msg) {
        if (out != null) out.println(msg);
    }

    public int getScore() { return score; }
    public long getTotalResponseTime() { return totalResponseTime; }
    public String getPlayerName() { return playerName; }
    public void setAnswered(boolean answered) { this.answeredCurrentQuestion = answered; }

    public void addScore(long timeTaken) {
        this.score++;
        this.totalResponseTime += timeTaken;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Conectat la Quiz! Seteaza-ti numele cu comanda: nume <NumeleTau>");
            out.println("Scrie 'start' cand toata lumea e gata sa inceapa.");

            String request;
            while ((request = in.readLine()) != null) {
                String cmd = request.trim();

                if (cmd.equalsIgnoreCase("stop")) {
                    server.stopServer();
                    break;
                } else if (cmd.toLowerCase().startsWith("nume ")) {
                    this.playerName = cmd.substring(5).trim();
                    out.println("Numele tau a fost setat la: " + this.playerName);
                } else if (cmd.equalsIgnoreCase("start")) {
                    server.startGame();
                } else {
                    if (server.isGameActive()) {
                        if (!answeredCurrentQuestion) {
                            server.submitAnswer(this, cmd);
                            answeredCurrentQuestion = true;
                        } else {
                            out.println("Ai raspuns deja! Asteapta urmatoarea intrebare.");
                        }
                    } else {
                        out.println("Asteptam sa inceapa jocul... (Poti scrie 'start')");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Clientul " + playerName + " s-a deconectat.");
        } finally {
            server.removeClient(this);
            try {
                if (!socket.isClosed()) socket.close();
            } catch (IOException e) {}
        }
    }
}