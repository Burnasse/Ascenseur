package com.company.backend;

public class OutsideButton implements Button {

    private int floor;
    private boolean isGoingUp;

    public OutsideButton(int floor, boolean isGoingUp) {
        this.floor = floor;
        this.isGoingUp = isGoingUp;
    }

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
