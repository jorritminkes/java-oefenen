package nl.jorritminkes;

public class Mancala extends Hole {

    public Mancala(Hole firstHole, Player owner, int holeNumber, int seeds) {
        super(firstHole, owner, holeNumber, 0);

        if (holeNumber == BOARD_SIZE) {
            setNextHole(new Pit(firstHole, getOwner().getOpponent(), holeNumber + 1, 4));
        } else {
            setNextHole(firstHole);
        }
        
        getOwner().setMancala(this);
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

    @Override
    Hole sow(int seedsLeft) {
        if (!getOwner().isToMove()) {
            return getNextHole().sow(seedsLeft);
        }
        setSeeds(getSeeds() + 1);
        return seedsLeft >= 2 ? getNextHole().sow(seedsLeft - 1) : this;
    }
}
