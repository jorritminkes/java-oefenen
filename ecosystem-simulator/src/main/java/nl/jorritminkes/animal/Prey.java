package nl.jorritminkes.animal;

import nl.jorritminkes.Position;
import nl.jorritminkes.Tile;
import nl.jorritminkes.World;
import nl.jorritminkes.resource.Resource;

import java.util.List;

public class Prey extends Animal {
    public Prey(Position position, World world, int energy, int health, int vision) {
        super(position, world, energy, health, vision);
    }

    @Override
    public void move() {
        List<Tile> visibleTiles = this.getVisibleTiles();
        Tile bestTile = null;
        int maxEnergy = Integer.MIN_VALUE;

        for (Tile tile : visibleTiles) {
            if (tile.hasResource()) {
                int distance = getDistanceToResource(world.getTile(getPosition()), tile);
                int potentialEnergy = calculateNetEnergy(distance, tile.getResource().getEnergyValue());

                if (potentialEnergy > maxEnergy) {
                    maxEnergy = potentialEnergy;
                    bestTile = tile;
                }
            }
        }

        if (bestTile != null) {
            stepForwards(bestTile);
        } else {
            makeRandomMove();
        }

        Tile currentTile = world.getTile(getPosition());
        if (currentTile.hasResource()) {
            Resource food = currentTile.getResource();
            modifyEnergy(food.getEnergyValue());
            currentTile.setResource(null);
        }
    }
}
