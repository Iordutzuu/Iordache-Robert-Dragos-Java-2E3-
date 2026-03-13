import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class Person implements Profile, Comparable<Person> {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String nationality;
    private Map<Profile, String> relationships;

    public Person(String id, String name, LocalDate birthDate, String nationality) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.relationships = new HashMap<>();
    }


    public void addRelationship(Profile node, String relationshipType) {
        this.relationships.put(node, relationshipType);
    }


    public Map<Profile, String> getRelationships() {
        return relationships;
    }

    @Override
    public String getId() {
        return id; }

    @Override
    public String getName() {
        return name; }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public String getNationality() {
        return nationality;
    }


    @Override
    public int compareTo(Person other) {

        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Person{id='" + id + "', name='" + name + "', birthDate=" + birthDate + ", nationality='" + nationality + "'}";
    }
}