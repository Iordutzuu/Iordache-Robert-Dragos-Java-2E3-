package model;
public class Resource {
    private String id;
    private String title;
    private String location;
    private String year;
    private String author;

    public Resource(String id, String title, String location, String year, String author) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.year = year;
        this.author = author;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getLocation() {
        return location;
    }
    public String getYear() {
        return year;
    }
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "R- id='" + id + "', title='" + title + "'}";
    }
}