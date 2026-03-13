import java.time.LocalDate;


public class Designer extends Person {
    private String preferredDesignTool;

    public Designer(String id, String name, LocalDate birthDate, String nationality, String preferredDesignTool) {
        super(id, name, birthDate, nationality);
        this.preferredDesignTool = preferredDesignTool;
    }

    public String getPreferredDesignTool() {
        return preferredDesignTool;
    }

    @Override
    public String toString() {
        return "Designer{id='" + getId() + "', name='" + getName() + "', tool='" + preferredDesignTool + "'}";
    }
}