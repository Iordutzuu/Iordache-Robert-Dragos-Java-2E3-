package command;

import repository.Catalog;
import exception.ResourceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class LoadCommand implements Command {
    private String path;
    private Catalog catalog;

    public LoadCommand(String path, Catalog catalog) {
        this.path = path;
        this.catalog = catalog;
    }

    @Override
    public void execute() throws ResourceException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Catalog loadedCatalog = mapper.readValue(new File(path), Catalog.class);
            this.catalog.setResources(loadedCatalog.getResources());
        } catch (Exception e) {
            throw new ResourceException("Eroare la incarcarea catalogului din fisierul: " + path, e);
        }
    }
}