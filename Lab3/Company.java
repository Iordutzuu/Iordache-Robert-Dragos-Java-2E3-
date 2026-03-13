
public class Company implements Profile, Comparable<Company> {
    private String id;
    private String name;
    private String industry;


    public Company(String id, String name, String industry) {
        this.id = id;
        this.name = name;
        this.industry = industry;
    }

    @Override
    public String getId() {
        return id; }

    @Override
    public String getName() {
        return name;
    }


    public String getIndustry() {
        return industry; }


    @Override
    public int compareTo(Company other) {

        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Company{id='" + id + "', name='" + name + "', industry='" + industry + "'}";
    }
}