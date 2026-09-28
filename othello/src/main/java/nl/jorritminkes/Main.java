package nl.jorritminkes;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();

        // Valid BLACK moves
        testMove(board, 2, 3, Disk.BLACK, true);
        testMove(board, 3, 2, Disk.BLACK, true);
        testMove(board, 4, 5, Disk.BLACK, true);
        testMove(board, 5, 4, Disk.BLACK, true);

        // Invalid BLACK moves
        testMove(board, 0, 0, Disk.BLACK, false);
        testMove(board, 3, 3, Disk.BLACK, false);

        // Valid WHITE moves
        testMove(board, 2, 4, Disk.WHITE, true);
        testMove(board, 3, 5, Disk.WHITE, true);
        testMove(board, 4, 2, Disk.WHITE, true);
        testMove(board, 5, 3, Disk.WHITE, true);
    }

    private static void testMove(
            Board board,
            int row,
            int col,
            Disk color,
            boolean expected
    ) {
        Position position = new Position(row, col);

        boolean result = board.isValidMove(position, color);

        System.out.printf(
                "(%d, %d) %s: %s (expected %s)%n",
                row,
                col,
                color,
                result,
                expected
        );

        if (result != expected) {
            System.out.println("TEST FAILED");
        }
    }
}