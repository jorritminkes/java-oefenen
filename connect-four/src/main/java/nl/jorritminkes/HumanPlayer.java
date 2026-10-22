package nl.jorritminkes;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner scanner;

    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int chooseMove(Board board) {
        while (true) {
            System.out.print("Enter column (0-6) to drop your piece: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 6.");
                scanner.next();
                continue;
            }

            int col = scanner.nextInt();
            if (board.canPlay(col)) {
                return col;
            }
            System.out.println("That column is full or does not exist. Try again.");
        }
    }
}