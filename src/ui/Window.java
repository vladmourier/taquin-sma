package ui;

import taquin.Agent;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Created by Vlad on 12/12/2016.
 */
public class Window extends JFrame implements Observer {
    private String title = "Taquin autonome !";
    private final int xSize = 600;
    private final int ySize = 600;

    private AgentPanel[][] Grid;


    public Window(int gridSizeVertical, int gridSizeHorizontal) {
        super();
        this.setSize(xSize, ySize);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.Grid = new AgentPanel[gridSizeHorizontal][gridSizeVertical];

        this.setLayout(new GridLayout(gridSizeVertical, gridSizeHorizontal));

        int k = 1;
        for (int i = 0; i < gridSizeHorizontal; i++) {
            for (int j = 0; j < gridSizeVertical; j++) {
                AgentPanel p = new AgentPanel();
                p.setBackground(getColorAccordingToId(0));
                Grid[i][j] = p;
                this.add(p);
                k++;
            }
        }

        setVisible(true);
    }

    public void drawAgents() {
        int[][] agentGrid = Agent.getGrid();
        int iMax = agentGrid.length;
        for (int i = 0; i < iMax; i++) {
            int jMax = agentGrid[i].length;
            for (int j = 0; j < jMax; j++) {
                this.Grid[i][j].setBackground(getColorAccordingToId(agentGrid[i][j]));
                if (agentGrid[i][j] != 0) {
                    this.Grid[i][j].displayInformations();
                } else {
                    this.Grid[i][j].hideInformations();
                }
            }
        }
    }

    private static Color getColorAccordingToId(int id) {
        if (id < 63) {
            String[] colors = {
                    "#FFFFFF",
                    "#000000",
                    "#00FF00",
                    "#0000FF",
                    "#FF0000",
                    "#01FFFE",
                    "#FFA6FE",
                    "#FFDB66",
                    "#006401",
                    "#010067",
                    "#95003A",
                    "#007DB5",
                    "#FF00F6",
                    "#FFEEE8",
                    "#774D00",
                    "#90FB92",
                    "#0076FF",
                    "#D5FF00",
                    "#FF937E",
                    "#6A826C",
                    "#FF029D",
                    "#FE8900",
                    "#7A4782",
                    "#7E2DD2",
                    "#85A900",
                    "#FF0056",
                    "#A42400",
                    "#00AE7E",
                    "#683D3B",
                    "#BDC6FF",
                    "#263400",
                    "#BDD393",
                    "#00B917",
                    "#9E008E",
                    "#001544",
                    "#C28C9F",
                    "#FF74A3",
                    "#01D0FF",
                    "#004754",
                    "#E56FFE",
                    "#788231",
                    "#0E4CA1",
                    "#91D0CB",
                    "#BE9970",
                    "#968AE8",
                    "#BB8800",
                    "#43002C",
                    "#DEFF74",
                    "#00FFC6",
                    "#FFE502",
                    "#620E00",
                    "#008F9C",
                    "#98FF52",
                    "#7544B1",
                    "#B500FF",
                    "#00FF78",
                    "#FF6E41",
                    "#005F39",
                    "#6B6882",
                    "#5FAD4E",
                    "#A75740",
                    "#A5FFD2",
                    "#FFB167",
                    "#009BFF"
            };

            String colorStr = colors[id];

            return new Color(
                    Integer.valueOf(colorStr.substring(1, 3), 16),
                    Integer.valueOf(colorStr.substring(3, 5), 16),
                    Integer.valueOf(colorStr.substring(5, 7), 16));
        } else return new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }

    @Override
    public void update(Observable o, Object arg) {
        drawAgents();
    }
}
