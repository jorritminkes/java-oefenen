package nl.jorritminkes;

import nl.jorritminkes.exceptions.OnvoldoendeSaldoException;

public interface BetaalMethode {
    void verwerkBetaling(double bedrag) throws OnvoldoendeSaldoException;
}
