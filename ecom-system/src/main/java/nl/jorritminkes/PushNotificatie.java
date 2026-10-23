package nl.jorritminkes;

public class PushNotificatie extends Notificatie {

    public PushNotificatie(String bericht) {
        super(bericht);
    }

    @Override
    void verstuur(Bestelling bestelling) {
        String bericht = getBericht();

        System.out.println("[PUSH] App notificatie: " + bericht);
    }
}
