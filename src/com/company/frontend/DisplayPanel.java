package com.company.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * The type Display panel display the information about the engine, door, floor and elevator.
 */
public class DisplayPanel extends JPanel{

    private JTextField floorTextField = new JTextField("Floor: 0");
    private JTextField motorTextField = new JTextField("Action: Stop");
    private JTextField doorTextField = new JTextField("Door: closed");

    private LinkedList<JPanel> floorRectList = new LinkedList<>();

    private JPanel textFieldPanel = new JPanel();

    /**
     * Instantiates a new Display panel.
     *
     * @param floorNumber the floor number
     */
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

        for (int i = floorNumber; i > 0; i--) {
            JPanel floorPanel = new JPanel();
            floorRectList.add(floorPanel);
            if(i==1)floorPanel.setBackground(Color.BLACK);
            else floorPanel.setBackground(Color.WHITE);
            elevatorPanel.add(floorPanel);
        }

        setLayout(new GridLayout(1,2));
        add(textFieldPanel);
        add(elevatorPanel);
    }

    /**
     * Gets floor text field.
     *
     * @return the floor text field
     */
    public JTextField getFloorTextField() {
        return floorTextField;
    }

    /**
     * Gets motor text field.
     *
     * @return the motor text field
     */
    public JTextField getMotorTextField() {
        return motorTextField;
    }

    /**
     * Gets door text field.
     *
     * @return the door text field
     */
    public JTextField getDoorTextField() {
        return doorTextField;
    }

    /**
     * Gets floor rect list.
     *
     * @return the floor rect list
     */
    public LinkedList<JPanel> getFloorRectList() {
        return floorRectList;
    }
}
