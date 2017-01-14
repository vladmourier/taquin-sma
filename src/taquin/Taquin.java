package taquin;

import model.Position;
import ui.Homepage;
import ui.Window;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bastien on 12/12/2016.
 */
public class Taquin {
    public static ArrayList<Agent> agents = new ArrayList<Agent>();

    public static void createGame(int GSize, int nbAgents){
        boolean collision = true;
        int[][] grid = new int[GSize][GSize];
        Window window = new Window(GSize,GSize);

        for (int j=0; j<GSize; j++) {
            for (int k=0; k<GSize; k++) {
                grid[j][k] = 0;
            }
        }
        int i = 0;
        while (i < nbAgents) {
            collision = false;
            int x = (int)(Math.random() * GSize);
            int y = (int)(Math.random() * GSize);
            Position start = new Position(x,y);
            for (Agent a : agents) {
                if (start.equals(a.getCurrent()))  {
                    collision = true;
                }
            }
            if (!collision) {
                Agent agent = new Agent(i+1, start, new Position((i/grid.length), i%grid.length));
                agents.add(agent);
                agent.addObserver(window);
                grid[x][y] = agent.getIdAgent();
            } else {
                i--;
            }
            i++;
        }
        for (Agent a : agents) {
            a.setAgents(agents);
        }
        Agent.updateGrid(grid);
        Agent.displayGrid();
        window.drawAgents();

        for (Agent a :agents) {
            new Thread(a).start();
        }
    }

    public static void main(String[] args) {
        Homepage homepage = new Homepage();
        homepage.pack();
        homepage.setVisible(true);
    }


    public static boolean isComplete(){
        for(Agent agent : agents){
            if(!agent.goalAchieved()) return false;
        }
        return true;
    }
}
