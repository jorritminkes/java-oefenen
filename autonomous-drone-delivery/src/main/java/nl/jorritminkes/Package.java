package nl.jorritminkes;

public class Package {
    private Hub destination;
    private int priorityLevel;

    public Package(Hub destination, int priorityLevel) {
        this.destination = destination;
        this.priorityLevel = priorityLevel;
    }

    public Hub getDestination() {
        return destination;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}
