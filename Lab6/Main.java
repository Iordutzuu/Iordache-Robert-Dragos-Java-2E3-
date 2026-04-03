package org.example;

import org.example.model.Actor;
import org.example.model.Movie;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            GenreDAO genres = new GenreDAO();
            ActorDAO actors = new ActorDAO();
            MovieDAO movies = new MovieDAO();

            try {
                genres.create("Comedie");
            } catch (SQLException e) {
            }

            Integer comedieId = genres.findByName("Comedie");

            actors.create("Leonardo DiCaprio");
            Actor myActor = actors.findById(7);
            System.out.println("Actorul citit din DB: " + myActor);

            Movie titanic = new Movie(
                    null,
                    "Titanic",
                    Date.valueOf("1997-12-19"),
                    194,
                    7.9,
                    comedieId
            );
            movies.create(titanic);

            Movie dbMovie = movies.findByTitle("Titanic");
            System.out.println("Filmul citit din DB: " + dbMovie);

            ReportGenerator report = new ReportGenerator();
            report.generateHTML();

            Database.closePool();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}