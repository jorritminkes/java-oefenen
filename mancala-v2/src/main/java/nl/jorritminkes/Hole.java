package nl.jorritminkes;

public abstract class Hole {
    private final Hole firstHole;
    private Hole nextHole;
    private int seeds;
    private final Player owner;
    private final int holeNumber;
    public static final int PITS_PER_SIDE = 6;
    public static final int BOARD_SIZE = 7;
    public static final int TOTAL_HOLES = 14;

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

    public Player getOpponent() {
        return owner.getOpponent();
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

    public Hole getHoleRelative(int steps) {
        if (steps < 0) {
            throw new IllegalStateException("Invalid value for getHoleAt");
        } else if (steps == 0) {
            return this;
        } else {
            return getNextHole().getHoleRelative(steps - 1);
        }
    }

    Hole getHoleAt(int holeNumber) {
        if (holeNumber < 1) {
            throw new IllegalArgumentException(
                    "Hole number must be 1 at a minimum");
        }
        return firstHole.getHoleRelative(holeNumber - 1);
    }

    public int getSeedsAt(int holeNumber) {
        return getHoleAt(holeNumber).getSeeds();
    }

    public int getScore(Player player) {
        return player.getMancala().getSeeds();
    }



    abstract Hole sow(int seedsLeft);

    abstract void sweepSeeds(int carried);
}
