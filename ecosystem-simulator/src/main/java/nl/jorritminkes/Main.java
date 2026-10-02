package nl.jorritminkes;

import nl.jorritminkes.animal.Predator;
import nl.jorritminkes.animal.Prey;
import nl.jorritminkes.resource.Plant;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int worldWidth = 20;
        int worldHeight = 20;
        Random random = new Random();

        World world = new World(worldWidth, worldHeight, () -> new Plant());

        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(worldWidth);
            int y = random.nextInt(worldHeight);
            Position pos = new Position(x, y);

            Prey prey = new Prey(pos, world, 50, 100, 5);

            world.addAnimal(prey);
            world.getTile(pos).addAnimal(prey);
        }

        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(worldWidth);
            int y = random.nextInt(worldHeight);
            Position pos = new Position(x, y);

            Predator predator = new Predator(pos, world, 80, 100, 8);

            world.addAnimal(predator);
            world.getTile(pos).addAnimal(predator);
        }

        System.out.println("Starting Simulation...");
        int totalTicks = 1000;

        for (int tick = 1; tick <= totalTicks; tick++) {
            world.update();

            System.out.println("Tick " + tick + " completed.");
            world.printWorld();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulation Finished.");
    }
}