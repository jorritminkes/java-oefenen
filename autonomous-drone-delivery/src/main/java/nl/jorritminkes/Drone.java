package nl.jorritminkes;

import nl.jorritminkes.DeliveryStrategy.DeliveryStrategy;

public class Drone {
    private Hub currentHub;
    private int maximumCarryingCapacity;
    private int batteryLevel;
    private DeliveryStrategy strategy;
    private Package pkg;

    public Drone(Hub currentHub, int maximumCarryingCapacity, int batteryLevel, DeliveryStrategy strategy, Package pkg) {
        this.currentHub = currentHub;
        this.maximumCarryingCapacity = maximumCarryingCapacity;
        this.batteryLevel = batteryLevel;
        this.strategy = strategy;
        this.pkg = pkg;
    }

    public Hub getCurrentHub() {
        return currentHub;
    }

    public DeliveryStrategy getStrategy() {
        return strategy;
    }

    public void loadPackage(Package pkg) {
        if (currentHub.getQueue().contains(pkg)) {
            this.pkg = pkg;
        }
    }

    public void unloadPackage() {
        if (this.pkg != null && this.pkg.getDestination() == this.currentHub) {
            this.currentHub.acceptDelivery(this.pkg);
            this.pkg = null;
        }
    }

    public void flyTo(Hub nextHub) {
        this.currentHub = nextHub;
    }

    public boolean isCarryingPackage() {
        return this.pkg != null;
    }

    public Package getLoadedPackage() {
        return this.pkg;
    }


}
