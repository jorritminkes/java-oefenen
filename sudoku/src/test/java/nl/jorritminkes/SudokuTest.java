package nl.jorritminkes;

import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTest {

    private final String samplePuzzle = "530070000" + "600195000" + "098000060" +
            "800060003" + "400803001" + "700020006" +
            "060000280" + "000419005" + "000080079";

    @Test
    public void testCellInitialValue() {
        Sudoku sudoku = new Sudoku(samplePuzzle);
        Cell targetCell = sudoku.getRow(0)[2]; // Dit zou een '0' moeten zijn in de beginstand

        assertEquals(0, targetCell.getValue(), "Cell initial value should be 0");
    }

    @Test
    public void testCandidateFiltering() {
        Sudoku sudoku = new Sudoku(samplePuzzle);
        Cell targetCell = sudoku.getRow(0)[2];

        Set<Integer> candidates = targetCell.getCandidates();

        assertFalse(candidates.isEmpty(), "Candidates set should not be empty");
        assertTrue(candidates.size() < 9, "Candidates should be filtered based on peers in row, col and box");
    }

    @Test
    public void testPuzzleNotYetSolved() {
        Sudoku sudoku = new Sudoku(samplePuzzle);

        assertFalse(sudoku.isSolved(), "Sudoku should not be marked as solved at start");
    }
}