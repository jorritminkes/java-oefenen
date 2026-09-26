package nl.jorritminkes.smartDevices;

import nl.jorritminkes.DeviceOffException;
import nl.jorritminkes.SmartDevice;

public class SmartThermostat implements SmartDevice {
    String naam;
    double doelTemperatuur;
    double huidigeTemperatuur;
    boolean isAan;
    
    public SmartThermostat(String naam, double doelTemperatuur, double huidigeTemperatuur, boolean isAan) {
        this.naam = naam;
        this.doelTemperatuur = doelTemperatuur;
        this.huidigeTemperatuur = huidigeTemperatuur;
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
            return "Het apparaat staat aan";
        }
        return "Het apparaat staat uit.";
    }
    
    @Override
    public double getPowerConsumption() {
        double powerConsumption = (doelTemperatuur > huidigeTemperatuur) ? 2000 : 15;
        
        return isAan ? powerConsumption: 0;
    }
    
    public void setTargetTemperature(double temp) throws DeviceOffException {
        if (isAan) {
            doelTemperatuur = temp;
        } else {
            throw new DeviceOffException("Thermostaat staat uit");
        }
    }
}