package nl.jorritminkes;

public class Cell {
    private final int x;
    private final int y;
    private Integer value;

    public Cell(int x, int y, Integer value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    void setValue(Integer value) {
        this.value = value;
    }
}
