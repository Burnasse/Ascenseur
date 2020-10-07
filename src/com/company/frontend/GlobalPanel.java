package com.company.frontend;

import com.company.backend.Operator;

import javax.swing.*;
import java.awt.*;

public class GlobalPanel extends JPanel {

    public static final int FLOOR_NUMBER = 6;

    public static DisplayPanel displayPanel = new DisplayPanel(FLOOR_NUMBER);
    public static ControlPanel controlPanel = new ControlPanel(FLOOR_NUMBER,
            displayPanel.getFloorTextField(),
            displayPanel.getMotorTextField(),
            displayPanel.getDoorTextField(),
            displayPanel.getFloorRectList(),
            new Operator(FLOOR_NUMBER));

    public GlobalPanel(){
        setLayout(new GridLayout(2,1));

        add(displayPanel);
        add(controlPanel);
    }
}
