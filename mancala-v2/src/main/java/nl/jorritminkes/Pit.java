package nl.jorritminkes;

public class Pit extends Hole {

    public Pit() {
        super(new Player(), 1, 4);
        setNextHole(new Pit(this, getOwner(), getHoleNumber() + 1, 4));
    }

    public Pit(Hole firstHole, Player owner, int holeNumber, int seeds) {
        super(firstHole, owner, holeNumber, seeds);

        if (holeNumber == PITS_PER_SIDE || holeNumber == BOARD_SIZE + PITS_PER_SIDE) {
            setNextHole(new Mancala(firstHole, owner, holeNumber + 1, 0));
        } else {
            setNextHole(new Pit(firstHole, owner, holeNumber + 1, 4));
        }
    }

    @Override
    public Hole getHoleAt(int steps) {
        if (steps < 1) {
            throw new IllegalStateException("Invalid value for getHoleAt");
        }

        if (steps == 1) {
            return this;
        } else {
            return getNextHole().getHoleAt(steps - 1);
        }
    }

    Pit getNeighborPit() {
        int neighborHoleNumber = TOTAL_HOLES - getHoleNumber();

        return (Pit) getFirstHole().getHoleAt(neighborHoleNumber);
    }

    public void move() {
        if (!getOwner().isToMove()) {
            throw new IllegalStateException("It is the other player's turn");
        }
        if (getSeeds() == 0) {
            throw new IllegalStateException("Can not play on empty pit");
        }

        int seedsToPass = getSeeds();
        setSeeds(0);
        Hole lastHole = getNextHole().sow(seedsToPass);

        if (lastHole != getOwner().getMancala()) {
            getOwner().switchMove();
        }

        emptyRowsWhenGameOver();
    }

    private void emptyPit() {
        setSeeds(0);
    }

    @Override
    Hole sow(int seedsLeft) {
        boolean wasEmpty = getSeeds() == 0;
        Pit neighbor = getNeighborPit();

        setSeeds(getSeeds() + 1);

        if (seedsLeft >= 2) {
            return getNextHole().sow(seedsLeft - 1);
        } else if (wasEmpty && neighbor.getSeeds() != 0 && getOwner().isToMove()) {
            Mancala mancala = getOwner().getMancala();
            mancala.setSeeds(mancala.getSeeds() + getSeeds() + neighbor.getSeeds());
            emptyPit();
            neighbor.emptyPit();
        }
        return this;
    }

    @Override
    void sweepSeeds(int carried) {
        int total = carried + getSeeds();
        emptyPit();
        getNextHole().sweepSeeds(total);
    }

    void emptyRowsWhenGameOver() {
        if (isGameOver()) {
            Hole firstPitPlayerOne = getFirstHole();
            Hole firstPitPlayerTwo = firstPitPlayerOne.getOwner().getMancala().getNextHole();

            firstPitPlayerOne.sweepSeeds(0);
            firstPitPlayerTwo.sweepSeeds(0);
        }
    }

    boolean isGameOver() {
        return seedsForPlayer(getOwner()) == 0 || seedsForPlayer(getOwner().getOpponent()) == 0;
    }

    int seedsForPlayer(Player player) {
        Hole targetPit = player.getOpponent().getMancala().getNextHole();
        int totalSeeds = 0;

        for (int pit = 1; pit <= PITS_PER_SIDE; pit++) {
            totalSeeds += targetPit.getSeeds();
            targetPit = targetPit.getNextHole();
        }
        return totalSeeds;
    }
}