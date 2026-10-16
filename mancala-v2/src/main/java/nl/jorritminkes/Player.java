package nl.jorritminkes;

public class Player {
    private boolean isToMove;
    private final Player opponent;
    private Mancala mancala;
    private final int number;

    public Player() {
        this.number = 1;
        this.isToMove = true;
        this.opponent = new Player(this);
    }

    public Player(int playerToMove) {
        this.number = 1;
        this.isToMove = (playerToMove != 2);
        this.opponent = new Player(this);
    }

    private Player(Player opponent) {
        this.opponent = opponent;
        this.isToMove = !opponent.isToMove;
        this.number = opponent.getNumber() == 1 ? 2 : 1;
    }

    public Player getOpponent() {
        return opponent;
    }

    public int getNumber() {
        return number;
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
