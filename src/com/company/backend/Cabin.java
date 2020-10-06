package com.company.backend;

public class Cabin {

    private int floor ;

    private Direction direction ;

    public Cabin() {
        this.floor = 0;
        this.direction = Direction.NONE;
    }

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
