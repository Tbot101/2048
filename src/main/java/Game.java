import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Runnable game = new _2048.Run2048();
        SwingUtilities.invokeLater(game);
    }
}