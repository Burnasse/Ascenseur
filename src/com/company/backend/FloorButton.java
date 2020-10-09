package com.company.backend;

import com.company.frontend.GlobalPanel;

import java.awt.*;

/**
 * Represents floor button inside elevator.
 */
public class FloorButton implements Button {

    private final int floor;

    /**
     * Instantiates a new Floor button.
     *
     * @param floor the floor
     */
    public FloorButton(int floor) {
        this.floor = floor;
    }

    @Override
    public void displayAction() {
        if(GlobalPanel.controlPanel.getButtonMap().get(String.valueOf(floor)).getBackground() == Color.ORANGE)
            GlobalPanel.controlPanel.getButtonMap().get(String.valueOf(floor)).setBackground(null);
        else
            GlobalPanel.controlPanel.getButtonMap().get(String.valueOf(floor)).setBackground(Color.ORANGE);
    }
}
