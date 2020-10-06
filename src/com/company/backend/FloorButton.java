package com.company.backend;

public class FloorButton implements Button {

    private int floor;

    public FloorButton(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
