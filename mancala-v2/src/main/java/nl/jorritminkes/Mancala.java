package nl.jorritminkes;

public class Mancala extends Hole {

    public Mancala(Hole firstHole, Player owner, int holeNumber) {
        super(firstHole, owner, holeNumber);

        if (holeNumber == BOARD_SIZE) {
            setNextHole(new Pit(firstHole, getOwner().getOpponent(), holeNumber + 1));
        } else {
            setNextHole(firstHole);
        }
    }

    @Override
    public Hole getHoleAt(int steps) {
        if (steps < 1 || steps > 14) {
            throw new IllegalStateException("Invalid value for getHoleAt");
        }

        if (steps == 1) {
            return this;
        } else {
            return getNextHole().getHoleAt(steps - 1);
        }
    }

}
