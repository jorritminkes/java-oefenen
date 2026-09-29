package nl.jorritminkes;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {

    public Position getMove(Board board, Disk color) {
        Scanner myObj = new Scanner(System.in);

        while (true) {
            System.out.println("Geef een rij en kolom (1 tm 8), bijvoorbeeld: 3 4");
            String moveInput = myObj.nextLine().trim();

            String[] parts = moveInput.split("[,\\s]+");

            if (parts.length == 2) {
                try {
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);

                    if (row >= 1 && row <= 8 && col >= 1 && col <= 8) {
                        Position position = new Position(row - 1, col - 1);

                        List<Position> validMoves = board.getValidMoves(color);

                        if (validMoves.contains(position)) {
                            return position;
                        } else {
                            System.out.println("Dit is geen geldige zet (geen stenen te flankeren). Probeer een andere plek.");
                        }

                    } else {
                        System.out.println("Getallen moeten tussen 1 en 8 liggen. Probeer opnieuw.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ongeldige invoer. Voer twee getallen in.");
                }
            } else {
                System.out.println("Typ exact twee getallen gescheiden door een spatie of komma.");
            }
        }
    }
}