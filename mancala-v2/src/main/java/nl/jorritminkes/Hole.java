package nl.jorritminkes;

public abstract class Hole {
    private Hole nextHole;
    private Player owner;
    private int holeNumber;

    public Hole(Hole nextHole, Player owner, int holeNumber) {
        this.nextHole = nextHole;
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
}
