package nl.jorritminkes;

public abstract class Vessel {
    private int serialNumber;
    private String captain;
    private int fuelPercentage;
    
    public Vessel(int serialNumber, String captain, int fuelPercentage) {
        this.serialNumber = serialNumber;
        this.captain = captain;
        this.fuelPercentage = fuelPercentage;
    }
    
    public abstract void executePrimaryMission();
    public abstract void maximumHyperspaceJump();
    
    public int getFuelPercentage() {
        return fuelPercentage;
    }
    
    
    
    
    
}