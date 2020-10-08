package com.company.backend;

import com.company.frontend.GlobalPanel;

import java.awt.*;

public class EmergencyButton implements Button {

    private boolean isPressed = false;

    @Override
    public void displayAction() {
        isPressed = !isPressed;
        if(isPressed){
            GlobalPanel.displayPanel.getMotorTextField().setText("Action: Emergency mode");
            GlobalPanel.displayPanel.getMotorTextField().setForeground(Color.red);
        }
        else{
            GlobalPanel.displayPanel.getMotorTextField().setText("Action: Stop");
            GlobalPanel.displayPanel.getMotorTextField().setForeground(Color.green);
        }
    }
}
