package nl.jorritminkes.Fleet;

import nl.jorritminkes.Vessel;

public class Dreadnought extends Vessel {
    
    private int weaponPower;

    public Dreadnought(int serialNumber, String captain, int fuelPercentage, int weaponPower) {
        super(serialNumber, captain, fuelPercentage);
        this.weaponPower = weaponPower;
    }

    @Override
    public void executePrimaryMission() {
        System.out.println("Charging weapons to " + weaponPower + " level and engaging the nearest hostile target.");
    }

    @Override
    public void maximumHyperspaceJump() {
        double jumpRange = getFuelPercentage() * 10.0;
        System.out.println("Jump range is " + jumpRange);
    }

    public int getWeaponPower() {
        return weaponPower;
    }

    public void setWeaponPower(int weaponPower) {
        this.weaponPower = weaponPower;
    }
}