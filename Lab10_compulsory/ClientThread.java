package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request = in.readLine();

            if (request != null) {
                System.out.println("Serverul a primit comanda: " + request);

                if (request.equalsIgnoreCase("stop")) {
                    out.println("Server stopped");
                    server.stopServer();
                } else {
                    out.println("Server received the request: " + request);
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare de comunicare cu clientul: " + e.getMessage());
        } finally {
            try {
                if (!socket.isClosed()) socket.close();
            } catch (IOException e) {
                System.err.println("Eroare la inchiderea socket-ului: " + e.getMessage());
            }
        }
    }
}