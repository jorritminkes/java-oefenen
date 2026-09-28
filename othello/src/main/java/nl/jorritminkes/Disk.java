package nl.jorritminkes;

public enum Disk {
    BLACK,
    WHITE,
    EMPTY;

    public Disk getOpponent() {
        if (this == BLACK) {
            return WHITE;
        } else if (this == WHITE) {
            return BLACK;
        } else {
            return EMPTY;
        }
    }
}