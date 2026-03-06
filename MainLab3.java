import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Profile> network = new ArrayList<>();
        network.add(new Person("P1", "Popescu Ion"));
        network.add(new Company("C1", "Tech Solutions SRL"));
        network.add(new Person("P2", "Ionescu Maria"));
        network.add(new Company("C2", "Alpha Corp"));

        System.out.println("Înainte de sortare:");
        network.forEach(System.out::println);

        network.sort(Comparator.comparing(Profile::getName));

        System.out.println("\nDupă sortare:");
        network.forEach(System.out::println);
    }
}