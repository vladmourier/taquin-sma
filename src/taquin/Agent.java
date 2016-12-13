package taquin;

import model.Position;

import java.util.ArrayList;

/**
 * Created by Vlad on 12/12/2016.
 */
public class Agent extends Thread {

    private int idAgent;
    private ArrayList<Agent> agents;
    private Position current;
    private Position goal;
    private static int[][] grid;

    public boolean goalAchieved() {
        return current.equals(goal);
    }

    public Agent(int idAgent, Position start, Position goal) {
        this.idAgent = idAgent;
        this.current = start;
        this.goal = goal;
    }


    public void setCurrent(Position current) {
        this.current = current;
    }

    public Position getCurrent() {
        return this.current;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    public static void updateGrid(int[][] grid) {
        Agent.grid = grid;
    }

    public static void displayGrid() {
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid.length; j++) {
                System.out.print(grid[i][j] + " ");
                if(j == grid.length - 1) {
                    System.out.println(" ");
                }
            }
        }
    }

    public void run() {

    }

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }
}
