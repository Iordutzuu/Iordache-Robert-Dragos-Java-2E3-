package model;

public class Resource {
    private String id;
    private String title;
    private String location;
    private String year;
    private String author;

    public Resource() {}

    public Resource(String id, String title, String location, String year, String author) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.year = year;
        this.author = author;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "R- id='" + id + "', title='" + title + "'";
    }
}