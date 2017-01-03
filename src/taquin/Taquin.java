package taquin;

import model.Position;
import ui.Window;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bastien on 12/12/2016.
 */
public class Taquin {
    public static ArrayList<Agent> agents = new ArrayList<Agent>();

    public static void main(String[] args) {
        int nbAgents = 8;
        boolean collision = true;
        int n = 4;
        int[][] grid = new int[n][n];
        Window window = new Window(n,n);

        for (int j=0; j<n; j++) {
            for (int k=0; k<n; k++) {
                grid[j][k] = 0;
            }
        }
        int i = 0;
        while (i < nbAgents) {
            collision = false;
            int x = (int)(Math.random() * n);
            int y = (int)(Math.random() * n);
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


    public static boolean isComplete(){
        for(Agent agent : agents){
            if(!agent.goalAchieved()) return false;
        }
        return true;
    }
}
