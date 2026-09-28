package nl.jorritminkes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hub {
    private ArrayList<Package> Queue = new ArrayList<>();
    private ArrayList<Hub> neighbors = new ArrayList<>();
    private ArrayList<Package> deliveredPackages = new ArrayList<>();

    public void addNeighbor(Hub neighbor) {
        neighbors.add(neighbor);
    }

    public List<Hub> getNeighbors() {
        return Collections.unmodifiableList(neighbors);
    }

    public List<Package> getQueue() {
        return Collections.unmodifiableList(Queue);
    }

    public void grabPackage(Package pkg) {
        Queue.remove(pkg);
    }

    public void addPackage(Package pkg) {
        Queue.add(pkg);
    }

    public void acceptDelivery(Package pkg) {
        deliveredPackages.add(pkg);
    }


}
