package taquin;

/**
 * Created by Bastien on 13/12/2016.
 */
public enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Directions[] getAll(){
        return new Directions[]{UP, DOWN, LEFT, RIGHT};
    }
}
