package nl.jorritminkes.animal;

import nl.jorritminkes.Position;
import nl.jorritminkes.Tile;
import nl.jorritminkes.World;

import java.util.List;

public class Predator extends Animal {

    public Predator(Position position, World world, int energy, int health, int vision) {
        super(position, world, energy, health, vision);
    }

    @Override
    public void move() {
        List<Tile> visibleTiles = this.getVisibleTiles();
        Tile bestTile = null;
        int minDistance = Integer.MAX_VALUE;

        for (Tile tile : visibleTiles) {
            if (tile.hasAnimal() && tile.getOccupier() instanceof Prey) {
                int distance = getDistanceToResource(world.getTile(getPosition()), tile);

                if (distance < minDistance) {
                    minDistance = distance;
                    bestTile = tile;
                }
            }
        }

        if (bestTile != null && minDistance == 1) {
            Animal prey = bestTile.getOccupier();
            prey.takeDamage(100);
            modifyEnergy(40);
            world.recordPlantEaten();

            stepForwards(bestTile);
        } else if (bestTile != null) {
            stepForwards(bestTile);
        } else {
            makeRandomMove();
        }
    }
}