package com.company.backend;

public class Cabin {

    private int floor = 0;

    private Direction direction = Direction.NONE;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
