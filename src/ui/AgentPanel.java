package ui;

import taquin.Agent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vlad on 19/12/2016.
 */
class AgentPanel extends JPanel {

    private final Label goal;
    private final Label ID;
    private final Label InPosition;

    AgentPanel() {
        this.goal = new Label();
        this.ID = new Label();
        this.InPosition = new Label();
        this.setLayout(new GridLayout(3, 3));
        this.add(goal);
        this.add(ID);
        this.add(InPosition);
    }

    public void displayInformations() {
        this.goal.setVisible(true);
        this.ID.setVisible(true);
        this.InPosition.setVisible(true);
        this.updateUI();
    }

    public void hideInformations() {
        this.goal.setVisible(false);
        this.ID.setVisible(false);
        this.InPosition.setVisible(false);
    }

    public void updateInformations(Agent agent) {
        this.goal.setText(agent.getGoal().toString());
        this.ID.setText(agent.getIdAgent() + "");
        this.InPosition.setText(agent.goalAchieved() + "");

        this.goal.setBackground(getBackground());
        this.InPosition.setBackground(agent.goalAchieved() ? Color.white : getBackground());
        this.ID.setBackground(getBackground());
    }
}
