package _2048;

public class _2048 {
    private int[][] board;
    private int score;
    private boolean gameStatus;


    public _2048(){
        reset();
    }

    private void reset() {
        board = new int[4][4];
        score = 0;
        gameStatus = false;
    }
}
