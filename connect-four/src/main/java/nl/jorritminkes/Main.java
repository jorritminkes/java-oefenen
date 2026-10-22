package nl.jorritminkes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        System.out.println("Player 1 is X, Player 2 is O. Player 1 starts.\n");
        System.out.println("1) Player vs Player");
        System.out.println("2) Player vs Computer (you start)");
        System.out.println("3) Player vs Computer (computer starts)");

        int mode = askMode(scanner);
        Player[] players = createPlayers(mode, scanner);

        board.printBoard();
        while (!board.isGameOver()) {
            System.out.println("Player " + (board.getPlayerToMove() == 1 ? "1 (X)" : "2 (O)") + " to move");
            Player player = players[board.getPlayerToMove() - 1];
            int col = player.chooseMove(board);
            if (player instanceof ComputerPlayer) {
                System.out.println("Computer plays column " + col);
            }
            board.makeMove(col);
            board.printBoard();
        }

        if (board.hasWinner()) {
            System.out.println("Player " + (board.getPlayerToMove() == 1 ? "1 (X)" : "2 (O)") + " wins!");
        } else {
            System.out.println("It is a draw");
        }

        System.out.println("Press Enter to close...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static int askMode(Scanner scanner) {
        while (true) {
            System.out.print("Choose 1, 2 or 3: ");
            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }
            int mode = scanner.nextInt();
            if (mode >= 1 && mode <= 3) {
                return mode;
            }
        }
    }

    private static Player[] createPlayers(int mode, Scanner scanner) {
        int depth = 9;
        switch (mode) {
            case 2:
                return new Player[]{new HumanPlayer(scanner), new ComputerPlayer(depth)};
            case 3:
                return new Player[]{new ComputerPlayer(depth), new HumanPlayer(scanner)};
            default:
                return new Player[]{new HumanPlayer(scanner), new HumanPlayer(scanner)};
        }
    }
}