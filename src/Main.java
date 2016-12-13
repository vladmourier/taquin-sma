import ui.Window;

/**
 * Created by Vlad on 12/12/2016.
 */
public class Main {
    public static void main (String[] args){
        Window window = new Window(4, 4);


        Agent a = new Agent();
        a.addObserver(window);

        a.run();
    }
}