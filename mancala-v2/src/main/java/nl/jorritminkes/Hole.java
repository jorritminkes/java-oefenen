package nl.jorritminkes;

public abstract class Hole {
    private final Hole firstHole;
    private Hole nextHole;
    private int seeds;
    private final Player owner;
    private final int holeNumber;
    public final int PITS_PER_SIDE = 6;
    public final int BOARD_SIZE = 7;

    public Hole(Player owner, int holeNumber, int seeds) {
        this.firstHole = this;
        this.owner = owner;
        this.holeNumber = holeNumber;
        this.seeds = seeds;
    }

    public Hole(Hole firstHole, Player owner, int holeNumber, int seeds) {
        this.firstHole = firstHole;
        this.owner = owner;
        this.holeNumber = holeNumber;
        this.seeds = seeds;
    }

    public Hole getFirstHole() {
        return firstHole;
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

    public int getSeeds() {
        return seeds;
    }

    void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public final void setNextHole(Hole nextHole) {
        this.nextHole = nextHole;
    }

    public abstract Hole getHoleAt(int holeNumber);

    abstract void sow(int seedsLeft);
}
