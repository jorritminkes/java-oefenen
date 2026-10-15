package nl.jorritminkes;

public class Player {
    private boolean isToMove;
    private Player opponent;
    private Mancala mancala;

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

    private Player(Player opponent) {
        this.opponent = opponent;
        this.isToMove = !opponent.isToMove;
    }

    public Player getOpponent() {
        return opponent;
    }

    public boolean isToMove() {
        return isToMove;
    }

    void switchMove() {
        isToMove = !isToMove;
        opponent.isToMove = !opponent.isToMove;
    }

    void setMancala(Mancala mancala) {
        this.mancala = mancala;
    }

    Mancala getMancala() {
        return mancala;
    }
}
