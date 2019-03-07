package GameOfLife.Tests;

import GameOfLife.GameOfLife;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGameOfLife {
    public boolean[][] fullGrid;
    public GameOfLife gol;

    @Before
    public void setup(){
        gol = new GameOfLife();

        fullGrid = new boolean[][]
        {
            {true, true, true},
            {true, true, true},
            {true, true, true}
        };
    }

    @Test
    public void testGetNeighboursMiddle(){
        int actual = gol.getAmountNeighbours(fullGrid, 1, 1);
        int expected = 8;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetNeighboursTopLeft(){
        int actual = gol.getAmountNeighbours(fullGrid, 0, 0);
        int expected = 3;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetNeighboursMiddleRight(){
        int actual = gol.getAmountNeighbours(fullGrid, 2,1);
        int expected = 5;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testInit1(){
        int actual = getAmountAliveCells(gol.init(3, 4, 0.25f));
        int expected = 3;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testInit2(){
        int actual = getAmountAliveCells(gol.init(100, 400, 0.1f));
        int expected = 400;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testInit3(){
        int actual = getAmountAliveCells(gol.init(12, 18, 0.24f));
        int expected = 52;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testInit4(){
        int actual = getAmountAliveCells(gol.init(12, 18, 0.2f));
        int expected = 44;
        Assert.assertEquals(actual, expected);
    }

    private int getAmountAliveCells(boolean[][] world){
        int out = 0;
        for (int y = 0; y < world.length; y++) {
            for (int x = 0; x < world[y].length; x++) {
                if(world[y][x]){
                    out++;
                }
            }
        }

        return out;
    }
}
