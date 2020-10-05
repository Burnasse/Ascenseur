package com.company.backend;

public class OutsideButton implements Button {

    private int floor = 0;
    private boolean isGoingUp = false;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isGoingUp() {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp) {
        isGoingUp = goingUp;
    }
}
