package org.example.lab7;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Date releaseDate;
    private Integer duration;
    private Double score;
    private Integer genreId;

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public Date getReleaseDate() { return releaseDate; }
    public Integer getDuration() { return duration; }
    public Double getScore() { return score; }
    public Integer getGenreId() { return genreId; }
}