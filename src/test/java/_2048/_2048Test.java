package _2048;

import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class _2048Test {

    _2048 game = new _2048();

    _2048Test() throws IOException {
    }


    @org.junit.jupiter.api.Test
    void getHighTile() {
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(32); testBoard.add(4); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(8); testBoard.add(0);
        testBoard.add(0); testBoard.add(16); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        assertEquals(32, game.getHighTile());
    }

    @org.junit.jupiter.api.Test
    void previousMove() {
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(32); testBoard.add(4); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(8); testBoard.add(0);
        testBoard.add(0); testBoard.add(16); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        Tile[][] savedBoard = game.getBoard();
        game.left(true);
        game.right(true);
        game.previousMove();
        Tile[][] previousBoard = game.getBoard();
        Assert.assertTrue(game.boardSameCheck(savedBoard,previousBoard));
    }

    @org.junit.jupiter.api.Test
    public void testCheckCanMove(){
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(32); testBoard.add(4); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(8); testBoard.add(0);
        testBoard.add(0); testBoard.add(16); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        Assert.assertTrue(game.canMove());
    }

    @org.junit.jupiter.api.Test
    public void testCheckCannotMove(){
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(2); testBoard.add(4); testBoard.add(8); testBoard.add(16);
        testBoard.add(4); testBoard.add(2); testBoard.add(32); testBoard.add(8);
        testBoard.add(2); testBoard.add(4); testBoard.add(8); testBoard.add(16);
        testBoard.add(4); testBoard.add(2); testBoard.add(32); testBoard.add(8);
        game.setBoard(testBoard);
        Assert.assertFalse(game.canMove());
    }

    @org.junit.jupiter.api.Test
    public void testScore(){
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(2); testBoard.add(0); testBoard.add(0); testBoard.add(2);
        testBoard.add(4); testBoard.add(4); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        game.left(false);
        Assert.assertEquals(12, game.getScore());
    }

//    @org.junit.jupiter.api.Test
//    public void testSave() throws IOException {
//        List<Integer> testBoard = new ArrayList<>();
//        testBoard.add(2); testBoard.add(0); testBoard.add(2); testBoard.add(0);
//        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
//        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(2);
//        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
//        game.setBoard(testBoard);
//        game.setScore(0);
//        game.saveGameFile();
//        Assert.assertEquals("2,0,2,0,0,0,0,0,0,0,0,0,2,0,0,0,0", game.getSavedOutput());
//    }

    @org.junit.jupiter.api.Test
    public void testSaveAndReload() throws IOException {
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(2); testBoard.add(0); testBoard.add(0); testBoard.add(2);
        testBoard.add(4); testBoard.add(4); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        Tile[][] savedBoard = game.getBoard();
        game.saveGameFile();
        game.reset();
        game.left(false);
        game.loadGameFile();
        Tile[][] reloadBoard = game.getBoard();
        Assert.assertTrue(game.boardSameCheck(savedBoard,reloadBoard));
    }

    @org.junit.jupiter.api.Test
    public void testEmptySaved() throws IOException {
        game.setSavedOutput("");
        game.saveGameFile();
        game.loadGameFile();
        Tile[][] gameBoard = game.getBoard();
        _2048 defaultGame = new _2048();
        Tile[][] defaultGameBoard = defaultGame.getBoard();
        Assert.assertTrue(game.boardSameCheck(gameBoard,defaultGameBoard));
    }

    @org.junit.jupiter.api.Test
    public void testEmptyWrongInput() throws IOException {
        game.setSavedOutput("wrong");
        game.saveGameFile();
        game.loadGameFile();
        Tile[][] gameBoard = game.getBoard();
        _2048 defaultGame = new _2048();
        Tile[][] defaultGameBoard = defaultGame.getBoard();
        Assert.assertTrue(game.boardSameCheck(gameBoard,defaultGameBoard));
    }

    @org.junit.jupiter.api.Test
    public void testEmptyIntegerInputOdd() throws IOException {
        // Should restart board game as all tiles should be divisible by 2
        game.setSavedOutput("2,0,2,0,14,0,0,0,3,0,0,0,0,0,0,0,0");
        game.saveGameFile();
        game.loadGameFile();
        Tile[][] gameBoard = game.getBoard();
        _2048 defaultGame = new _2048();
        Tile[][] defaultGameBoard = defaultGame.getBoard();
        Assert.assertTrue(game.boardSameCheck(gameBoard,defaultGameBoard));
    }

    @org.junit.jupiter.api.Test
    public void testMoveRight() throws IOException {
        // Should restart board game as all tiles should be divisible by 2
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(2);
        testBoard.add(2); testBoard.add(2); testBoard.add(2); testBoard.add(0);
        testBoard.add(2); testBoard.add(0); testBoard.add(0); testBoard.add(2);
        testBoard.add(2); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        game.right(false);
        Tile[][] testTile = game.getBoard();

        _2048 defaultGame = new _2048();
        List<Integer> expectedBoard = new ArrayList<>();
        expectedBoard.add(0); expectedBoard.add(0); expectedBoard.add(0); expectedBoard.add(0);
        expectedBoard.add(0); expectedBoard.add(0); expectedBoard.add(0); expectedBoard.add(0);
        expectedBoard.add(0); expectedBoard.add(2); expectedBoard.add(0); expectedBoard.add(0);
        expectedBoard.add(2); expectedBoard.add(4); expectedBoard.add(4); expectedBoard.add(2);
        defaultGame.setBoard(expectedBoard);
        Tile[][] expectedTile = defaultGame.getBoard();
        Assert.assertTrue(game.boardSameCheck(testTile,expectedTile));
    }

    @org.junit.jupiter.api.Test
    public void testMoveRightDouble() throws IOException {
        // Should restart board game as all tiles should be divisible by 2
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(2); testBoard.add(2); testBoard.add(2); testBoard.add(2);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        game.down(false);
        Tile[][] testTileDown1 = game.getBoard();
        game.down(false);
        Tile[][] testTileDown2 = game.getBoard();

        _2048 defaultGame = new _2048();

        List<Integer> down1Board = new ArrayList<>();
        down1Board.add(4); down1Board.add(4); down1Board.add(0); down1Board.add(0);
        down1Board.add(0); down1Board.add(0); down1Board.add(0); down1Board.add(0);
        down1Board.add(0); down1Board.add(0); down1Board.add(0); down1Board.add(0);
        down1Board.add(0); down1Board.add(0); down1Board.add(0); down1Board.add(0);
        defaultGame.setBoard(down1Board);
        Tile[][] down1Tile = defaultGame.getBoard();

        List<Integer> down2Board = new ArrayList<>();
        down2Board.add(8); down2Board.add(0); down2Board.add(0); down2Board.add(0);
        down2Board.add(0); down2Board.add(0); down2Board.add(0); down2Board.add(0);
        down2Board.add(0); down2Board.add(0); down2Board.add(0); down2Board.add(0);
        down2Board.add(0); down2Board.add(0); down2Board.add(0); down2Board.add(0);
        defaultGame.setBoard(down1Board);
        Tile[][] down2Tile = defaultGame.getBoard();

        Assert.assertTrue(game.boardSameCheck(testTileDown1,down1Tile));
        Assert.assertTrue(game.boardSameCheck(testTileDown2,down2Tile));
    }

    @org.junit.jupiter.api.Test
    public void test2048Reached() throws IOException {
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(2); testBoard.add(0); testBoard.add(0); testBoard.add(2);
        testBoard.add(4); testBoard.add(4); testBoard.add(0); testBoard.add(0);
        testBoard.add(2048); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        _2048.State gameState = game.getGameStatus();
        Assert.assertEquals(gameState, _2048.State.WIN);
    }

}