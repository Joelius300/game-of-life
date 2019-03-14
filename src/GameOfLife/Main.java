package GameOfLife;

import java.util.concurrent.TimeUnit;

public class Main {
    private static final GameOfLife game = new GameOfLife();
    private static boolean[][] startArray =
    {   {true, true, true, false, false, false},
        {true, true, true, false, false, false},
        {true, true, true, false, false, false},
        {true, true, true, false, false, false},
        {true, true, true, false, false, false},
        {true, true, true, false, false, false}
    };

    public static boolean[][] world;

    public static void main(String[] args){
        world = game.init(50, 100, 0.1f);
        while(true){
            cycle();
        }
    }

    private static void cycle(){
        game.show(world);
        world = game.getNextGen(world);

        //System.out.println("---------------------------------------------------------------------------------------");
        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {

        }
    }
}
