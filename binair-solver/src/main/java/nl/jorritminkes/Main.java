package nl.jorritminkes;

public class Main {
    public static void main(String[] args) {
        Board board = new Board("11....0...1..1.1...1.1...1..0..11...0..........1........0....1.0....1..1..1..0......1.....0.1..0.0.1");
        board.solve();
        System.out.println(board.boardAsString());
        board.printBoard();
    }
}