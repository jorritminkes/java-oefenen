package nl.jorritminkes;

public class Board {
    private Cell[][] cells = new Cell[10][10];

    public Board(String input) {
        if (input.length() != 100) {
            throw new IllegalArgumentException("Input must be 100 characters");
        }

        for (int i = 0; i < 100; i++) {
            char c = input.charAt(i);

            int y = i / 10;
            int x = i % 10;

            if (c == '.') {
                cells[x][y] = new Cell(x, y, null);
            } else if (c == '0' || c == '1') {
                cells[x][y] = new Cell(x, y, c - '0');
            } else {
                throw new IllegalArgumentException(
                        "Invalid character at index " + i + ": " + c
                );
            }
        }
    }

    public Cell[][] solve() {
        while (!isSolved()) {
            for (int i = 0; i < 100; i++) {
                
            }
        }
    }

    public boolean isSolved() {
        for (int i = 0; i <= 100; i++) {
            int y = i / 10;
            int x = i % 10;
            if (cells[x][y] == null) {
                return false;
            }
        }
        return true;
    }
}
