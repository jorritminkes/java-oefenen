package nl.jorritminkes;

public class SmsNotificatie extends Notificatie {

    public SmsNotificatie(String bericht) {
        super(bericht);
    }

    @Override
    void verstuur(Bestelling bestelling) {
        String klantTelefoonnummer = bestelling.getKlantTelefoonnummer();
        String bericht = getBericht();

        System.out.println("[SMS] Verzenden naar " + klantTelefoonnummer + ": " + bericht);
    }
}
