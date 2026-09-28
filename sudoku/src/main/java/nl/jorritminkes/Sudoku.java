package nl.jorritminkes;

public class Sudoku {
    private Box[] boxes;
    private String initialPuzzle;

    public Sudoku(String puzzleValues) {
        this.initialPuzzle = puzzleValues;
        boxes = new Box[9];

        for (int box = 0; box < 9; box++) {
            int[] values = new int[9];
            int boxRow = box / 3;
            int boxCol = box % 3;
            int index = 0;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int sudokuRow = boxRow * 3 + row;
                    int sudokuCol = boxCol * 3 + col;

                    int puzzleIndex = sudokuRow * 9 + sudokuCol;

                    values[index++] =
                            Character.getNumericValue(puzzleValues.charAt(puzzleIndex));
                }
            }

            boxes[box] = new Box(values, boxRow, boxCol, this);
        }
    }

    public Cell[] getRow(int row) {
        Cell[] cells = new Cell[9];
        for (int col = 0; col < 9; col++) {
            cells[col] = getCell(row, col);
        }
        return cells;
    }

    public Cell[] getCol(int col) {
        Cell[] cells = new Cell[9];
        for (int row = 0; row < 9; row++) {
            cells[row] = getCell(row, col);
        }
        return cells;
    }

    public Box getBox(int boxRow, int boxCol) {
        return boxes[boxRow * 3 + boxCol];
    }

    public Box getNextBox(Box box) {
        int currentRow = box.getRow();
        int currentCol = box.getCol();
        int nextCol = (currentCol + 1) % 3;
        int nextRow = currentRow;
        if (nextCol == 0) {
            nextRow = (nextRow + 1) % 3;
        }
        return getBox(nextRow, nextCol);
    }

    public Cell getCell(int row, int col) {
        Box box = getBox(row / 3, col / 3);

        int cellIndex = (row % 3) * 3 + (col % 3);

        return box.getCells()[cellIndex];
    }

    public String getString() {
        String sodokuString = "";
        for (int row = 0; row < 9; row++) {
            for (Cell cell : getRow(row)) {
                sodokuString += cell.getValue();
            }
        }
        return sodokuString;
    }

    public void printSudokuGrid() {
        String currentGrid = getString();
        for (int row = 0; row < 9; row++) {
            if (row > 0 && row % 3 == 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < 9; col++) {
                if (col > 0 && col % 3 == 0) {
                    System.out.print("| ");
                }
                int index = row * 9 + col;
                char c = currentGrid.charAt(index);
                System.out.print((c == '0' ? '.' : c) + " ");
            }
            System.out.println();
        }
    }

    public boolean isSolved() {
        return !getString().contains("0");
    }

    public void solve() {
        boxes[0].solve();
    }
}
