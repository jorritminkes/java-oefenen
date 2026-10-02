package nl.jorritminkes;

import nl.jorritminkes.resource.Resource;
import nl.jorritminkes.animal.Animal;

public class Tile {
    private Position position;
    private Resource resource;
    private Animal occupier;

    Tile(Position position) {
        this.position = position;
    }

    public void addAnimal(Animal animal) {
        this.occupier = animal;
    }

    public void removeAnimal(Animal animal) {
        if (this.occupier == animal) {
            this.occupier = null;
        }
    }

    public Animal getOccupier() {
        return occupier;
    }

    public boolean hasAnimal() {
        return occupier != null;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public boolean hasResource() {
        return resource != null;
    }

    public Position getPosition() {
        return position;
    }
}