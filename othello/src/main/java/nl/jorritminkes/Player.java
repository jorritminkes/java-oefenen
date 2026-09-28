package nl.jorritminkes;

public interface Player {
    Position getMove(Board board, Disk color);
}
