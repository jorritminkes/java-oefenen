package nl.jorritminkes.animal;

import nl.jorritminkes.Position;
import nl.jorritminkes.Tile;
import nl.jorritminkes.World;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Animal {
    private Position position;
    private World world;
    private int energy;
    private int health;
    private int vision;

    public Animal(Position position, World world, int energy, int health, int vision) {
        this.position = position;
        this.world = world;
        this.energy = energy;
        this.health = health;
        this.vision = vision;
    }

    public void update() {
        move();
        modifyEnergy(-1);
    }

    public abstract void move();

    protected void setPosition(Position newPosition) {
        if (position != null) {
            Tile oldTile = world.getTile(position);
            oldTile.removeAnimal(this);
        }
        this.position = newPosition;
        Tile newTile = world.getTile(position);
        newTile.addAnimal(this);
    }

    public Position getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHealth() {
        return health;
    }

    public List<Tile> getVisibleTiles() {
        List<Tile> visibleTiles = new ArrayList<>();

        int minX = Math.max(0, position.getX() - vision);
        int maxX = Math.min(world.getWidth() - 1, position.getX() + vision);
        int minY = Math.max(0, position.getY() - vision);
        int maxY = Math.min(world.getHeight() - 1, position.getY() + vision);

        int visionSquared = vision * vision;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                int dx = x - position.getX();
                int dy = y - position.getY();

                if ((dx * dx) + (dy * dy) <= visionSquared) {
                    visibleTiles.add(world.getTile(new Position(x, y)));
                }
            }
        }

        return visibleTiles;
    }

    protected void modifyEnergy(int amount) {
        energy = Math.max(0, Math.min(100, energy + amount));
    }

    protected void takeDamage(int amount) {
        health = Math.max(0, Math.min(100, health - amount));
    }

    public boolean isDead() {
        if (energy <= 0) {
            return true;
        }
        if (health <= 0) {
            return true;
        }
        return false;
    }
}
