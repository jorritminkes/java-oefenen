package nl.jorritminkes;

import nl.jorritminkes.exceptions.OnvoldoendeSaldoException;

public class CreditCardBetaling implements BetaalMethode {
    @Override
    public void verwerkBetaling(double bedrag) throws OnvoldoendeSaldoException {
        System.out.println("Via creditcard is er " + bedrag + "euro overgemaakt");
    }
}
