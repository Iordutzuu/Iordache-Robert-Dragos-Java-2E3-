package command;

import repository.Catalog;
import exception.ResourceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class SaveCommand implements Command {
    private Catalog catalog;
    private String path;

    public SaveCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    @Override
    public void execute() throws ResourceException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), catalog);
        } catch (Exception e) {
            throw new ResourceException("Eroare la salvarea catalogului in fisierul: " + path, e);
        }
    }
}