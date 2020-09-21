package com.company;

import javax.swing.*;

public class FloorButton extends JButton {

    private int floor;

    public FloorButton(JTextField textField, int floor){
        this.floor = floor;

        setText(String.valueOf(floor));
        addActionListener( e -> {
            textField.setText("FLoor: " + String.valueOf(floor));
        });

    }

    public int getFloor() {
        return floor;
    }
}
