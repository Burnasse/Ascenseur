package com.company.backend;

import com.company.frontend.GlobalPanel;

import java.awt.*;

public class OutsideButton implements Button {

    private int floor;
    private boolean isGoingUp;

    public OutsideButton(int floor, boolean isGoingUp) {
        this.floor = floor;
        this.isGoingUp = isGoingUp;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isGoingUp() {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp) {
        isGoingUp = goingUp;
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
