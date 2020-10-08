package com.company.frontend;

import com.company.backend.Operator;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class ControlPanel extends JPanel{

    private JPanel elevatorPanel = new JPanel();
    private JPanel outDoorPanel = new JPanel();

    private Operator operator;

    public ControlPanel(int floorNumber, JTextField floorTextField, JTextField motorTextField, JTextField doorTextField, LinkedList<JPanel> elevatorPanels,Operator operator){
        setLayout(new GridLayout(1,2));

        GridLayout elevatorPanelLayout = new GridLayout(floorNumber + 2,1);
        GridLayout outDoorPanelLayout = new GridLayout(floorNumber,2);

        elevatorPanel.setLayout(elevatorPanelLayout);
        outDoorPanel.setLayout(outDoorPanelLayout);

        this.operator = operator;


        for (int i = 0; i < floorNumber; i++) {
            int floor = i;

            /* creation bouton de l'etage i interieur */
            FloorButton newButton = new FloorButton(i);
            newButton.addActionListener(e->{
                operator.newRequestInsideCabin(floor);
            });

            elevatorPanel.add(newButton);

            /* creation bouton up de l'etage i*/
            JButton callH = new JButton("H"+i);
            callH.addActionListener(e->{
                this.operator.newRequestInsideCabin(floor);
                    }
            );
            /* creation bouton down de l'etage i*/

            JButton callD = new JButton("D"+i);
            callD.addActionListener(e->{
                        this.operator.newRequestInsideCabin(floor);
                    }
            );
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

        add(elevatorPanel);
        add(outDoorPanel);
    }


}
