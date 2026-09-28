package nl.jorritminkes;

public class Box {
    private Cell[] cells;
    private int row;
    private int col;

    public Box(int[] values, int row, int col, Sudoku sudoku) {
        this.row = row;
        this.col = col;

        this.cells = new Cell[9];

        for (int i = 0; i < 9; i++) {
            int cellRow = row * 3 + i / 3;
            int cellCol = col * 3 + i % 3;

            cells[i] = new Cell(values[i], cellRow, cellCol, sudoku);
        }
    }

    public Cell[] getCells() {
        return cells;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public void solve() {
        for (Cell cell : cells) {
            cell.solveCell();
        }
    }
}
