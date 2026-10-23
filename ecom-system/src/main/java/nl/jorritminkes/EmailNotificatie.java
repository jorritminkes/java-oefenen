package nl.jorritminkes;

public class EmailNotificatie extends Notificatie {

    public EmailNotificatie(String bericht) {
        super(bericht);
    }

    @Override
    void verstuur(Bestelling bestelling) {
        String klantEmail = bestelling.getKlantEmail();
        String bericht = getBericht();

        System.out.println("[EMAIL] Verzenden naar " + klantEmail + ": " + bericht);
    }
}
