package nl.jorritminkes;

public class Main {
    public static void main(String[] args)
    {
        String puzzle = "530070000" + "600195000" + "098000060" +
                "800060003" + "400803001" + "700020006" +
                "060000280" + "000419005" + "000080079";
        Sudoku sudoku = new Sudoku(puzzle);

        sudoku.printSudokuGrid();
        System.out.println("Sudoku created successfully!");

        sudoku.solve();
        System.out.println("Final Result: " + sudoku.getString());
        sudoku.printSudokuGrid();
    }
}

