package taquin;

import model.communication.AgentSocket;
import model.Position;
import model.communication.events.MessageReceivedEvent;
import model.communication.events.MessageReceivedListener;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Vlad on 12/12/2016.
 */
public class Agent extends Observable implements Runnable, MessageReceivedListener {

    private int idAgent;
    private ArrayList<Agent> agents;
    private ArrayList<String> messageQueue = new ArrayList<>();
    private AgentSocket agentSocket;
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
        this.agentSocket = new AgentSocket(idAgent);
        this.agentSocket.addMessageReceivedListener(this);
        //new Thread(agentSocket).start();
    }

    public static int[][] getGrid() {
        return grid;
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
        while(!Taquin.isComplete()){
            setChanged();
            notifyObservers();
        }
    }

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    public void sendMessage(int recipient, String msg) {
        this.agentSocket.sendMessage(recipient, msg);
    }

    @Override
    public void messageReceived(MessageReceivedEvent event) {
        messageQueue.add(event.getSource().getLastReceivedMessage());
    }
}
