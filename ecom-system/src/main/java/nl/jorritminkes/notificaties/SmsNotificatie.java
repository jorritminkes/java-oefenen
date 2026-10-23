package nl.jorritminkes.notificaties;

import nl.jorritminkes.Bestelling;

public class SmsNotificatie extends Notificatie {

    public SmsNotificatie(String bericht) {
        super(bericht);
    }

    @Override
    public void verstuur(Bestelling bestelling) {
        String klantTelefoonnummer = bestelling.getKlantTelefoonnummer();
        String bericht = getBericht();

        System.out.println("[SMS] Verzenden naar " + klantTelefoonnummer + ": " + bericht);
    }
}
