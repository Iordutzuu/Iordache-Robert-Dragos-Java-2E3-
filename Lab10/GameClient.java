package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 8100;

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectat la server! (Scrie 'exit' pentru a te deconecta)");

            Thread listenerThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println("\n[Server]: " + response);
                        System.out.print("> ");
                    }
                } catch (IOException e) {
                    System.out.println("\n[Sistem]: Conexiunea cu serverul a fost inchisa.");
                }
            });
            listenerThread.start();

            while (true) {
                System.out.print("> ");
                String command = scanner.nextLine().trim();

                if (command.isEmpty()) continue;

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Se inchide clientul...");
                    break;
                }

                out.println(command);
            }

        } catch (IOException e) {
            System.err.println("Eroare de conexiune la server (L-ai pornit?): " + e.getMessage());
        }
    }
}