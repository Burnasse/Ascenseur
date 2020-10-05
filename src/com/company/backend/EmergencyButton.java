package com.company.backend;

public class EmergencyButton implements Button {

    private boolean isPressed = false;

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
