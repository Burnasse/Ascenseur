package com.company.frontend;

import javax.swing.*;
import java.awt.*;

public class GlobalPanel extends JPanel {

    public static final int FLOOR_NUMBER = 6;

    public GlobalPanel(){
        setLayout(new GridLayout(2,1));

        DisplayPanel displayPanel = new DisplayPanel(FLOOR_NUMBER);
        ControlPanel controlPanel = new ControlPanel(
                FLOOR_NUMBER,
                displayPanel.getFloorTextField(),
                displayPanel.getMotorTextField(),
                displayPanel.getDoorTextField(),
                displayPanel.getFloorRectList());

        add(displayPanel);
        add(controlPanel);
    }
}
