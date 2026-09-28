package nl.jorritminkes;

import java.util.TreeSet;
import java.util.Set;

public class Cell {
    private int value;
    private int row;
    private int col;
    private Sudoku sudoku;

    public Cell(int value, int row, int col, Sudoku sudoku) {
        this.value = value;
        this.row = row;
        this.col = col;
        this.sudoku = sudoku;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public Set<Integer> getCandidates() {
        Set<Integer> candidates = new TreeSet<>();

        for (int value = 1; value <= 9; value++) {
            candidates.add(value);
        }

        for (Cell cell : sudoku.getRow(row)) {
            candidates.remove(cell.getValue());
        }

        for (Cell cell : sudoku.getCol(col)) {
            candidates.remove(cell.getValue());
        }

        Box box = sudoku.getBox(row / 3, col / 3);

        for (Cell cell : box.getCells()) {
            candidates.remove(cell.getValue());
        }

        return candidates;
    }

}
