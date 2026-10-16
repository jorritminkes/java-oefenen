package nl.jorritminkes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pit firstPit = new Pit();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MANCALA GAME ===");
        System.out.println("Player 1 pits: 1-6 | Mancala: 7");
        System.out.println("Player 2 pits: 8-13 | Mancala: 14\n");

        while (!firstPit.isGameOver()) {
            printBoard(firstPit);

            Player currentPlayer = firstPit.getOwner().isToMove() ? firstPit.getOwner() : firstPit.getOwner().getOpponent();
            String playerName = (currentPlayer == firstPit.getOwner()) ? "Player 1" : "Player 2";

            System.out.println(playerName + "'s turn.");
            System.out.print("Choose a pit to move (Enter pit number): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            try {
                Pit chosenPit = (Pit) firstPit.getHoleAt(choice);
                chosenPit.move();
            } catch (Exception e) {
                System.out.println("Invalid move: " + e.getMessage() + "\n");
            }
        }

        printBoard(firstPit);
        System.out.println("=== GAME OVER ===");
        int p1Score = firstPit.getOwner().getMancala().getSeeds();
        int p2Score = firstPit.getOwner().getOpponent().getMancala().getSeeds();
        System.out.println("Player 1 Mancala: " + p1Score);
        System.out.println("Player 2 Mancala: " + p2Score);

        if (p1Score > p2Score) {
            System.out.println("Player 1 wins!");
        } else if (p2Score > p1Score) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }

        scanner.close();
    }

    private static void printBoard(Pit firstPit) {
        System.out.println("\n--------------------------------------------------");
        // Print Player 2 pits (reversed order 13 down to 8 for traditional top-row view)
        System.out.print("P2 Pits: ");
        for (int i = 13; i >= 8; i--) {
            System.out.print("[" + i + ":" + firstPit.getHoleAt(i).getSeeds() + "] ");
        }
        System.out.println();

        // Mancalas
        System.out.println("P2 Mancala (14): " + firstPit.getHoleAt(14).getSeeds() +
                "  |  P1 Mancala (7): " + firstPit.getHoleAt(7).getSeeds());

        // Print Player 1 pits (1 to 6)
        System.out.print("P1 Pits: ");
        for (int i = 1; i <= 6; i++) {
            System.out.print("[" + i + ":" + firstPit.getHoleAt(i).getSeeds() + "] ");
        }
        System.out.println("\n--------------------------------------------------");
    }
}