package nl.jorritminkes.DeliveryStrategy;

import nl.jorritminkes.Drone;

public interface DeliveryStrategy {
    void executeTurn(Drone drone);
}