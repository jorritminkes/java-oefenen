package nl.jorritminkes;

public class Board {
    private int[][] board;
    private static final int EMPTY = 0;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;
    private int playerToMove = PLAYER_1;
    private boolean isGameOver = false;
    private boolean winnerFound = false;

    public Board() {
        board = new int[7][6];
    }

    private Board(Board other) {
        board = new int[other.board.length][];
        for (int col = 0; col < other.board.length; col++) {
            board[col] = other.board[col].clone();
        }
        playerToMove = other.playerToMove;
        isGameOver = other.isGameOver;
        winnerFound = other.winnerFound;
    }

    public Board copy() {
        return new Board(this);
    }

    public boolean canPlay(int col) {
        return col >= 0 && col < board.length && board[col][0] == EMPTY;
    }

    public int getPlayerToMove() {
        return playerToMove;
    }

    public boolean hasWinner() {
        return winnerFound;
    }

    public int getCell(int col, int row) {
        return board[col][row];
    }

    public boolean makeMove(int col) {
        if (isGameOver) {
            return false;
        }

        if (col < 0 || col >= board.length) {
            return false;
        }

        for (int row = board[col].length - 1; row >= 0; row--) {
            if (board[col][row] == EMPTY) {
                board[col][row] = playerToMove;

                if (hasWon(playerToMove)) {
                    isGameOver = true;
                    winnerFound = true;
                    return true;
                }

                if (isBoardFull()) {
                    isGameOver = true;
                    return true;
                }

                switchMove();
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

    private boolean hasWon(int player) {
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                if (board[col][row] != player) continue;

                if (col + 3 < 7 && board[col + 1][row] == player &&
                        board[col + 2][row] == player && board[col+3][row] == player) {
                    return true;
                }

                if (row + 3 < 6 && board[col][row + 1] == player &&
                        board[col][row + 2] == player && board[col][row + 3] == player) {
                    return true;
                }

                if (col + 3 < 7 && row + 3 < 6 && board[col + 1][row + 1] == player &&
                        board[col + 2][row + 2] == player && board[col + 3][row + 3] == player) {
                    return true;
                }

                if (col + 3 < 7 && row - 3 >= 0 && board[col + 1][row - 1] == player &&
                        board[col + 2][row - 2] == player && board[col + 3][row - 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[col][row] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
