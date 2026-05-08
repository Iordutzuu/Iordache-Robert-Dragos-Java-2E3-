package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 8100;
    private boolean running = true;
    private ServerSocket serverSocket;

    public GameServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Serverul a pornit si asculta pe portul " + PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("Eroare la server: " + e.getMessage());
            } else {
                System.out.println("Serverul a fost oprit cu succes.");
            }
        }
    }

    public void stopServer() {
        this.running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Eroare la oprirea serverului: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new GameServer();
    }
}