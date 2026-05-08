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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Clientul a pornit. Scrie o comanda (sau 'exit' pentru a inchide):");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Clientul se inchide...");
                break;
            }

            if (command.isEmpty()) continue;

            try (
                    Socket socket = new Socket(serverAddress, port);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                out.println(command);

                String response = in.readLine();
                System.out.println(response);

                if (command.equalsIgnoreCase("stop")) {
                    System.out.println("Serverul a fost oprit din telecomanda.");
                }

            } catch (IOException e) {
                System.err.println("Eroare conexiune: " + e.getMessage());
            }
        }
        scanner.close();
    }
}