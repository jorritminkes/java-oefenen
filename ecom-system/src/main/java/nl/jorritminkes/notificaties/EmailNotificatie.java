package nl.jorritminkes.notificaties;

import nl.jorritminkes.Bestelling;

public class EmailNotificatie extends Notificatie {

    public EmailNotificatie(String bericht) {
        super(bericht);
    }

    @Override
    public void verstuur(Bestelling bestelling) {
        String klantEmail = bestelling.getKlantEmail();
        String bericht = getBericht();

        System.out.println("[EMAIL] Verzenden naar " + klantEmail + ": " + bericht);
    }
}
