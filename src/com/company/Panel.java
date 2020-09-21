package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Panel extends JPanel {

    private GridLayout layout = new GridLayout(8,3);

    public Panel(JTextField floorTextField, JTextField motorTextField, JTextField doorTextField){

        setLayout(layout);

        List<FloorButton> floorButtonList = new ArrayList<>();

        JButton alertButton = new JButton("ALERT");
        JButton openTheGate = new JButton("Open the gate");

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

        for (int i = 0; i < 6; i++) {
            FloorButton newButton = new FloorButton(floorTextField,i);
            floorButtonList.add(newButton);
            add(newButton);
            JButton callH = new JButton("H"+i);
            JButton callD = new JButton("D"+i);
            add(callH);
            add(callD);
        }

        add(alertButton);
        add(openTheGate);
    }
}
