package com.company.backend;

import com.company.frontend.GlobalPanel;

import java.awt.*;

/**
 * Represents button outside elevator.
 */
public class OutsideButton implements Button {

    private final int floor;
    private final boolean isGoingUp;

    /**
     * Instantiates a new Outside button.
     *
     * @param floor     the floor
     * @param isGoingUp the is going up
     */
    public OutsideButton(int floor, boolean isGoingUp) {
        this.floor = floor;
        this.isGoingUp = isGoingUp;
    }

    @Override
    public void displayAction() {
        if(isGoingUp) {
            if(GlobalPanel.controlPanel.getButtonMap().get("H" + floor).getBackground() == Color.ORANGE)
                GlobalPanel.controlPanel.getButtonMap().get("H" + floor).setBackground(null);
            else
                GlobalPanel.controlPanel.getButtonMap().get(("H" + floor)).setBackground(Color.ORANGE);
        }
        else {
            if(GlobalPanel.controlPanel.getButtonMap().get("D" + floor).getBackground() == Color.ORANGE)
                GlobalPanel.controlPanel.getButtonMap().get("D" + floor).setBackground(null);
            else
                GlobalPanel.controlPanel.getButtonMap().get("D" + floor).setBackground(Color.ORANGE);
        }
    }

}
