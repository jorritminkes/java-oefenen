package nl.jorritminkes;

import nl.jorritminkes.exceptions.OnvoldoendeSaldoException;
import nl.jorritminkes.notificaties.EmailNotificatie;
import nl.jorritminkes.notificaties.Notificatie;
import nl.jorritminkes.notificaties.SmsNotificatie;
import nl.jorritminkes.notificaties.PushNotificatie;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bestelling bestelling = new Bestelling("1", 125.50, "a@a.nl", "2");

        System.out.println("Bestelling " + bestelling.getBestelNummer() + " op email " + bestelling.getKlantEmail() + " is aangemaakt");

        BetaalMethode betaalMethode = new IdealBetaling();

        try {
            betaalMethode.verwerkBetaling(bestelling.getBedrag());

            List<Notificatie> notificaties = Arrays.asList(
                    new EmailNotificatie("Bestelling aangemaakt"),
                    new SmsNotificatie("Big money binnengekregen, namelijk " + bestelling.getBedrag()),
                    new PushNotificatie("Bestelling met nummer " + bestelling.getBestelNummer() + " is aangekomen.")
            );

            for (Notificatie notificatie : notificaties) {
                notificatie.verstuur(bestelling);
            }

        } catch (OnvoldoendeSaldoException e) {
            System.err.println("FOUT. Kan bestelling " + bestelling.getBestelNummer() + " niet afronden.");
            System.err.println("Reden: " + e.getMessage());

        } catch (Exception e) {
            System.err.println("\nCRITICAL ERROR. Er is een onverwachte fout opgetreden: " + e.getMessage());
        }

        System.out.println("\nklaar met bestelling. Thanks voor je doekoe");
    }
}