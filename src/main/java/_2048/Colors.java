package _2048;

import java.awt.*;
import java.util.HashMap;

public class Colors {
    private HashMap<Integer, Color> colorHashMap = new HashMap<>();

    public Colors(){
        colorHashMap.put(0, new Color(255,205,204));
        colorHashMap.put(2, new Color(200,255,204));
        colorHashMap.put(4, new Color(150,204,102));
        colorHashMap.put(8, new Color(255,102,102));
        colorHashMap.put(16, new Color(255,153,0));
        colorHashMap.put(32, new Color(250,240,40));
        colorHashMap.put(64, new Color(240,100,200));
        colorHashMap.put(128, new Color(240,50,100));
        colorHashMap.put(256, new Color(51,230,255));
        colorHashMap.put(512, new Color(20,180,220));
        colorHashMap.put(1024, new Color(20,130,200));
        colorHashMap.put(2048, new Color(255,255,255));
    }

    public Color getColorHashMap(int value){
        return colorHashMap.get(value);
    }
}
