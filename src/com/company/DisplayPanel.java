package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DisplayPanel extends JPanel{

    private JTextField floorTextField = new JTextField("Floor: 0");
    private JTextField motorTextField = new JTextField("Action: Stop");
    private JTextField doorTextField = new JTextField("Door: close");

    private LinkedList<JPanel> floorRectList = new LinkedList<>();

    private JPanel textFieldPanel = new JPanel();

    public DisplayPanel(int floorNumber){
        GridLayout textFieldLayout = new GridLayout(3,1);
        motorTextField.setForeground(Color.GREEN);
        doorTextField.setForeground(Color.red);

        textFieldPanel.setLayout(textFieldLayout);
        textFieldPanel.add(floorTextField);
        textFieldPanel.add(motorTextField);
        textFieldPanel.add(doorTextField);

        JPanel elevatorPanel = new JPanel();
        elevatorPanel.setLayout(new GridLayout(6,1));

        for (int i = 0; i < floorNumber; i++) {
            JPanel floorPanel = new JPanel();
            floorRectList.add(floorPanel);
            if(i==floorNumber-1)floorPanel.setBackground(Color.BLACK);
            else floorPanel.setBackground(Color.WHITE);
            elevatorPanel.add(floorPanel);
        }

        setLayout(new GridLayout(1,2));
        add(textFieldPanel);
        add(elevatorPanel);
    }

    public JTextField getFloorTextField() {
        return floorTextField;
    }

    public JTextField getMotorTextField() {
        return motorTextField;
    }

    public JTextField getDoorTextField() {
        return doorTextField;
    }

    public LinkedList<JPanel> getFloorRectList() {
        return floorRectList;
    }
}
