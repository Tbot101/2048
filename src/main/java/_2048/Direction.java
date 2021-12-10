package _2048;
import _2048.Tile;

public class Direction {
    private Tile[][] copyBoard;
    private int score;

    public Direction(Tile[][] board, int scoreGame) {
        score = scoreGame;
        copyBoard = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyBoard[i][j] = new Tile(board[i][j].getValue());
            }
        }
    }

    public void spin(int num) {
        for (int x = 0; x < num; x++) {
            Tile[][] rotated = new Tile[4][4];
            for (int a = 0; a < rotated.length; a++) {
                for (int b = 0; b < rotated[0].length; b++) {
                    rotated[b][3 - a] = new Tile(copyBoard[a][b].getValue());
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyBoard[i][j].setValue(rotated[i][j].getValue());
                }
            }
        }
    }

    public void move() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                if (copyBoard[i][j].getValue() == 0) {
                    int num = 1;
                    while (j - num >= 0 && copyBoard[i][j - num].getValue() == 0) {
                        num++;
                    }
                    if (j - num >= 0) {
                        copyBoard[i][j].setValue(copyBoard[i][j - num].getValue());
                        copyBoard[i][j - num].setValue(0);
                    }
                }
            }
        }
    }

    public void add() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (copyBoard[i][j].getValue() == copyBoard[i][j - 1].getValue()) {
                    int newTileVal = copyBoard[i][j].getValue() + copyBoard[i][j - 1].getValue();
                    score += newTileVal;
                    copyBoard[i][j].setValue(
                            newTileVal
                    );
                    copyBoard[i][j - 1].setValue(0);
                }
            }
        }
    }

    public void change(Tile[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int tileValue = copyBoard[i][j].getValue();
                board[i][j].setValue(tileValue);
            }
        }
    }

    public int getScore(){
        return score;
    }

    public Tile[][] getCopyBoard(){
        return copyBoard;
    }
}