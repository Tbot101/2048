package _2048;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class _2048 {
    private Tile[][] board;
    private int score;
    private int highTile;
    private Stack<Tile[][]> gameState = new Stack<>();
    private Stack<Integer> gameScore = new Stack<>();
    private Stack<Integer> gameHighTile = new Stack<>();
    private List<String> saveGame = new ArrayList<>();
    static final String PATH_TO_SAVED_GAMES = "files/saved_game.txt";

    private boolean gameStatus;
    private int MAX_INT = 2048;

    public _2048(){
        reset();
    }

    public void reset() {
        board = new Tile[4][4];
        gameStatus = true;
        gameState.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        score = 0;
        highTile = getHighTile();
        gameStateSave();
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
        System.out.println("Score: "+ score);
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
        System.out.println("Score: "+ score);
    }

    public String toString(Tile[][] board){
        String boardString = "";
        for(int i = 0; i<4; i++){
            for(int j=0; j<4;j++){
                int tileVal = board[i][j].getValue();
                boardString += tileVal;
            }
            boardString += "\n";
        }
        System.out.println(boardString);
        return boardString;
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
        gameScore.pop();
        gameHighTile.pop();
        Tile[][] oldBoard = gameState.pop();
        Integer oldScore = gameScore.pop();
        Integer oldHighTile = gameHighTile.pop();
        board = oldBoard;
        score = oldScore;
        highTile = oldHighTile;
    }

    public void gameStateSave(){
        Tile[][] copyBoard = new Tile[4][4];
        Integer copyScore = score;
        Integer copyHighTile = highTile;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyBoard[i][j] = new Tile(board[i][j].getValue());
            }
        }
        gameState.push(copyBoard);
        gameScore.push(copyScore);
        gameHighTile.push(copyHighTile);
    }

    public int gameStateLength(){
        return gameState.size();
    }

    public List<String> gameStateToString(){
        String gameStateString = gameState.toString();
        String gameScoreString = gameScore.toString();
        String gameHighTileString = gameHighTile.toString();

        List<String> fullGameState = new ArrayList<>();
        fullGameState.add(gameStateString);
        fullGameState.add(gameScoreString);
        fullGameState.add(gameHighTileString);

        saveGame = fullGameState;

        return fullGameState;
    }

    public void writeStringsToFile(
            List<String> stringsToWrite, String filePath,
            boolean append
    ) {
        File file = Paths.get(filePath).toFile();
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);
            for (String string : stringsToWrite) {
                bw.write(string, 0, string.length());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (Exception e) {
                System.out.println("Error in closing the BufferedWriter" + e);
            }
        }
    }

    public void saveGameFile(){

    }


    public boolean getGameStatus(){
        return gameStatus;
    }

    public void canMove(){

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
        gameStateSave();
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
        gameStateSave();
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
        gameStateSave();
    }

    // move logic for the board when the move right command is pressed.
    public void right() {
        Move m = new Move(board);
        m.shift();
        m.compare();
        m.shift();
        m.applyData(board);
        addTile();
        gameStateSave();
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
                        int newTileVal = copy[row][col].getValue() + copy[row][col - 1].getValue();
                        score += newTileVal;
                        copy[row][col].setValue(
                                newTileVal
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
            int highestTile = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int tileValue = copy[i][j].getValue();
                    blocks[i][j].setValue(tileValue);
                    if(tileValue > highTile){
                        highTile = tileValue;
                    }
                }
            }
        }
    }


    public static void main(String args[]){
        _2048 game = new _2048();
        game.print();

        game.left();
        game.print();
        game.gameStateToString();

        game.right();
        game.print();
        game.gameStateToString();

        game.up();
        game.up();

        game.down();

//        game.toString(game.board);
    }
}
