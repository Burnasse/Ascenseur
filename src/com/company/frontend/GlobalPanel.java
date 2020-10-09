package com.company.frontend;

import com.company.backend.Operator;

import javax.swing.*;
import java.awt.*;

/**
 * The type Global panel is the container for display panel and control panel.
 */
public class GlobalPanel extends JPanel {

    /**
     * The constant FLOOR_NUMBER.
     */
    public static final int FLOOR_NUMBER = 6;

    /**
     * The constant displayPanel.
     */
    public static DisplayPanel displayPanel = new DisplayPanel(FLOOR_NUMBER);

    /**
     * The constant controlPanel.
     */
    public static ControlPanel controlPanel = new ControlPanel(FLOOR_NUMBER, new Operator(FLOOR_NUMBER));

    /**
     * Instantiates a new Global panel.
     */
    public GlobalPanel(){
        setLayout(new GridLayout(2,1));
        add(displayPanel);
        add(controlPanel);
    }
}
