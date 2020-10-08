package com.company.backend;

import com.company.frontend.GlobalPanel;

import java.awt.*;

public class FloorButton implements Button {

    private int floor;

    public FloorButton(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
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
