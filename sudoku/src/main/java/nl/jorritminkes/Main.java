package nl.jorritminkes;

public class Main {
    public static void main(String[] args) {
        String puzzle = "078050001203000540549008637056100000192000783000007160405800319027000806800060270";
        Sudoku sudoku = new Sudoku(puzzle);

        sudoku.printSudokuGrid();
        System.out.println("Sudoku created successfully!");

        sudoku.solve();
        System.out.println("Final Result: " + sudoku.getString());
        sudoku.printSudokuGrid();
    }
}

