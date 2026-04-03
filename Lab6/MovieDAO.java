package org.example;

import org.example.model.Movie;
import java.sql.*;

public class MovieDAO {

    public void create(Movie movie) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "INSERT INTO movies (title, release_date, duration, score, genre_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setDate(2, movie.getReleaseDate());
            pstmt.setInt(3, movie.getDuration());
            pstmt.setDouble(4, movie.getScore());
            pstmt.setInt(5, movie.getGenreId());

            pstmt.executeUpdate();
        }
    }

    public Movie findByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM movies WHERE title = ?")) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getDate("release_date"),
                            rs.getInt("duration"),
                            rs.getDouble("score"),
                            rs.getInt("genre_id")
                    );
                }
                return null;
            }
        }
    }
}