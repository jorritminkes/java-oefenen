package nl.jorritminkes;

import java.util.Objects;

public class Board {
    private final Cell[][] cells = new Cell[10][10];
    private final String originalString;
    private final static int BOARD_SIZE = 100;
    private final static int SIZE = 10;
    private final static int MAX_INDEX = SIZE - 1;

    public Board(String input) {
        if (input.length() != BOARD_SIZE) {
            throw new IllegalArgumentException("Input must be 100 characters");
        }

        this.originalString = input;

        for (int i = 0; i < BOARD_SIZE; i++) {
            char c = input.charAt(i);

            int x = i % SIZE;
            int y = i / SIZE;

            if (c == '.') {
                cells[x][y] = new Cell(x, y, null);
            } else if (c == '0' || c == '1') {
                cells[x][y] = new Cell(x, y, c - '0');
            } else {
                throw new IllegalArgumentException(
                        "Invalid character at index " + i + ": " + c
                );
            }
        }
    }

    public String originalString() {
        return originalString;
    }

    public void solve() {
        boolean progress;

        do {
            progress = false;

            for (int i = 0; i < BOARD_SIZE; i++) {
                int x = i % SIZE;
                int y = i / SIZE;

                Cell cell = cells[x][y];

                if (cell.getValue() == null) {
                    solveCell(cell);

                    if (cell.getValue() != null) {
                        progress = true;
                    }
                }
            }
        } while (hasEmptyCells() && progress);
    }

    private void solveCell(Cell cell) {
        horizontalSqueezeSolve(cell);
        if (cell.getValue() != null) return;
        verticalSqueezeSolve(cell);
        if (cell.getValue() != null) return;
        leftSolve(cell);
        if (cell.getValue() != null) return;
        upSolve(cell);
        if (cell.getValue() != null) return;
        rightSolve(cell);
        if (cell.getValue() != null) return;
        downSolve(cell);
        if (cell.getValue() != null) return;
        columnCounter(cell);
        if (cell.getValue() != null) return;
        rowCounter(cell);
    }

    private void horizontalSqueezeSolve(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if (x < 1 || x > MAX_INDEX - 1) {
            return;
        }

        Integer leftValue = valueAt(x - 1, y);
        Integer rightValue = valueAt(x + 1, y);

        if (leftValue != null && Objects.equals(leftValue, rightValue)) {
            cell.setValue(leftValue == 0 ? 1 : 0);
        }
    }

    private void verticalSqueezeSolve(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if (y < 1 || y > MAX_INDEX - 1) {
            return;
        }

        Integer upValue = valueAt(x, y - 1);
        Integer downValue = valueAt(x, y + 1);

        if (upValue != null && Objects.equals(upValue, downValue)) {
            cell.setValue(upValue == 0 ? 1 : 0);
        }
    }

    private void leftSolve(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if (x < 2) {
            return;
        }

        Integer leftLeftValue = valueAt(x - 2, y);
        Integer leftValue = valueAt(x - 1, y);

        if (leftLeftValue != null && Objects.equals(leftLeftValue, leftValue)) {
            cell.setValue(leftLeftValue == 0 ? 1 : 0);
        }
    }

    private void upSolve(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if (y < 2) {
            return;
        }

        Integer upUpValue = valueAt(x, y - 2);
        Integer upValue = valueAt(x, y - 1);

        if (upUpValue != null && Objects.equals(upUpValue, upValue)) {
            cell.setValue(upUpValue == 0 ? 1 : 0);
        }
    }

    private void rightSolve(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if (x > MAX_INDEX - 2) {
            return;
        }

        Integer rightRightValue = valueAt(x + 2, y);
        Integer rightValue = valueAt(x + 1, y);

        if (rightRightValue != null && Objects.equals(rightRightValue, rightValue)) {
            cell.setValue(rightRightValue == 0 ? 1 : 0);
        }
    }

    private void downSolve(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if (y > MAX_INDEX - 2) {
            return;
        }

        Integer downDownValue = valueAt(x, y + 2);
        Integer downValue = valueAt(x, y + 1);

        if (downDownValue != null && Objects.equals(downDownValue, downValue)) {
            cell.setValue(downDownValue == 0 ? 1 : 0);
        }
    }

    private void columnCounter(Cell cell) {
        int column = cell.getX();
        int numberOfOnes = 0;
        int numberOfZeros = 0;

        for (int y = 0; y < SIZE; y++) {
            Integer value = valueAt(column, y);
            if (value == null) {
                continue;
            }
            if (value == 0) {
                numberOfZeros++;
            } else {
                numberOfOnes++;
            }
        }

        if (numberOfOnes == (SIZE / 2)) {
            cell.setValue(0);
        } else if (numberOfZeros == (SIZE / 2)) {
            cell.setValue(1);
        }
    }

    private void rowCounter(Cell cell) {
        int row = cell.getY();
        int numberOfOnes = 0;
        int numberOfZeros = 0;

        for (int x = 0; x < SIZE; x++) {
            Integer value = valueAt(x, row);
            if (value == null) {
                continue;
            }
            if (value == 0) {
                numberOfZeros++;
            } else {
                numberOfOnes++;
            }
        }
        if (numberOfOnes == (SIZE / 2)) {
            cell.setValue(0);
        } else if (numberOfZeros == (SIZE / 2)) {
            cell.setValue(1);
        }
    }

    private boolean hasEmptyCells() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            int x = i % SIZE;
            int y = i / SIZE;
            if (cells[x][y].getValue() == null) {
                return true;
            }
        }
        return false;
    }

    private Integer valueAt(int x, int y) {
        return cells[x][y].getValue();
    }

    public String boardAsString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < BOARD_SIZE; i++) {
            int x = i % SIZE;
            int y = i / SIZE;
            Integer value = cells[x][y].getValue();
            result.append(value == null ? "." : value.toString());
        }
        return result.toString();
    }

    public void printBoard() {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Integer value = valueAt(x, y);
                result.append(value == null ? "." : value.toString()).append(' ');
            }
            result.append('\n');
        }

        System.out.print(result);
    }
}
