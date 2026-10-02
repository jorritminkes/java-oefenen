package nl.jorritminkes;

import nl.jorritminkes.animal.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.jorritminkes.resource.Resource;
import java.util.Map;
import java.util.function.Supplier;

public class World {
    private Tile[][] grid;
    private int width;
    private int height;
    private List<Animal> animals;
    private Supplier<Resource> resourceGenerator;

    private int plantsEatenThisTick = 0;
    private int preyEatenThisTick = 0;
    private int totalPlantsEaten = 0;
    private int totalPreyEaten = 0;
    private int totalStarved = 0;

    public World(int width, int height, Supplier<Resource> resourceGenerator) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];
        this.animals = new ArrayList<>();
        this.resourceGenerator = resourceGenerator;

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
        plantsEatenThisTick = 0;
        preyEatenThisTick = 0;

        List<Animal> deadAnimals = new ArrayList<>();

        for (Animal animal : animals) {
            animal.update();

            if (animal.isDead()) {
                deadAnimals.add(animal);

                if (animal.getEnergy() <= 0) {
                    totalStarved++;
                }
            }
        }

        for (Animal deadAnimal : deadAnimals) {
            animals.remove(deadAnimal);
            getTile(deadAnimal.getPosition()).removeAnimal(deadAnimal);
        }

        Map<Tile, Resource> newGrowths = new HashMap<>();

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Tile tile = grid[x][y];

                if (tile.hasResource() && Math.random() < 0.02) {
                    spreadPlants(x, y, newGrowths);
                }
            }
        }

        for (Map.Entry<Tile, Resource> entry : newGrowths.entrySet()) {
            entry.getKey().setResource(entry.getValue());
        }

        spawnRandomResource(0.10);
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

    private void spawnRandomResource(double spawnChance) {
        if (Math.random() < spawnChance) {
            int randomX = (int) (Math.random() * getWidth());
            int randomY = (int) (Math.random() * getHeight());

            Tile randomTile = grid[randomX][randomY];
            if (!randomTile.hasResource()) {
                randomTile.setResource(resourceGenerator.get());
            }
        }
    }

    private void spreadPlants(int x, int y, Map<Tile, Resource> newGrowths) {
        List<Tile> emptyNeighbors = new ArrayList<>();

        if (x > 0) {
            Tile left = grid[x - 1][y];
            if (!left.hasResource() && !newGrowths.containsKey(left)) {
                emptyNeighbors.add(left);
            }
        }
        if (x < getWidth() - 1) {
            Tile right = grid[x + 1][y];
            if (!right.hasResource() && !newGrowths.containsKey(right)) {
                emptyNeighbors.add(right);
            }
        }
        if (y > 0) {
            Tile up = grid[x][y - 1];
            if (!up.hasResource() && !newGrowths.containsKey(up)) {
                emptyNeighbors.add(up);
            }
        }
        if (y < getHeight() - 1) {
            Tile down = grid[x][y + 1];
            if (!down.hasResource() && !newGrowths.containsKey(down)) {
                emptyNeighbors.add(down);
            }
        }
        if (!emptyNeighbors.isEmpty()) {
            int randomIndex = (int) (Math.random() * emptyNeighbors.size());
            Tile chosenTile = emptyNeighbors.get(randomIndex);

            Resource parentResource = grid[x][y].getResource();
            Resource babyResource = parentResource.reproduce();

            newGrowths.put(chosenTile, babyResource);
        }
    }

    public void printWorld() {
        int preyCount = 0;
        int predatorCount = 0;
        int plantCount = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = grid[x][y];

                if (tile.hasAnimal()) {
                    if (tile.getOccupier() instanceof nl.jorritminkes.animal.Predator) {
                        System.out.print("X ");
                        predatorCount++;
                    } else if (tile.getOccupier() instanceof nl.jorritminkes.animal.Prey) {
                        System.out.print("O ");
                        preyCount++;
                    }
                } else if (tile.hasResource()) {
                    System.out.print("* ");
                    plantCount++;
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("========================================");
        System.out.println("POPULATION: " + preyCount + " Prey | " + predatorCount + " Predators | " + plantCount + " Plants");
        System.out.println("THIS TICK : " + plantsEatenThisTick + " Plants eaten | " + preyEatenThisTick + " Prey eaten");
        System.out.println("ALL TIME  : " + totalPlantsEaten + " Plants eaten | " + totalPreyEaten + " Prey eaten | " + totalStarved + " Starved");
        System.out.println("========================================\n");
    }

    public void recordPlantEaten() {
        plantsEatenThisTick++;
        totalPlantsEaten++;
    }

    public void recordPreyEaten() {
        preyEatenThisTick++;
        totalPreyEaten++;
    }
}
