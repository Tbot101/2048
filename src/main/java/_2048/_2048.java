package _2048;

import java.util.*;

public class _2048 {
    private Tile[][] board;
    private int score;
    private LinkedList<Tile[][]> gameState = new LinkedList<>();

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
        System.out.println("PRINT OLDBOARD");
        board = oldBoard;
        print(board);
        System.out.println("PRINT OLDBOARD");
    }

    public void gameStateSave(){
        Tile[][] saveBoard = board.clone();
        gameState.add(saveBoard);
    }

    public static void main(String args[]){
        _2048 game = new _2048();
        game.print();
        game.addTile();
        game.gameStateSave();
        game.print();
        game.previousMove();
        game.print();
    }
}
