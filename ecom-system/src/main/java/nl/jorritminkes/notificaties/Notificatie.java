package nl.jorritminkes.notificaties;

import nl.jorritminkes.Bestelling;

public abstract class Notificatie {
    private String bericht;

    public Notificatie(String bericht) {
        this.bericht = bericht;
    }

    public String getBericht() {
        return bericht;
    }

    public abstract void verstuur(Bestelling bestelling);
}
