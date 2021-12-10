package _2048;

import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        game.left();
        game.right();
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
        game.left();
        Assert.assertEquals(12, game.getScore());
    }

    /*@org.junit.jupiter.api.Test
    public void testSave() throws IOException {
        List<Integer> testBoard = new ArrayList<>();
        testBoard.add(2); testBoard.add(0); testBoard.add(2); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(2);
        testBoard.add(0); testBoard.add(0); testBoard.add(0); testBoard.add(0);
        game.setBoard(testBoard);
        game.setScore(0);
        game.saveGameFile();
        Assert.assertEquals("2,0,2,0,0,0,0,0,0,0,0,0,2,0,0,0,0", game.getSavedOutput());
    }*/

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
        game.left();
        game.loadGameFile();
        Tile[][] reloadBoard = game.getBoard();
        Assert.assertTrue(game.boardSameCheck(savedBoard,reloadBoard));
    }


}