package nl.jorritminkes;

public class Mancala extends Hole {

    public Mancala(Hole firstHole, Player owner, int holeNumber) {
        super(owner, holeNumber);

        if (holeNumber == BOARD_SIZE) {
            setNextHole(new Pit(firstHole, getOwner().getOpponent(), holeNumber + 1));
        } else {
            setNextHole(firstHole);
        }
    }
}
