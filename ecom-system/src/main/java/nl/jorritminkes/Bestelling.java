package nl.jorritminkes;

public class Bestelling {
    private String bestelNummer;
    private double bedrag;
    private String klantEmail;
    private String klantTelefoonnummer;

    public Bestelling(String bestelNummer, double bedrag, String klantEmail, String klantTelefoonnummer) {
        this.bestelNummer = bestelNummer;
        this.bedrag = bedrag;
        this.klantEmail = klantEmail;
        this.klantTelefoonnummer = klantTelefoonnummer;
    }

    public String getBestelNummer() {
        return bestelNummer;
    }

    public double getBedrag() {
        return bedrag;
    }

    public String getKlantEmail() {
        return klantEmail;
    }

    public String getKlantTelefoonnummer() {
        return klantTelefoonnummer;
    }
 }
