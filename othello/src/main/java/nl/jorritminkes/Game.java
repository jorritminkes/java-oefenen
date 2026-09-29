package nl.jorritminkes;

import java.util.List;

public class Game {
    private Board board = new Board();
    private Player player1 = new HumanPlayer();
    private Player player2 = new RandomBot();

    public void start() {
        Disk currentTurn = Disk.BLACK;

        while (!board.getValidMoves(Disk.BLACK).isEmpty() || !board.getValidMoves(Disk.WHITE).isEmpty()) {

            List<Position> validMoves = board.getValidMoves(currentTurn);

            if (validMoves.isEmpty()) {
                currentTurn = currentTurn.getOpponent();
                continue;
            }

            Position playerMove;
            if (currentTurn == Disk.BLACK) {
                playerMove = player1.getMove(board, Disk.BLACK);
            } else {
                playerMove = player2.getMove(board, Disk.WHITE);
            }

            if (board.isValidMove(playerMove, currentTurn)) {
                board.applyMove(playerMove, currentTurn);
            }


            currentTurn = currentTurn.getOpponent();
        }

        System.out.println("Het spel is afgelopen");
    }
}
