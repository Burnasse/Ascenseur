package com.company.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class FloorButton extends JButton {

    private int floor;

    public FloorButton(JTextField textField, LinkedList<JPanel> elevatorPanels, int floor){
        this.floor = floor;

        setText(String.valueOf(floor));
        addActionListener( e -> {
            textField.setText("FLoor: " + floor);
            for(JPanel current : elevatorPanels){
                if(elevatorPanels.indexOf(current) == floor) current.setBackground(Color.BLACK);
                else current.setBackground(Color.WHITE);
            }
        });

    }

    public int getFloor() {
        return floor;
    }
}
