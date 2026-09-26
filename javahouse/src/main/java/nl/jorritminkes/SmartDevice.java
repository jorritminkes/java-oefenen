package nl.jorritminkes;

public interface SmartDevice {
    void turnOn();
    void turnOff();
    boolean isOn();
    String getStatus();
    double getPowerConsumption();
    
}