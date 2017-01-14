package taquin;

import javafx.geometry.Pos;
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
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
                if (j == grid.length - 1) {
                    System.out.println(" ");
                }
            }
        }
    }

    private boolean isSafeDirection(Directions direction) {
        boolean safe = true;
        Position position = getNextPosition(direction);
        if (position.getX() < 0 || position.getX() >= grid.length || position.getY() < 0 || position.getY() >= grid.length) {
            safe = false;
        }
        for (Agent a : agents) {
            if (a.getCurrent().equals(position)) {
                safe = false;
            }
        }
        return safe;
    }

    public boolean move(Directions direction) {
//        try {
//            sleep(100);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
        Position position = getNextPosition(direction);
        boolean safe = isSafeDirection(direction);

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
            boolean hasMoved = false;
            Directions toGo = null;
            Directions[] possibleDir = Directions.getAll();

            if (!messageQueue.isEmpty()) {
                toGo = possibleDir[0];
                int i = 1;
                while (i < possibleDir.length && !isSafeDirection(toGo)) {
                    toGo = possibleDir[i];
                    i++;
                }

                hasMoved = move(toGo);
//                System.out.println(getIdAgent() + "moved following request");
                messageQueue.clear();

            } else if (!getCurrent().equals(goal)) {
                int vector_x = goal.getX() - getCurrent().getX();
                int vector_y = goal.getY() - getCurrent().getY();

                if (vector_x != 0 || vector_y != 0) {
                    if (new Random().nextBoolean() && vector_x != 0) {
                        toGo = vector_x > 0 ? DOWN : UP;
                    } else if (vector_y != 0) {
                        toGo = vector_y > 0 ? RIGHT : LEFT;
                    }
                }
                if (toGo != null) {
                    if (!(hasMoved = move(toGo))) {
                        switch (toGo) {
                            case UP:
                            case DOWN:
                                hasMoved = move(new Random().nextBoolean() ? LEFT : RIGHT);
                                break;
                            case LEFT:
                            case RIGHT:
                                hasMoved = move(new Random().nextBoolean() ? UP : DOWN);
                                break;
                        }
                    }
                }
            }
            if (!hasMoved && !goalAchieved()) {
                if (toGo == null) {
                    toGo = possibleDir[new Random().nextInt(possibleDir.length)];
                }
                Position next = getNextPosition(toGo);
                sendMessage(grid[next.getX()][next.getY()], "MOVE");
            }
        }
        setChanged();
        notifyObservers();
        System.out.println(goalAchieved() ? "Le numéro " + getIdAgent() + " est en place" : "");
        displayGrid();
    }

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    public void sendMessage(int recipient, String msg) {
        if (recipient != this.getIdAgent() && recipient > 0) {
//            System.out.println("Agent :" + getIdAgent() + "request " + recipient + " to move");
            this.agentSocket.sendMessage(recipient, msg);
        }
    }

    Position getNextPosition(Directions d) {
        switch (d) {
            case UP:
                return new Position(Math.max(getCurrent().getX() - 1, 0), getCurrent().getY());
            case DOWN:
                return new Position(Math.min(getCurrent().getX() + 1, grid.length - 1), getCurrent().getY());
            case LEFT:
                return new Position(getCurrent().getX(), Math.max(getCurrent().getY() - 1, 0));
            case RIGHT:
                return new Position(getCurrent().getX(), Math.min(getCurrent().getY() + 1, grid[getCurrent().getX()].length - 1));
        }
        return null;
    }

    public Position getGoal() {
        return goal;
    }

    @Override
    public void messageReceived(MessageReceivedEvent event) {
//        System.out.println("Je suis : " + idAgent + "     Reçu : " + event.getSource().getLastReceivedMessage());
        messageQueue.add(event.getSource().getLastReceivedMessage());
    }
}
