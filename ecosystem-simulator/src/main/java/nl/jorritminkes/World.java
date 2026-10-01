package nl.jorritminkes;

import nl.jorritminkes.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class World {
    private Tile[][] grid;
    private int width;
    private int height;
    private List<Animal> animals;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];
        this.animals = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Tile(new Position(x, y));
            }
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void update() {
        List<Animal> deadAnimals = new ArrayList<>();

        for (Animal animal : animals) {
            animal.update();

            if (animal.isDead()) {
                deadAnimals.add(animal);
            }
        }

        for (Animal deadAnimal : deadAnimals) {
            animals.remove(deadAnimal);
            getTile(deadAnimal.getPosition()).removeAnimal(deadAnimal);
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Tile getTile(Position position) {
        return grid[position.getX()][position.getY()];
    }
}
