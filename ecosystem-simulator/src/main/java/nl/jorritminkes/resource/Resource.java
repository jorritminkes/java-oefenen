package nl.jorritminkes.resource;

public abstract class Resource {
    private int energyValue;

    public Resource(int energyValue) {
        this.energyValue = energyValue;
    }

    public int getEnergyValue() {
        return energyValue;
    }

    public abstract Resource reproduce();
}
