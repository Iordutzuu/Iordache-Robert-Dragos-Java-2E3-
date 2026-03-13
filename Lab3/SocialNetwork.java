import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SocialNetwork {
    private List<Profile> profiles;


    public SocialNetwork() {
        this.profiles = new ArrayList<>();
    }


    public void addProfile(Profile profile) {
        this.profiles.add(profile);
    }


    public List<Profile> getProfiles() {
        return profiles;
    }


    public int computeImportance(Profile target) {
        Set<Profile> uniqueConnections = new HashSet<>();

        if (target instanceof Person) {
            Person targetPerson = (Person) target;
            uniqueConnections.addAll(targetPerson.getRelationships().keySet());
        }

        for (Profile p : profiles) {
            if (p instanceof Person) {
                Person personInNetwork = (Person) p;
                if (personInNetwork.getRelationships().containsKey(target)) {
                    uniqueConnections.add(personInNetwork);
                }
            }
        }

        return uniqueConnections.size();
    }


    public void printNetworkByImportance() {
        profiles.sort((p1, p2) -> Integer.compare(this.computeImportance(p2), this.computeImportance(p1)));

        System.out.println("== Rețeaua Socială ==");
        for (Profile p : profiles) {
            System.out.println(p.getName() + " [" + p.getClass().getSimpleName() + "] - Importanță: " + computeImportance(p));
        }
    }
}