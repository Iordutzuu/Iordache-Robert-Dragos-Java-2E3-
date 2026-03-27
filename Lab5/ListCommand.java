package command;

import repository.Catalog;
import exception.ResourceException;

public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws ResourceException {
        System.out.println("--- Catalog de Resurse ---");
        if (catalog.getResources().isEmpty()) {
            System.out.println("Catalogul este gol.");
        } else {
            catalog.getResources().forEach(System.out::println);
        }
    }
}