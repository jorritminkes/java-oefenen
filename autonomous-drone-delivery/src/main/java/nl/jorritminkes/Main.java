package nl.jorritminkes;

import nl.jorritminkes.DeliveryStrategy.UrgencyStrategy;

public class Main {
    public static void main(String[] args) {
        Hub hubA = new Hub();
        Hub hubB = new Hub();
        Hub hubC = new Hub();

        hubA.addNeighbor(hubB);
        hubB.addNeighbor(hubA);
        hubB.addNeighbor(hubC);
        hubC.addNeighbor(hubB);

        Package urgentPackage = new Package(hubC, 10);
        hubA.addPackage(urgentPackage);

        UrgencyStrategy strategy = new UrgencyStrategy();
        Drone drone = new Drone(hubA, 1, 100, strategy, null);


        System.out.println("--- Starting Drone Delivery Simulation ---");

        for (int turn = 1; turn <= 4; turn++) {
            System.out.print("Turn " + turn + " | Drone is at " + getHubName(drone.getCurrentHub(), hubA, hubB, hubC));

            drone.getStrategy().executeTurn(drone);

            if (drone.isCarryingPackage()) {
                System.out.println(" -> Picked up/Carrying a package!");
            } else {
                System.out.println(" -> Hands are empty.");
            }
        }
    }

    private static String getHubName(Hub hub, Hub a, Hub b, Hub c) {
        if (hub == a) return "Hub A";
        if (hub == b) return "Hub B";
        if (hub == c) return "Hub C";
        return "Unknown Hub";
    }
}