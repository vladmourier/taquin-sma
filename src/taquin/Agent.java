package taquin;

import model.communication.AgentSocket;
import model.Position;
import model.communication.events.MessageReceivedEvent;
import model.communication.events.MessageReceivedListener;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import static java.lang.Thread.sleep;
import static taquin.Directions.*;

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

    public boolean move(Directions direction) {
        try {
            sleep(1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        boolean safe = true;
        Position position = new Position();
        switch(direction) {
            case LEFT:
                position = new Position(getCurrent().getX(), getCurrent().getY()-1);
                break;
            case RIGHT:
                position = new Position(getCurrent().getX(), getCurrent().getY()+1);
                break;
            case UP:
                position = new Position(getCurrent().getX()-1, getCurrent().getY());
                break;
            case DOWN:
                position = new Position(getCurrent().getX()+1, getCurrent().getY());
        }
        if (position.getX() < 0 || position.getX() >= grid.length || position.getY() < 0 || position.getY() >= grid.length) {
            safe = false;
        }
        for (Agent a : agents) {
            if (a.getCurrent().equals(position)) {
                safe = false;
            }
        }
        if (safe) {
            grid[getCurrent().getX()][getCurrent().getY()] = 0;
            grid[position.getX()][position.getY()] = idAgent;
            this.setCurrent(position);
            setChanged();
            notifyObservers();
        }
        return safe;
    }

    public void run() {
        while (!Taquin.isComplete()) {
            Directions toGo = null;
            System.out.println("Je suis l'agent : " + getIdAgent() + " je suis en " + getCurrent() + " et je vais en " + goal);
            if (!getCurrent().equals(goal)) {
                int vector_x = goal.getX() - getCurrent().getX();
                int vector_y = goal.getY() - getCurrent().getY();

                if (vector_x != 0 || vector_y != 0) {
                    if (new Random().nextBoolean()) {
                        toGo = vector_x > 0 ? DOWN : UP;
                    } else {
                        toGo = vector_y > 0 ? RIGHT : LEFT;
                    }
                }
            }
            if (toGo != null)
                move(toGo);
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
