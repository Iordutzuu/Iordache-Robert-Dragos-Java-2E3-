package command;

import model.Resource;
import exception.ResourceException;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class ViewCommand implements Command {
    private Resource resource;

    public ViewCommand(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void execute() throws ResourceException {
        try {
            Desktop desktop = Desktop.getDesktop();
            String location = resource.getLocation();

            if (location.startsWith("http://") || location.startsWith("https://")) {
                desktop.browse(new URI(location));
            } else {
                File file = new File(location);
                if (file.exists()) {
                    desktop.open(file);
                } else {
                    throw new ResourceException("Fișierul local nu a fost găsit.", null);
                }
            }
        } catch (Exception e) {
            throw new ResourceException("Eroare la deschiderea resursei.", e);
        }
    }
}