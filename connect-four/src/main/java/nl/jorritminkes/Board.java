package nl.jorritminkes;

public class Board {
    private int[][] board;
    private static final int EMPTY = 0;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;
    private int playerToMove = PLAYER_1;

    public Board() {
        board = new int[7][6];
        printBoard();
    }

    public boolean makeMove(int col) {
        for (int row = 5; row >= 0; row--) {
            if (board[col][row] == EMPTY) {
                board[col][row] = playerToMove;
                switchMove();
                printBoard();
                return true;
            }
        }
        return false;
    }

    private void switchMove() {
        playerToMove = (playerToMove == PLAYER_1) ? PLAYER_2 : PLAYER_1;
    }

    public void printBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                char symbol = '.';
                if (board[col][row] == PLAYER_1) {
                    symbol = 'X';
                } else if (board[col][row] == PLAYER_2) {
                    symbol = 'O';
                }
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
        System.out.println();
    }
}
