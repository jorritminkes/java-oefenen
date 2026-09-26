package nl.jorritminkes;

import nl.jorritminkes.smartDevices.SmartLight;
import nl.jorritminkes.smartDevices.SmartThermostat;

public class Main {
    public static void main(String[] args) {
        SmartHome smarthome = new SmartHome();
        
        SmartLight lamp1 = new SmartLight("l1", "rood", 1, true);
        SmartLight lamp2 = new SmartLight("l2", "groen", 50, true);
        SmartLight lamp3 = new SmartLight("l3", "blauw", 100, false);
        
        SmartThermostat therm1 = new SmartThermostat("t1", 30, 20, true);
        SmartThermostat therm2 = new SmartThermostat("t2", 0, -10, false);
        SmartThermostat therm3 = new SmartThermostat("t3", 50, 30, true);
        
        smarthome.addDevice(lamp1);
        smarthome.addDevice(lamp2);
        smarthome.addDevice(lamp3);
        smarthome.addDevice(therm1);
        smarthome.addDevice(therm2);
        smarthome.addDevice(therm3);
        
        // Test changes to brightness
        System.out.println("--- Testen van succesvolle wijzigingen ---");
        lamp1.turnOn();
        try {
            lamp1.setBrightness(50);   
            System.out.println("Lamp 1 helderheid succesvol aangepast.");
        } catch (DeviceOffException e) {
            System.out.println("Fout: " + e.getMessage());
        }
        
        // Test exception
        System.out.println("\n--- Testen van de Exception ---");
        try {
            System.out.println("Proberen thermostaat 2 aan te passen terwijl deze uit staat...");
            therm2.setTargetTemperature(21.0); 
        } catch (DeviceOffException e) {
            System.out.println("Netjes afgevangen! De foutmelding is: " + e.getMessage());
        }
        
        // Status rapport
        System.out.println("\n--- Huidige Status Rapport ---");
        smarthome.printStatusReport();
        System.out.println("Totaal huidig stroomverbruik: " + smarthome.calculateTotalPowerUsage() + " Watt");
        
        // Test verbruik = 0 als alles uit
        System.out.println("\n--- Systeem gaat slapen (alles uit) ---");
        smarthome.turnEverythingOff();
        
        smarthome.printStatusReport();
        System.out.println("Totaal nieuw stroomverbruik: " + smarthome.calculateTotalPowerUsage() + " Watt");
    }
}