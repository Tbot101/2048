package _2048;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class _2048 {
    private Tile[][] board;
    private int score;
    private int highTile;
    private Stack<Tile[][]> gameState = new Stack<>();
    private Stack<Integer> gameScore = new Stack<>();
    private List<String> saveGame = new ArrayList<>();
    static final String PATH_TO_SAVED_GAMES = "files/saved_game.txt";
    private String savedOutput = "";

    enum State {
        RUN, WIN, LOSE
    }
    private State gameStatus;

    private int MAX_INT = 2048;

    public _2048() throws IOException {
        reset();
    }

    public void reset() {
        if (gameStatus != State.RUN) {
            board = new Tile[4][4];
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

    // For Testing
    public void addTile(int i, int j, int value) {
        board[i][j] = new Tile(value);
    }

    // For Testing
    public void setScore(int value) {
        score = value;
        gameScore.push(score);
    }

    // For Testing
    public String getSavedOutput() {
        return savedOutput;
    }

    public void previousMove(){
        if(gameState.size()!=0){
            gameState.pop();
            gameScore.pop();
            Tile[][] oldBoard = gameState.pop();
            Integer oldScore = gameScore.pop();
            board = oldBoard;
            score = oldScore;
        }
    }

    public void gameStateSave(){
        Tile[][] copyBoard = new Tile[4][4];
        Integer copyScore = score;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyBoard[i][j] = new Tile(board[i][j].getValue());
            }
        }
        gameState.push(copyBoard);
        gameScore.push(copyScore);
    }

    public int gameStateLength(){
        return gameState.size();
    }

    public void setBoard(List<Integer> values){
        if(values.size() == 16){
            int counter = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    board[i][j].setValue(values.get(counter));
                    counter ++;
                }
            }
        }
    }

    public void saveGameBoard(){
        Stack<Tile[][]> gameStateClone = (Stack<Tile[][]>) gameState.clone();
        Stack<Integer> gameScoreClone = (Stack<Integer>) gameScore.clone();
        List<Integer> state = new ArrayList<>();
        Tile[][] stateTile = gameStateClone.pop();
        int score;
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                state.add(stateTile[i][j].getValue());
                System.out.println(stateTile[i][j].getValue());
            }
        }
        score = gameScoreClone.pop();
        savedOutput = "";
        for(int j = state.size()-1;j>=0;j--){
            savedOutput += state.get(j) + ",";
        }
        savedOutput += Integer.toString(score);
        }

    public void writeStringsToFile(
            String filePath
    ) {
        File file = Paths.get(filePath).toFile();
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);
            saveGameBoard();
            String output = savedOutput;
            bw.write(output, 0, output.length());
            bw.newLine();
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
        writeStringsToFile(PATH_TO_SAVED_GAMES);
    }

    // Parses through the saved data in a systemic manner
    static String extractColumn(String csvLine, int csvColumn) {
        if (csvLine == null) {
            return null;
        }
        String[] splitString = csvLine.split(",", -1);
        if (splitString.length > csvColumn) {
            String extractString = splitString[csvColumn];
            return extractString;
        }
        return null;
    }

    // Loads the saved game data onto the grid
    public void loadGameFile() throws IOException {
        BufferedReader reader = null;
        try{
            String data = "";
            reader = new BufferedReader(new FileReader(PATH_TO_SAVED_GAMES));
            data += reader.readLine();

            if(data == ""){
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        board[i][j] = new Tile();
                    }
                }
                addTile();
                addTile();
                score = 0;
                gameStateSave();
                saveGameFile();
            }
            System.out.println(data);


            int tileCounter = 0;
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    board[a][b] = new Tile(Integer.parseInt(extractColumn(data,tileCounter)));
                    tileCounter+=1;
                }
            }
            int scoreGame = Integer.parseInt(String.valueOf(extractColumn(data,tileCounter)));
            score = scoreGame;
            gameState.clear();
            gameScore.clear();
            gameState.push(board);
            gameScore.push(scoreGame);


        } catch (FileNotFoundException e) {
            File fileCreate = new File(PATH_TO_SAVED_GAMES);
            fileCreate.createNewFile();
            reset();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    // Returns the status of the Game
    public void getGameStatus(){
        gameStatus = State.RUN;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == MAX_INT) {
                    gameStatus = State.WIN;
                }
            }
        }
        if(!canMove()){
            gameStatus = State.LOSE;
        }
    }
    // Checks if it is possible for further moves to occur
    public boolean canMove(){
        Tile[][] copyBoard;
        copyBoard = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Integer tileVal = board[i][j].getValue();
                copyBoard[i][j] = new Tile();
            }
        }
        int notPossibleMoves = 0;
        boolean canMove = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Integer tileVal = copyBoard[i][j].getValue();
                    if(i==0 && j == 0){
                        if(tileVal != copyBoard[i+1][j].getValue()
                                && tileVal != copyBoard[i][j+1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if((i==1 || i==2) && j == 0){
                        if(tileVal != copyBoard[i+1][j].getValue()
                            && tileVal != copyBoard[i-1][j].getValue()
                            && tileVal != copyBoard[i][j+1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if(i==3 && j == 0){
                        if(tileVal != copyBoard[i-1][j].getValue()
                                && tileVal != copyBoard[i][j+1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if(i== 0 && (j == 1 || j==2)){
                        if(tileVal != copyBoard[i+1][j].getValue()
                                && tileVal != copyBoard[i][j-1].getValue()
                                && tileVal != copyBoard[i][j+1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if(i== 0 && j == 3){
                        if(tileVal != copyBoard[i+1][j].getValue()
                                && tileVal != copyBoard[i][j-1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if((i== 1 || i == 2) && (j == 1 || j==2)){
                        if(tileVal != copyBoard[i+1][j].getValue()
                                && tileVal != copyBoard[i-1][j].getValue()
                                && tileVal != copyBoard[i][j-1].getValue()
                                && tileVal != copyBoard[i][j+1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if((i== 1 || i == 2) && j == 3){
                        if(tileVal != copyBoard[i+1][j].getValue()
                                && tileVal != copyBoard[i-1][j].getValue()
                                && tileVal != copyBoard[i][j-1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if(i== 3 && j == 3){
                        if(tileVal != copyBoard[i-1][j].getValue()
                                && tileVal != copyBoard[i][j-1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
                    if(i== 3 && (j == 1 || j == 2)){
                        if(tileVal != copyBoard[i-1][j].getValue()
                                && tileVal != copyBoard[i][j+1].getValue()
                                && tileVal != copyBoard[i][j-1].getValue()){
                            notPossibleMoves ++;
                        }
                    }
            }
        }
        if(notPossibleMoves == 16){
            canMove = false;
        }
        return canMove;
    }

    public boolean boardSameCheck(Tile[][] board, Tile[][] newBoard){
        boolean notSame = true;
        int sameTiles = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Integer tileBoard = board[i][j].getValue();
                Integer tileNewBoard = newBoard[i][j].getValue();
                if(tileBoard ==  tileNewBoard){
                    sameTiles ++;
                }
            }
        }
        if(sameTiles == 16){
            notSame = false;
        }
        return notSame;
    }

    public void right() {
        boolean same = false;
        Direction newB = new Direction(board, score);
        newB.move();
        newB.add();
        newB.move();
        if(boardSameCheck(board, newB.getCopyBoard())){
            same = true;
        }
        newB.change(board);
        score = newB.getScore();
        if(same){
            addTile();
            gameStateSave();
            saveGameFile();
            getGameStatus();
        }
    }

    public void left() {
        boolean same = false;
        Direction newB = new Direction(board, score);
        newB.spin(2);
        newB.move();
        newB.add();
        newB.move();
        newB.spin(2);
        if(boardSameCheck(board, newB.getCopyBoard())){
            same = true;
        }
        newB.change(board);
        score = newB.getScore();
        if(same){
            addTile();
            gameStateSave();
            saveGameFile();
            getGameStatus();
        }
    }

    public void up() {
        boolean same = false;
        Direction newB = new Direction(board, score);
        newB.spin(1);
        newB.move();
        newB.add();
        newB.move();
        newB.spin(3);
        if(boardSameCheck(board, newB.getCopyBoard())){
            same = true;
        }
        newB.change(board);
        score = newB.getScore();
        if(same){
            addTile();
            gameStateSave();
            saveGameFile();
            getGameStatus();
        }
    }

    public void down() {
        boolean same = false;
        Direction newB = new Direction(board, score);
        newB.spin(3);
        newB.move();
        newB.add();
        newB.move();
        newB.spin(1);
        if(boardSameCheck(board, newB.getCopyBoard())){
            same = true;
        }
        newB.change(board);
        score = newB.getScore();
        if(same){
            addTile();
            gameStateSave();
            saveGameFile();
            getGameStatus();
        }
    }


    public static void main(String args[]) throws IOException {
        _2048 game = new _2048();
//        game.print();
//
//        game.left();
//        game.print();
//        game.gameStateToString();
//
//        game.right();
//        game.print();
//        game.gameStateToString();
//
//        game.up();
//        game.up();
//
//        game.down();

    }
}
