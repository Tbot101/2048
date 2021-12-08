package _2048;

import java.util.*;

public class _2048 {
    private Tile[][] board;
    private int score;
    private Stack<Tile[][]> gameState = new Stack<>();
    private boolean gameStatus;
    private int MAX_INT = 2048;

    public _2048(){
        reset();
    }

    private void reset() {
        board = new Tile[4][4];
        gameState.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        gameStateSave();
        score = 0;
    }

    public Tile[][] getBoard(){
        return board;
    }

    public int getScore(){
        return score;
    }

    public boolean gameStateBool() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == MAX_INT) {
                    return true;
                }
            }
        }
        return false;
    }

    public void print() {
        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){
                int tileVal = board[i][j].getValue();
                System.out.print(tileVal);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }

    public void print(Tile[][] board) {
        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){
                int tileVal = board[i][j].getValue();
                System.out.print(tileVal);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public int getHighTile() {
        int highTile = 0;
        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){
                int tileVal = board[i][j].getValue();
                if(tileVal > highTile){
                    highTile = tileVal;
                }
            }
        }
        return highTile;
    }

    public void addTile() {
        List<Integer[]> list = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){
                if(board[i][j].getValue()==0){
                    Integer[] tilePos = new Integer[2];
                    tilePos[0] = i;
                    tilePos[1] = j;
                    list.add(tilePos);
                }
            }
        }
        Random rand = new Random();
        Integer[] newPos = list.get(rand.nextInt(list.size()));
        float random = new Random().nextInt(10);
        int newTileVal = random > 8 ? 4 : 2;
        board[newPos[0]][newPos[1]] = new Tile(newTileVal);
    }

    public void previousMove(){
        gameState.pop();
        Tile[][] oldBoard = gameState.pop();
        board = oldBoard;
        System.out.println("PRINT OLDBOARD");
    }

    public void gameStateSave(){
        Tile[][] copy = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = new Tile(board[i][j].getValue());
            }
        }
        gameState.push(copy);
    }

    public void gameValid(){

    }

    public void move(){

    }


    // move logic for the board when the move up command is pressed.
    public void up() {
        Move m = new Move(board);
        m.rotate(1);
        m.shift();
        m.compare();
        m.shift();
        m.rotate(3);
        m.applyData(board);
        addTile();
    }

    // move logic for the board when the move down command is pressed.
    public void down() {
        Move m = new Move(board);
        m.rotate(3);
        m.shift();
        m.compare();
        m.shift();
        m.rotate(1);
        m.applyData(board);
        addTile();
    }

    // move logic for the board when the move left command is pressed.
    public void left() {
        Move m = new Move(board);
        m.rotate(2);
        m.shift();
        m.compare();
        m.shift();
        m.rotate(2);
        m.applyData(board);
        addTile();
    }

    // move logic for the board when the move right command is pressed.
    public void right() {
        Move m = new Move(board);
        m.shift();
        m.compare();
        m.shift();
        m.applyData(board);
        addTile();
    }

    // holds the logic/helper classes for the previous move methods
    // board is processed as if the right key is always pressed
    // if a different direction key is pressed, the board is rotated to the
    // appropriate state and then back
    private class Move {
        private Tile[][] copy;

        public Move(Tile[][] blocks) {
            copy = new Tile[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copy[i][j] = new Tile(blocks[i][j].getValue());
                }
            }
        }

        public void shift() {
            for (int row = 0; row < 4; row++) {
                for (int col = 3; col >= 0; col--) {
                    if (copy[row][col].getValue() == 0) {
                        int i = 1;
                        while (col - i >= 0 && copy[row][col - i].getValue() == 0) {
                            i++;
                        }
                        if (col - i >= 0) {
                            copy[row][col].setValue(copy[row][col - i].getValue());
                            copy[row][col - i].setValue(0);
                        }
                    }
                }
            }
        }

        public void compare() {
            for (int row = 0; row < 4; row++) {
                for (int col = 3; col > 0; col--) {
                    if (copy[row][col].getValue() == copy[row][col - 1].getValue()) {
                        copy[row][col].setValue(
                                copy[row][col].getValue() + copy[row][col - 1].getValue()
                        );
                        copy[row][col - 1].setValue(0);
                    }
                }
            }
        }

        public void rotate(int times) {
            for (int t = 0; t < times; t++) {
                Tile[][] rot = new Tile[4][4];
                for (int y = 0; y < rot.length; y++) {
                    for (int x = 0; x < rot[0].length; x++) {
                        rot[x][3 - y] = new Tile(copy[y][x].getValue());
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        copy[i][j].setValue(rot[i][j].getValue());
                    }
                }
            }
        }

        public void applyData(Tile[][] blocks) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    blocks[i][j].setValue(copy[i][j].getValue());
                }
            }
        }
    }


    public static void main(String args[]){
        _2048 game = new _2048();
        game.print();
        game.addTile();

        game.gameStateSave();
        game.print();
        game.left();
        game.gameStateSave();

        game.print();
        game.right();
        game.gameStateSave();

        game.print();
        game.previousMove();
        game.print();

    }
}
