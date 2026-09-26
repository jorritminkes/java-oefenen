package nl.jorritminkes.Fleet;

import nl.jorritminkes.Vessel;

public class ReconScout extends Vessel {
    
    public ReconScout(int serialNumber, String captain, int fuelPercentage) {
        super(serialNumber, captain, fuelPercentage);
    }
    
    @Override
    public void executePrimaryMission() {
        System.out.println("Running a deep-space sensor sweep and recording planetary data");
    }
    
    @Override
    public double maximumHyperspaceJump() {
        double jumpRange = getFuelPercentage() * 100;
        return jumpRange;        
    }
}
