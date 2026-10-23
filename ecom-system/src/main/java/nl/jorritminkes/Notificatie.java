package nl.jorritminkes;

public abstract class Notificatie {
    private String bericht;

    public Notificatie(String bericht) {
        this.bericht = bericht;
    }

    String getBericht() {
        return bericht;
    }

    abstract void verstuur(Bestelling bestelling);
}
