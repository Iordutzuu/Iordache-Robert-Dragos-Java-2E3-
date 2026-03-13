import java.time.LocalDate;


public class Programmer extends Person {
    private String favoriteLanguage;
    public Programmer(String id, String name, LocalDate birthDate, String nationality, String favoriteLanguage) {
        super(id, name, birthDate, nationality);
        this.favoriteLanguage = favoriteLanguage;
    }
    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    @Override
    public String toString() {
        return "Programmer{id='" + getId() + "', name='" + getName() + "', language='" + favoriteLanguage + "'}";
    }
}