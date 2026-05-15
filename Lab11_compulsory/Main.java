package org.example;

import org.example.model.Player;
import org.example.repository.PlayerRepository;

public class Main {
    public static void main(String[] args) {
        PlayerRepository repo = new PlayerRepository();

        Player player1 = new Player("Alexandru");
        Player player2 = new Player("Bogdan");

        repo.save(player1);
        repo.save(player2);

        System.out.println("Jucatori salvati cu succes in baza de date H2!");

        Player foundPlayer = repo.findById(1L);
        System.out.println("Am extras din DB jucatorul: " + foundPlayer.getName());

        repo.close();
    }
}