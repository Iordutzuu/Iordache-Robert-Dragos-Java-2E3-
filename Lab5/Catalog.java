package repository;

import model.Resource;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}