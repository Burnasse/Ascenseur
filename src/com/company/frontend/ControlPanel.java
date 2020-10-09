package com.company.frontend;

import com.company.backend.Operator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Control panel represents all buttons used by the elevator.
 */
public class ControlPanel extends JPanel{

    private Map<String, JButton> buttonMap = new HashMap<>();

    private JPanel elevatorPanel = new JPanel();
    private JPanel outDoorPanel = new JPanel();

    private Operator operator;

    /**
     * Instantiates a new Control panel.
     *
     * @param floorNumber the floor number
     * @param operator    the operator
     */
    public ControlPanel(int floorNumber, Operator operator){
        setLayout(new GridLayout(1,2));

        GridLayout elevatorPanelLayout = new GridLayout(floorNumber + 2,1);
        GridLayout outDoorPanelLayout = new GridLayout(floorNumber,2);

        elevatorPanel.setLayout(elevatorPanelLayout);
        outDoorPanel.setLayout(outDoorPanelLayout);

        this.operator = operator;

        for (int i = 0; i < floorNumber; i++) {
            int floor = i;

            FloorButton newButton = new FloorButton(i);
            newButton.addActionListener(e->{
                operator.newRequestInsideCabin(floor);
            });
            buttonMap.put(newButton.getText(),newButton);
            elevatorPanel.add(newButton);

            JButton callH = new JButton("H"+i);
            callH.addActionListener(e->{
                this.operator.newUpRequestOutsideCabin(floor);
                    }
            );
            buttonMap.put(callH.getText(),callH);

            JButton callD = new JButton("D"+i);
            callD.addActionListener(e->{
                        this.operator.newDownRequestOutsideCabin(floor);
                    }
            );
            buttonMap.put(callD.getText(),callD);

            outDoorPanel.add(callH);
            outDoorPanel.add(callD);
        }

        JButton alertButton = new JButton("Emergency");
        JButton openTheGate = new JButton("Open the gate");

        buttonMap.put("Emergency", alertButton);

        elevatorPanel.add(alertButton);
        elevatorPanel.add(openTheGate);

        alertButton.addActionListener(e -> {
            operator.EmergencyPressed();
        });

        add(elevatorPanel);
        add(outDoorPanel);
    }

    /**
     * Gets button map.
     *
     * @return the button map
     */
    public Map<String, JButton> getButtonMap() {
        return buttonMap;
    }
}
