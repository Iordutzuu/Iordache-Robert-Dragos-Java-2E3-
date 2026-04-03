package org.example.model;

import java.sql.Date;

public class Movie {
    private Integer id;
    private String title;
    private Date releaseDate;
    private Integer duration;
    private Double score;
    private Integer genreId;

    public Movie() {}

    public Movie(Integer id, String title, Date releaseDate, Integer duration, Double score, Integer genreId) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.genreId = genreId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public Integer getGenreId() { return genreId; }
    public void setGenreId(Integer genreId) { this.genreId = genreId; }

    @Override
    public String toString() {
        return "Movie{id=" + id + ", title='" + title + "', score=" + score + "}";
    }
}