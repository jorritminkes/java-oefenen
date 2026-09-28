package nl.jorritminkes;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int BOARD_SIZE = 8;
    private Disk[][] grid;

    public Board() {
        grid = new Disk[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                grid[row][col] = Disk.EMPTY;
            }
        }

        grid[3][3] = Disk.WHITE;
        grid[3][4] = Disk.BLACK;
        grid[4][3] = Disk.BLACK;
        grid[4][4] = Disk.WHITE;
    }

    public boolean isValidMove(Position position, Disk color) {
        int row = position.getRow();
        int col = position.getCol();

        if (grid[row][col] != Disk.EMPTY) {
            return false;
        }

        Disk opponentColor = color.getOpponent();

        for (Direction direction : Direction.values()) {
            int rowDirection = direction.getRow();
            int colDirection = direction.getCol();

            int nextRow = row + rowDirection;
            int nextCol = col + colDirection;

            if (isOnBoard(nextRow, nextCol)
                    && grid[nextRow][nextCol] == opponentColor) {

                while (isOnBoard(nextRow, nextCol)
                        && grid[nextRow][nextCol] == opponentColor) {

                    nextRow += rowDirection;
                    nextCol += colDirection;
                }

                if (isOnBoard(nextRow, nextCol)
                        && grid[nextRow][nextCol] == color) {
                    return true;
                }
            }

        }

        return false;
    }

    private boolean isOnBoard(int row, int col) {
        return row >= 0 && row < BOARD_SIZE
                && col >= 0 && col < BOARD_SIZE;
    }

    public void applyMove(Position position, Disk color) {

    }

    public List<Position> getValidMoves(Disk color) {
        ArrayList<Position> list = new ArrayList<>();
        return list;
    }
}
