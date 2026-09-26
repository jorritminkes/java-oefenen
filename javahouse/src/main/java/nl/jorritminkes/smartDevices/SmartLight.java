package nl.jorritminkes.smartDevices;

import nl.jorritminkes.DeviceOffException;
import nl.jorritminkes.SmartDevice;

public class SmartLight implements SmartDevice {
    String naam;
    String kleur;
    int helderheid;
    boolean isAan;
    
    public SmartLight(String naam, String kleur, int helderheid, boolean isAan) {
        this.naam = naam;
        this.kleur = kleur;
        this.helderheid = helderheid;
        this.isAan = isAan;
    }
    
    @Override
    public void turnOn() {
        isAan = true;
    }
    
    @Override
    public void turnOff() {
        isAan = false;
    }
    
    @Override
    public boolean isOn() {
        return isAan;
    }
    
    @Override
    public String getStatus() {
        if (isAan) {
            return "Het apparaat staat aan en heeft helderheid: " + helderheid;
        }
        return "Het apparaat staat uit.";
    }
    
    @Override
    public double getPowerConsumption() {
        double powerConsumption = 0.2 * this.helderheid;
        return isAan ? powerConsumption : 0;
    }
    
    public void setBrightness(int helderheid) throws DeviceOffException {        
        if (isAan) {
            if (helderheid > 100) {
                helderheid = 100;
            } else if (helderheid < 0) {
                helderheid = 0;
            }
            this.helderheid = helderheid;
        } else {
            throw new DeviceOffException("Lamp staat uit");
        }
    }
    
    
}