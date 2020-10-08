package com.company.backend;

import com.company.frontend.GlobalPanel;

import java.awt.*;

public class Door {

    private boolean isOpen = false;

    public void toOpen(){
        isOpen=true;
        GlobalPanel.displayPanel.getDoorTextField().setText("Door: Opened");
        GlobalPanel.displayPanel.getDoorTextField().setForeground(Color.GREEN);
    }

    public void toClose(){
        isOpen = false;
        GlobalPanel.displayPanel.getDoorTextField().setText("Door: Closed");
        GlobalPanel.displayPanel.getDoorTextField().setForeground(Color.RED);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
