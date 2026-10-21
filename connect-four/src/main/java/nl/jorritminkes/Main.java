package nl.jorritminkes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1 is X, Player 2 is O.");
        System.out.println("Player 1 starts.\n");

        while (!board.isGameOver()) {
            System.out.print("Enter column (0-6) to drop your piece: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 6.");
                scanner.next();
                continue;
            }

            int col = scanner.nextInt();
            board.makeMove(col);
        }

        System.out.println("Lekker gewerkt pik. press enter to close");
        scanner.nextLine();
        scanner.nextLine();
    }
}