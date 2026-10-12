package nl.jorritminkes;

public class Pit extends Hole {

    public Pit() {
        super(new Player(), 1);
        setNextHole(new Pit(this, getOwner(), getHoleNumber() + 1));
    }

    public Pit(Hole firstHole, Player owner, int holeNumber) {
        super(owner, holeNumber);

        if (holeNumber == PITS_PER_SIDE || holeNumber == BOARD_SIZE + PITS_PER_SIDE) {
            setNextHole(new Mancala(firstHole, owner, holeNumber + 1));
        } else {
            setNextHole(new Pit(firstHole, owner, holeNumber + 1));
        }
    }
}