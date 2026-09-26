package nl.jorritminkes;

import nl.jorritminkes.Fleet.CargoFreighter;
import nl.jorritminkes.Fleet.Dreadnought;
import nl.jorritminkes.Fleet.ReconScout;

public class Main {
    public static void main(String[] args) {
        CommandCenter commandCenter = new CommandCenter();
        CargoFreighter cf1 = new CargoFreighter(1, "dikzak", 10, 1);
        Dreadnought dn1 = new Dreadnought(2, "cap", 0, 10);
        ReconScout rs1 = new ReconScout(3, "ricooo", 100);
        
        commandCenter.addVessel(cf1);
        commandCenter.addVessel(dn1);
        commandCenter.addVessel(rs1);
        
        commandCenter.runFleetMissions();
        
 }
}