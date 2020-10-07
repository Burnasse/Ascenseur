package com.company.backend;

import com.company.frontend.GlobalPanel;

import javax.swing.*;
import java.awt.*;

public class Cabin {

    private int floor;

    private Direction direction;

    public Cabin() {
        this.floor = 0;
        this.direction = Direction.NONE;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;

        GlobalPanel.displayPanel.getFloorTextField().setText("Floor: " + floor);
        for (JPanel current : GlobalPanel.displayPanel.getFloorRectList()) {
            if (GlobalPanel.displayPanel.getFloorRectList().indexOf(current) == GlobalPanel.FLOOR_NUMBER - (floor + 1))
                current.setBackground(Color.BLACK);
            else current.setBackground(Color.WHITE);
        }

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
