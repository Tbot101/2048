package _2048;

public class Cell {
    private int value;

    public Cell() {
        this.value = 0;
    }

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
