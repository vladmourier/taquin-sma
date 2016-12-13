package taquin;

import model.Position;
import ui.Window;

import java.util.ArrayList;

/**
 * Created by Bastien on 12/12/2016.
 */
public class Taquin {

    public static void main(String[] args) {
        int nbAgents = 6;
        boolean collision = true;
        int n = 5;
        int[][] grid = new int[n][n];
        Window window = new Window(n,n);

        for (int j=0; j<n; j++) {
            for (int k=0; k<n; k++) {
                grid[j][k] = 0;
            }
        }
        ArrayList<Agent> agents = new ArrayList<Agent>();
        int i = 0;
        while (i <= nbAgents) {
            collision = false;
            int x = (int)(Math.random() * n);
            int y = (int)(Math.random() * n);
            int xGoal = (int)(Math.random() * n);
            int yGoal = (int)(Math.random() * n);
            Position start = new Position(x,y);
            for (Agent a : agents) {
                if (start.equals(a.getCurrent()))  {
                    collision = true;
                }
            }
            if (!collision) {
                Agent agent = new Agent(i, start, new Position(xGoal, yGoal));
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
    }
}
