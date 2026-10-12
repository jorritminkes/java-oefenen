package nl.jorritminkes;

public abstract class Hole {
    private Hole nextHole;
    private Player owner;
    private int holeNumber;
    public final int PITS_PER_SIDE = 6;
    public final int BOARD_SIZE = 7;

    public Hole(Player owner, int holeNumber) {
        this.owner = owner;
        this.holeNumber = holeNumber;
    }

    public Hole getNextHole() {
        return nextHole;
    }

    public Player getOwner() {
        return owner;
    }

    public int getHoleNumber() {
        return holeNumber;
    }

    public final void setNextHole(Hole nextHole) {
        this.nextHole = nextHole;
    }
}
