package com.company.backend;

import com.company.frontend.GlobalPanel;

import java.awt.*;

/**
 * The type Door represent the elevator's door.
 */
public class Door {

    private boolean isOpen = false;

    /**
     * Open the door and display the action to the test interface.
     */
    public void toOpen(){
        isOpen=true;
        GlobalPanel.displayPanel.getDoorTextField().setText("Door: Opened");
        GlobalPanel.displayPanel.getDoorTextField().setForeground(Color.GREEN);
    }

    /**
     * Close the door and display the action to the test interface.
     */
    public void toClose(){
        isOpen = false;
        GlobalPanel.displayPanel.getDoorTextField().setText("Door: Closed");
        GlobalPanel.displayPanel.getDoorTextField().setForeground(Color.RED);
    }

    /**
     * Return true if the door is open, else return false.
     *
     * @return the boolean
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Sets the door open (true) or close (false).
     *
     * @param open the boolean
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
