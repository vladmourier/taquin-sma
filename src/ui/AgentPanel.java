package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vlad on 19/12/2016.
 */
public class AgentPanel extends JPanel {

    Label goal = new Label("GOAL");
    Label ID = new Label("TEST");


    AgentPanel() {
    }

    public void displayInformations() {
        this.add(goal);
        this.add(ID);
        this.updateUI();
    }

    public void hideInformations() {
        this.remove(goal);
        this.remove(ID);
    }
}
