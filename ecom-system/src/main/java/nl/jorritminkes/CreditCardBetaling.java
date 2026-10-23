package nl.jorritminkes;

import nl.jorritminkes.exceptions.OnvoldoendeSaldoException;

public class CreditCardBetaling implements BetaalMethode {
    @Override
    public void verwerkBetaling(double bedrag) throws OnvoldoendeSaldoException {
        if (bedrag > 1000) {
            throw new OnvoldoendeSaldoException("Niet genoeg saldo voor overschrijving van " + bedrag + " euro");
        } else if (bedrag < 0) {
            throw new OnvoldoendeSaldoException("Negatief bedrag");
        } else {
            System.out.println("Via creditcard is er " + bedrag + " euro overgemaakt");
        }
    }
}
