package main;
import model.Resource;
import repository.Catalog;
import exception.ResourceException;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        Resource res1 = new Resource("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", "1967", "Donald E. Knuth");
        Resource res2 = new Resource("java25", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf", "2025", "James Gosling & others");

        catalog.addResource(res1);
        catalog.addResource(res2);

        System.out.println("Resurse in catalog: " + catalog.getResources());

        try {
            System.out.println("Incercam sa deschidem resursa web...");
            catalog.openResource(res2);

        } catch (ResourceException e) {
            System.err.println("A prins exceptia: " + e.getMessage());
        }
    }
}