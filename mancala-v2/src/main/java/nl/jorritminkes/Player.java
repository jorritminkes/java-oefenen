package nl.jorritminkes;

public class Player {
    private Player opponent;

    public Player() {
        this.opponent = new Player(this);
    }

    public Player(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }
}
