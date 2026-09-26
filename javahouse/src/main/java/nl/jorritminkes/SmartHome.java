package nl.jorritminkes;

import java.util.ArrayList;

class SmartHome {
    ArrayList<SmartDevice> devices = new ArrayList<>();
    
    void addDevice(SmartDevice device) {
        devices.add(device);
    }
    
    void turnEverythingOff() {
        for (SmartDevice device : devices) {
            device.turnOff();
        }
    }
    
    double calculateTotalPowerUsage() {
        double totaalVerbruik = 0;
        
        for (SmartDevice device : devices) {
            totaalVerbruik += device.getPowerConsumption();
        }
        
        return totaalVerbruik;
    }
    
    void printStatusReport() {
        for (SmartDevice device : devices) {
            System.out.println(device.getStatus());
        }
    }
}