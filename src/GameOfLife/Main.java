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
    private static int timeout = 250;

    public static void main(String[] args){
        if(args.length == 0){
            world = game.init(50, 100, 0.1f);
        }else{
            try{
                world = game.init(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Float.parseFloat(args[2]));

                try{
                    timeout = Integer.parseInt(args[3]);
                }catch(Exception ex){}
            }catch (Exception e){
                System.out.println("Parameter Fehler! \nSyntax: ..jar HEIGHT WIDTH STARTPERCENT [TIMEOUT]");
                return;
            }
        }

        while(true){
            cycle();
        }
    }

    private static void cycle(){
        game.show(world);
        world = game.getNextGen(world);

        //System.out.println("---------------------------------------------------------------------------------------");
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {

        }
    }
}
