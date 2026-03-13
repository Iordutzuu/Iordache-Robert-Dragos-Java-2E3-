import java.time.LocalDate;
/**
 * Main application class used to demonstrate the functionality of the social network.
 * Covers model creation, relationship mapping, and importance computation.
 */
public class MainLab3 {
    /**
     * The main method serving as the entry point for the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("---  Crearea modelului complet ---");
        Person p1 = new Person("P1", "Mircea Rednic", LocalDate.of(1990, 5, 15), "Romanian");
        Programmer prog1 = new Programmer("PR1", "Joan Pablo", LocalDate.of(1995, 8, 20), "Spanish", "Java");
        Designer des1 = new Designer("D1", "Smith John", LocalDate.of(1992, 3, 10), "British", "Picsart");
        Company c1 = new Company("C1", "Tech Solutions SRL", "IT");
        System.out.println("Profiluri create cu succes!\n");

        System.out.println("--- Definirea relațiilor ---");
        p1.addRelationship(prog1, "friend");
        p1.addRelationship(c1, "employee");

        prog1.addRelationship(des1, "colleague");
        prog1.addRelationship(c1, "lead dev");

        des1.addRelationship(prog1, "colleague");
        System.out.println("Relațiile au fost stabilite între entități.\n");

        System.out.println("--- Crearea rețelei și adăugarea profilurilor ---");
        SocialNetwork myNetwork = new SocialNetwork();
        myNetwork.addProfile(p1);
        myNetwork.addProfile(prog1);
        myNetwork.addProfile(des1);
        myNetwork.addProfile(c1);
        System.out.println("Toate cele " + myNetwork.getProfiles().size() + " profiluri au fost adăugate în SocialNetwork.\n");

        System.out.println("---  Calculul individual al importanței ---");
        System.out.println(p1.getName() + " -> conexiuni unice: " + myNetwork.computeImportance(p1));
        System.out.println(prog1.getName() + " -> conexiuni unice: " + myNetwork.computeImportance(prog1));
        System.out.println(des1.getName() + " -> conexiuni unice: " + myNetwork.computeImportance(des1));
        System.out.println(c1.getName() + " -> conexiuni unice: " + myNetwork.computeImportance(c1) + "\n");

        System.out.println("---  Afișarea rețelei ordonate după importanță ---");
        myNetwork.printNetworkByImportance();
    }
}