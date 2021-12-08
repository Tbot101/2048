package _2048;

import java.awt.*;

public class Tile {
    private int value;
    private boolean merge;
    private Color tileColor;
    Colors colorMap = new Colors();

    public Tile() {
        this.value = 0;
        setColor();
    }

    public Tile(int value) {
        this.value = value;
        setColor();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        setColor();
    }

    public String toString(){
        return "" + this.value;
    }

    public void setColor(){
        this.tileColor = colorMap.getColorHashMap(this.value);
    }

    public Color getColor(){
        this.setColor();
        return tileColor;
    }

    /*void setMerge(boolean state) {
        merge = state;
    }

    boolean checkMergeState(Tile tile) {
        return !merge && tile != null && !tile.merge && value == tile.getValue();
    }

    int mergeWith(Tile other) {
        if (checkMergeState(other)) {
            value *= 2;
            merge = true;
            return value;
        }
        return -1;
    }*/
}
