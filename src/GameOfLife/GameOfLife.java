package GameOfLife;

import java.util.Random;

public class GameOfLife {
//    private static int height = 12;
//    private static int width = 12;
    final Random rnd = new Random();

    private boolean[][] startWorld;
//    private float startPercent = 0.4f;

//    public GameOfLife(){}

    public boolean[][] init(int height, int width, float startPercent){
        boolean[][] world = new boolean[height][width];
        for (int i = 0; i < height * width * startPercent; i++) {
            int y = rnd.nextInt(height);
            int x = rnd.nextInt(width);

            if(!world[y][x]){
                world[y][x] = true;
            }else{
                i--;
            }
        }

        return world;
    }

    public void show(boolean[][] world){
        StringBuilder wholeWorld = new StringBuilder();

        for (int y = 0; y < world.length; y++) {
            for (int x = 0; x < world[y].length; x++) {
                wholeWorld.append(world[y][x] ? "X " : ". ");
                //wholeWorld.append(world[y][x] ? "x\t" : " \t");
            }

            wholeWorld.append("\r\n");
        }

        System.out.println(wholeWorld.toString());
    }


//    jede lebendige Zelle, die weniger als zwei lebendige Nachbarn hat, stirbt an Einsamkeit
//    jede lebendige Zelle mit mehr als drei lebendigen Nachbarn stirbt an Überbevölkerung
//    jede lebendige Zelle mit zwei oder drei Nachbarn fühlt sich wohl und lebt weiter
//    jede tote Zelle mit genau drei lebendigen Nachbarn wird wieder zum Leben erweckt

    public boolean[][] getNextGen(boolean[][] world){
        boolean[][] newWorld = new boolean[world.length][world[0].length];

        for (int y = 0; y < newWorld.length; y++) {
            for (int x = 0; x < newWorld[y].length; x++) {
                int livingAround = getAmountNeighbours(world, x, y);

                if(world[y][x]){
                    if(livingAround < 2 || livingAround > 3){
                        newWorld[y][x] = false;
                    }else{
                        newWorld[y][x] = true;
                    }
                }else{
                    if(livingAround == 3 || livingAround == 2){
                        newWorld[y][x] = true;
                    }else{
                        newWorld[y][x] = false;
                    }
                }
            }
        }

        return newWorld;
    }

    public int getAmountNeighbours(boolean[][] world, int xNow, int yNow){
        int out = 0;
        for (int y = yNow-1; y <= yNow+1; y++) {
            for (int x = xNow-1; x <= xNow+1; x++) {
                boolean isCellWeReTalkinAbout = y == yNow && x == y;
                boolean isInOfBound = y >= 0 && y < world.length && x >= 0 && x < world[y].length;
                if(!isInOfBound || isCellWeReTalkinAbout){
                    continue;
                }

                if (world[y][x]) {
                    out++;
                }
            }
        }

        return out;
    }
}
