package repository;
import model.Resource;
import exception.ResourceException;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void openResource(Resource resource) throws ResourceException {
        if (!Desktop.isDesktopSupported()) {
            throw new ResourceException("Clasa Desktop nu este suportata pe sistemul tau.", null);
        }

        Desktop desktop = Desktop.getDesktop();
        String loc = resource.getLocation();

        try {
            if (loc.startsWith("http://") || loc.startsWith("https://")) {
                desktop.browse(new URI(loc));
            } else {
                File file = new File(loc);
                if (!file.exists()) {
                    throw new ResourceException("Fisierul local nu a fost gasit: " + loc, null);
                }
                desktop.open(file);
            }
        } catch (Exception e) {
            throw new ResourceException("Eroare la deschiderea resursei: " + resource.getTitle(), e);
        }
    }

    public List<Resource> getResources() {
        return resources;
    }
}