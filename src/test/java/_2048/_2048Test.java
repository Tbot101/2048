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
        game.gameStateSave();
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
        Assert.assertEquals(game.getScore(), 12);
    }

    @org.junit.jupiter.api.Test
    void gameValid() {
    }
}