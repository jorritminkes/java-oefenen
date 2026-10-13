package nl.jorritminkes;

public class Pit extends Hole {

    public Pit() {
        super(new Player(), 1, 4);
        setNextHole(new Pit(this, getOwner(), getHoleNumber() + 1, 4));
    }

    public Pit(Hole firstHole, Player owner, int holeNumber, int seeds) {
        super(firstHole, owner, holeNumber, 4);

        if (holeNumber == PITS_PER_SIDE || holeNumber == BOARD_SIZE + PITS_PER_SIDE) {
            setNextHole(new Mancala(firstHole, owner, holeNumber + 1, 0));
        } else {
            setNextHole(new Pit(firstHole, owner, holeNumber + 1, 4));
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

//    public void move() {
//        if (getOwner() == )
//    }
}