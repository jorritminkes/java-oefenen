package nl.jorritminkes;

public class Sudoku {
    private Box[] boxes;

    public Sudoku(String puzzleValues) {
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

    public Box[] getBoxes() {
        return boxes;
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

    public Cell getCell(int row, int col) {
        Box box = getBox(row / 3, col / 3);

        int cellIndex = (row % 3) * 3 + (col % 3);

        return box.getCells()[cellIndex];
    }

    public String solve() {
        System.out.println("Starting puzzle solving now");

        boxes[0].solve();

        return "solved";
    }
}
