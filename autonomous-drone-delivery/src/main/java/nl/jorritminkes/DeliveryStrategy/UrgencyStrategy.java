package nl.jorritminkes.DeliveryStrategy;

import nl.jorritminkes.Drone;
import nl.jorritminkes.Hub;
import nl.jorritminkes.Package;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UrgencyStrategy implements DeliveryStrategy {
    private final Random random = new Random();

    @Override
    public void executeTurn(Drone drone) {
        Hub currentHub = drone.getCurrentHub();

        drone.unloadPackage();

        if (!drone.isCarryingPackage()) {
            Optional<Package> highestPriorityPackage = currentHub.getQueue().stream()
                    .max(Comparator.comparingInt(Package::getPriorityLevel));
            if (highestPriorityPackage.isPresent()) {
                Package pkg = highestPriorityPackage.get();
                drone.loadPackage(pkg);
                drone.getCurrentHub().grabPackage(pkg);
            }
        }

        if (drone.isCarryingPackage()) {
            Package activePackage = drone.getLoadedPackage();
            Hub targetDestination = activePackage.getDestination();
            List<Hub> neighbors = currentHub.getNeighbors();

            if (neighbors.contains(targetDestination)) {
                drone.flyTo(targetDestination);
            } else if (!neighbors.isEmpty()) {
                Hub randomNeighbor = neighbors.get(random.nextInt(neighbors.size()));
                drone.flyTo(randomNeighbor);
            }
        }
    }
}
