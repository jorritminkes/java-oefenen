package nl.jorritminkes;

public class Pit extends Hole {

    public Pit() {
        Player player1 = new Player();
        super(nextHole, player1, 1);
    }

    public Pit(Hole nextHole, Player owner, int holeNumber) {
        super(nextHole, owner, holeNumber);

    }
}
