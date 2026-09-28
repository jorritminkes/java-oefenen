package nl.jorritminkes;

import java.util.Set;

public class Box {
    private Cell[] cells;
    private int row;
    private int col;
    private Sudoku sudoku;

    public Box(int[] values, int row, int col, Sudoku sudoku) {
        this.row = row;
        this.col = col;
        this.sudoku = sudoku;

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
        boolean localProgress = false;

        for (Cell cell : cells) {
            if (cell.getValue() == 0) {
                Set<Integer> candidates = cell.getCandidates();
                if (candidates.size() == 1) {
                    cell.setValue(candidates.iterator().next());
                    localProgress = true;
                }
            }
        }
        if (!sudoku.isSolved()) {
            Box nextBox = sudoku.getNextBox(this);

            if (nextBox != sudoku.getBox(0, 0) || localProgress) {
                nextBox.solve();
            }
        }
    }
}
