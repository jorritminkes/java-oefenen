package nl.jorritminkes.Fleet;

import nl.jorritminkes.Vessel;

public class CargoFreighter extends Vessel {
    
    private int cargoTonnage;

    public CargoFreighter(int serialNumber, String captain, int fuelPercentage, int cargoTonnage) {
        super(serialNumber, captain, fuelPercentage);
        this.cargoTonnage = cargoTonnage;
    }

    @Override
    public void executePrimaryMission() {
        System.out.println("Initiating docking procedures and offloading " + cargoTonnage + " tons of cargo.");
    }

    @Override
    public void maximumHyperspaceJump() {
        double jumpRange = (getFuelPercentage() * 50.0) - cargoTonnage;
        System.out.println("Jump range is " + jumpRange);
    }

    public int getCargoTonnage() {
        return cargoTonnage;
    }

    public void setCargoTonnage(int cargoTonnage) {
        this.cargoTonnage = cargoTonnage;
    }
}