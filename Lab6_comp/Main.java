package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            GenreDAO genres = new GenreDAO();

            try {
                genres.create("Action");
                genres.create("Drama");
                System.out.println("SUCCES: Genurile au fost inserate in baza de date");
            } catch (SQLException e) {
                System.out.println("NOTA: Genurile exista deja in baza de date");
            }

            Integer actionId = genres.findByName("Action");
            System.out.println("ID-ul gasit pentru genul 'Action' este: " + actionId);

            if (actionId != null) {
                String genreName = genres.findById(actionId);
                System.out.println("Genul gasit cu ID-ul " + actionId + " este: " + genreName);
            }

            Database.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}