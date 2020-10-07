package com.company.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class FloorButton extends JButton {

    private int floor;

    public FloorButton(int floor){
        this.floor = floor;
        setText(String.valueOf(floor));
    }

    public int getFloor() {
        return floor;
    }
}
