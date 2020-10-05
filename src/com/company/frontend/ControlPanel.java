package com.company.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class ControlPanel extends JPanel{

    private JPanel elevatorPanel = new JPanel();
    private JPanel outDoorPanel = new JPanel();

    public ControlPanel(int floorNumber, JTextField floorTextField, JTextField motorTextField, JTextField doorTextField, LinkedList<JPanel> elevatorPanels){
        setLayout(new GridLayout(1,2));

        GridLayout elevatorPanelLayout = new GridLayout(floorNumber + 2,1);
        GridLayout outDoorPanelLayout = new GridLayout(floorNumber,2);

        elevatorPanel.setLayout(elevatorPanelLayout);
        outDoorPanel.setLayout(outDoorPanelLayout);

        for (int i = 0; i < 6; i++) {
            FloorButton newButton = new FloorButton(floorTextField,elevatorPanels,i);
            elevatorPanel.add(newButton);

            JButton callH = new JButton("H"+i);
            JButton callD = new JButton("D"+i);
            outDoorPanel.add(callH);
            outDoorPanel.add(callD);
        }

        JButton alertButton = new JButton("ALERT");
        JButton openTheGate = new JButton("Open the gate");

        elevatorPanel.add(alertButton);
        elevatorPanel.add(openTheGate);

        alertButton.addActionListener(e -> {
            motorTextField.setText("Action: Emergency mode");
            motorTextField.setForeground(Color.red);
        });
        openTheGate.addActionListener(e -> {
            doorTextField.setText("Door: open");
            doorTextField.setForeground(Color.green);
            new Thread(() -> {
                try {
                    sleep(2000);
                    doorTextField.setText("Door: close");
                    doorTextField.setForeground(Color.red);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }).start();
        });

        add(elevatorPanel);
        add(outDoorPanel);
    }

}
