package main;

import model.Resource;
import repository.Catalog;
import exception.ResourceException;
import command.Command;
import command.ListCommand;
import command.SaveCommand;
import command.LoadCommand;
import command.ReportCommand;

public class Main {
    public static void main(String[] args) {
        Catalog originalCatalog = new Catalog();

         Resource res1 = new Resource("dino1", "Poza cu Dinozaur", "C:/Users/rjoac/OneDrive/Escritorio/OIP.webp", "2026", "Eu");
         Resource res2 = new Resource("java25", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf", "2025", "James Gosling & others");

        originalCatalog.addResource(res1);
        originalCatalog.addResource(res2);

        try {
            System.out.println("--- Executare SaveCommand ---");
            Command saveCmd = new SaveCommand(originalCatalog, "catalog.json");
            saveCmd.execute();
            System.out.println("Catalog salvat cu succes in catalog.json");

            System.out.println("\n--- Executare LoadCommand (intr-un catalog nou) ---");
            Catalog loadedCatalog = new Catalog();
            Command loadCmd = new LoadCommand("catalog.json", loadedCatalog);
            loadCmd.execute();

            System.out.println("\n--- Executare ListCommand (pe catalogul incarcat) ---");
            Command listCmd = new ListCommand(loadedCatalog);
            listCmd.execute();

            System.out.println("\n--- Executare ReportCommand (pe catalogul incarcat) ---");
            Command reportCmd = new ReportCommand(loadedCatalog);
            reportCmd.execute();

        } catch (ResourceException e) {
            System.err.println("A prins exceptia: " + e.getMessage());
        }
    }
}