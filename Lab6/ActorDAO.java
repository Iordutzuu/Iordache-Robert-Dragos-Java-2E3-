package org.example;

import org.example.model.Actor;
import java.sql.*;

public class ActorDAO {

    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO actors (name) VALUES (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Actor findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM actors WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Actor(rs.getInt("id"), rs.getString("name"));
                }
                return null;
            }
        }
    }
}