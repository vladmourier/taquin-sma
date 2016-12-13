

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Vlad on 12/12/2016.
 */
public class Agent extends Observable implements Runnable {


    @Override
    public void run() {

        setChanged();
        notifyObservers();
    }
}
