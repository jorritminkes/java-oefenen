package nl.jorritminkes.notificaties;

import nl.jorritminkes.Bestelling;

public class PushNotificatie extends Notificatie {

    public PushNotificatie(String bericht) {
        super(bericht);
    }

    @Override
    public void verstuur(Bestelling bestelling) {
        String bericht = getBericht();

        System.out.println("[PUSH] App notificatie: " + bericht);
    }
}
