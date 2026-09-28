package nl.jorritminkes;

public enum Direction {
    RIGHT(0, 1),
    UP_RIGHT(-1, 1),
    UP(-1, 0),
    UP_LEFT(-1, -1),
    LEFT(0, -1),
    DOWN_LEFT(1, -1),
    DOWN(1, 0),
    DOWN_RIGHT(1, 1);



    private final int row;
    private final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}


// // Can now do:
//for (Direction direction : Direction.values()) {
//int rowDirection = direction.getRow();
//int colDirection = direction.getCol();
//
//// Check this direction...
//}