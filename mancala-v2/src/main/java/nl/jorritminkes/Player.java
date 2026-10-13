package nl.jorritminkes;

public class Player {
    private boolean isToMove;
    private Player opponent;

    public Player() {
        this.isToMove = true;
        this.opponent = new Player(this);
    }

    public Player(int playerToMove) {
        if (playerToMove == 2) {
            isToMove = false;
        } else {
            isToMove = true;
        }
        this.opponent = new Player(this);
    }

    public Player(Player opponent) {
        this.opponent = opponent;
        this.isToMove = !opponent.isToMove;
    }

    public Player getOpponent() {
        return opponent;
    }

    public boolean isToMove() {
        return isToMove;
    }
}
