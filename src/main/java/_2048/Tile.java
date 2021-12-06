package _2048;

import java.awt.*;

public class Tile {
    private int value;
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
}
