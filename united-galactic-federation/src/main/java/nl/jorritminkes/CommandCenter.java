package nl.jorritminkes;

import java.util.ArrayList;

public class CommandCenter {
    ArrayList<Vessel> vessels = new ArrayList<>();
    
    void addVessel(Vessel vessel) {
        vessels.add(vessel);
    }
    
    void runFleetMissions() {
        for (Vessel vessel : vessels) {
            vessel.executePrimaryMission();
            vessel.maximumHyperspaceJump();
        }
    }
    
    
    
    
    
}
