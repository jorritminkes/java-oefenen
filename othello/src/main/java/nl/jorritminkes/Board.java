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
        return !getDisksToFLip(position, color).isEmpty();
    }

    private List<Position> getDisksToFLip(Position position, Disk color) {
        List<Position> disksToFlip = new ArrayList<>();
        int row = position.getRow();
        int col = position.getCol();

        if (!isOnBoard(row,col) || grid[row][col] != Disk.EMPTY) {
            return disksToFlip;
        }

        Disk opponentColor = color.getOpponent();

        for (Direction direction : Direction.values()) {
            int rowDirection = direction.getRow();
            int colDirection = direction.getCol();

            int currentRow = row + rowDirection;
            int currentCol = col + colDirection;

            List<Position> tempDirectionDisks = new ArrayList<>();

            while (isOnBoard(currentRow, currentCol)
                    && grid[currentRow][currentCol] == opponentColor) {

                tempDirectionDisks.add(new Position(currentRow, currentCol));

                currentRow += rowDirection;
                currentCol += colDirection;
            }

            if (isOnBoard(currentRow, currentCol)
                    && grid[currentRow][currentCol] == color) {
                disksToFlip.addAll(tempDirectionDisks);
            }

        }
        return disksToFlip;
    }

    private boolean isOnBoard(int row, int col) {
        return row >= 0 && row < BOARD_SIZE
                && col >= 0 && col < BOARD_SIZE;
    }

    public void applyMove(Position position, Disk color) {
        if (!isValidMove(position, color)) {
            return;
        }

        List<Position> disksToFlip = getDisksToFLip(position, color);

        grid[position.getRow()][position.getCol()] = color;

        for (Position positionsToFlip : disksToFlip) {
            int rowToFlip = positionsToFlip.getRow();
            int colToFlip = positionsToFlip.getCol();
            grid[rowToFlip][colToFlip] = color;
        }
    }

    public List<Position> getValidMoves(Disk color) {
        List<Position> validMoves = new ArrayList<>();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Position currentPosition = new Position(row, col);
                Disk disk = grid[row][col];

                if (isValidMove(currentPosition, color) && disk == Disk.EMPTY) {
                    validMoves.add(currentPosition);
                }

            }
        }
        return validMoves;
    }

    public void printBoard(Disk currentTurn) {
        System.out.println("   1  2  3  4  5  6  7  8  (Kolom)");
        System.out.println("  ------------------------");

        List<Position> validMoves = getValidMoves(currentTurn);

        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print((row + 1) + "| "); // Rijnummer aan de zijkant
            for (int col = 0; col < BOARD_SIZE; col++) {
                char symbol = '.';
                Disk disk = grid[row][col];

                if (disk == Disk.BLACK) {
                    symbol = 'B';
                } else if (disk == Disk.WHITE) {
                    symbol = 'W';
                } else {
                    Position pos = new Position(row, col);
                    if (validMoves.contains(pos)) {
                        symbol = 'o';
                    }
                }
                System.out.print(symbol + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
