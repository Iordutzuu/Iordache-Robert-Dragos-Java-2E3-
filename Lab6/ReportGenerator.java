package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGenerator {

    public void generateHTML() {
        String template = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head><title>Movie Report</title></head>\n" +
                "<body style='font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px;'>\n" +
                "    <h1 style='color: #333;'>Database Movies Report</h1>\n" +
                "    <table border='1' cellpadding='10' cellspacing='0' style='border-collapse: collapse; width: 100%%;'>\n" +
                "        <tr style='background-color: #4CAF50; color: white;'>\n" +
                "            <th>Title</th><th>Release Date</th><th>Duration</th><th>Score</th><th>Genre</th>\n" +
                "        </tr>\n" +
                "        %s\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";

        StringBuilder rows = new StringBuilder();

        String query = "SELECT * FROM movie_report_view";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                rows.append("<tr>")
                        .append("<td>").append(rs.getString("title")).append("</td>")
                        .append("<td>").append(rs.getDate("release_date")).append("</td>")
                        .append("<td>").append(rs.getInt("duration")).append(" min</td>")
                        .append("<td>").append(rs.getDouble("score")).append("</td>")
                        .append("<td>").append(rs.getString("genre")).append("</td>")
                        .append("</tr>\n");
            }

            String finalHtml = String.format(template, rows.toString());

            try (FileWriter writer = new FileWriter("report.html")) {
                writer.write(finalHtml);
                System.out.println("SUCCES MAXIM: Raportul HTML a fost generat!");
            } catch (IOException e) {
                System.out.println("Eroare la crearea fisierului: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}