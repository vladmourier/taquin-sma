import java.util.ArrayList;

/**
 * Created by Bastien on 12/12/2016.
 */
public class Taquin {

    public static void main(String[] args) {
        int nbAgents = 6;
        int n = 5;
        int[] grid;
        ArrayList<Agent> agents = new ArrayList<Agent>();
        for (int i = 0; i <= nbAgents; i++) {
            int x = (int)(Math.random() * n);
            int y = (int)(Math.random() * n);
            int xGoal = (int)(Math.random() * n);
            int yGoal = (int)(Math.random() * n);
            Agent agent = new Agent(i, new Position(x,y), new Position(xGoal, yGoal));
        }
    }
}
