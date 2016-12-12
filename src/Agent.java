import java.util.ArrayList;

/**
 * Created by Vlad on 12/12/2016.
 */
public class Agent extends Thread {

    private int id;
    private ArrayList<Agent> agents;
    private Position current;
    private Position goal;
    private static int[] grid;

    public boolean goalAchieved() {
        return current.equals(goal);
    }

    public Agent(int id, Position start, Position goal) {
        this.id = id;
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

    public static void updateGrid(int[] grid) {
        Agent.grid = grid;
    }

    public void run() {

    }
}
