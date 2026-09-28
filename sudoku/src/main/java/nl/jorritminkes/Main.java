package nl.jorritminkes;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("hi");

        String puzzle = "530070000" + "600195000" + "098000060" + "800060003" + "400803001" + "700020006" + "060000280" + "000419005" + "000080079";
        Sudoku sudoku = new Sudoku(puzzle);
        System.out.println("Sudoku created successfully!");
        System.out.println(sudoku.solve());

        System.out.println(sudoku.getRow(2)[0].getCandidates());
    }
}

