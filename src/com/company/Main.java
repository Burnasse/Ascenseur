package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JTextField floorTextField = new JTextField("Floor: 0");
        JTextField motorTextField = new JTextField("Action: Stop");
        JTextField doorTextField = new JTextField("Door: close");
        GridLayout textFieldLayout = new GridLayout(3,1);
        motorTextField.setForeground(Color.GREEN);
        doorTextField.setForeground(Color.red);

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(textFieldLayout);
        textFieldPanel.add(floorTextField);
        textFieldPanel.add(motorTextField);
        textFieldPanel.add(doorTextField);

        Panel panel = new Panel(floorTextField,motorTextField, doorTextField);

        JPanel globalFrame = new JPanel();
        globalFrame.setLayout(new GridLayout(2,1));
        globalFrame.add(textFieldPanel);
        globalFrame.add(panel);

        frame.add(globalFrame);

        frame.setPreferredSize(new Dimension(1000,1000));
        frame.pack();
        frame.setVisible(true);
    }
}
