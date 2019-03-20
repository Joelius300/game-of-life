package GameOfLife.Tests;

import GameOfLife.GameOfLife;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGameOfLife {
    public boolean[][] fullGrid;
    public boolean[][] in1;
    public boolean[][] expected1;
    public boolean[][] in2;
    public boolean[][] expected2;
    public boolean[][] in3;
    public boolean[][] expected3;
    public boolean[][] expected4;
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

        in1 = new boolean[][]
                {
                        {false, true, false},
                        {true, true, false},
                        {true, false, true}
                };

        expected1 = new boolean[][]
                {
                        {true, true, false},
                        {true, false, true},
                        {true, false, false}
                };

        in2 = new boolean[][]
                {
                        {false, false, false},
                        {true, true, true},
                        {false, false, false}
                };

        expected2 = new boolean[][]
                {
                        {false, true, false},
                        {false, true, false},
                        {false, true, false}
                };

        in3 = new boolean[][]
                {
                        {false, false, true},
                        {false, true, true},
                        {false, true, false}
                };

        expected3 = new boolean[][]
                {
                        {false, true, true},
                        {false, true, true},
                        {false, true, true}
                };

        expected4 = new boolean[][]
                {
                        {true, false, true},
                        {false, false, false},
                        {true, false, true}
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
        int expected = 4000;
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

    @Test
    public void testGeneration1(){
        boolean[][] actual = gol.getNextGen(in1);
        boolean[][] expected = expected1;
        gol.show(in1);
        gol.show(actual);
        gol.show(expected);
        Assert.assertArrayEquals(actual, expected);
    }

    //    jede lebendige Zelle, die weniger als zwei lebendige Nachbarn hat, stirbt an Einsamkeit
    //    jede lebendige Zelle mit zwei oder drei Nachbarn fühlt sich wohl und lebt weiter
    //    jede tote Zelle mit genau drei lebendigen Nachbarn wird wieder zum Leben erweckt
    @Test
    public void testGeneration2(){
        boolean[][] actual = gol.getNextGen(in2);
        boolean[][] expected = expected2;
        gol.show(in2);
        gol.show(actual);
        gol.show(expected);
        Assert.assertArrayEquals(actual, expected);
    }

    //    jede tote Zelle mit genau drei lebendigen Nachbarn wird wieder zum Leben erweckt
    @Test
    public void testGeneration3(){
        boolean[][] actual = gol.getNextGen(in3);
        boolean[][] expected = expected3;
        gol.show(in3);
        gol.show(actual);
        gol.show(expected);
        Assert.assertArrayEquals(actual, expected);
    }

    //    jede lebendige Zelle mit mehr als drei lebendigen Nachbarn stirbt an Überbevölkerung
    @Test
    public void testGeneration4(){
        boolean[][] actual = gol.getNextGen(fullGrid);
        boolean[][] expected = expected4;
        Assert.assertArrayEquals(actual, expected);
    }
}
