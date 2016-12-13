package model;

/**
 * Created by Bastien on 12/12/2016.
 */
public class Position {

    private int x;
    private int y;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position position) {
        return (this.getX() == position.getX() && this.getY() == position.getY());
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {

    }

    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}