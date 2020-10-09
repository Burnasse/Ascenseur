package com.company.backend;

import com.company.frontend.GlobalPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The type Cabin.
 */
public class Cabin {

    private int floor;

    private Direction direction;

    /**
     * Instantiates a new Cabin.
     */
    public Cabin() {
        this.floor = 0;
        this.direction = Direction.NONE;
    }

    /**
     * Gets floor where the elevator is.
     *
     * @return the floor.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets floor where the elevator is.
     *
     * @param floor the floor
     */
    public void setFloor(int floor) {
        this.floor = floor;

        GlobalPanel.displayPanel.getFloorTextField().setText("Floor: " + floor);
        for (JPanel current : GlobalPanel.displayPanel.getFloorRectList()) {
            if (GlobalPanel.displayPanel.getFloorRectList().indexOf(current) == GlobalPanel.FLOOR_NUMBER - (floor + 1))
                current.setBackground(Color.BLACK);
            else current.setBackground(Color.WHITE);
        }

    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
