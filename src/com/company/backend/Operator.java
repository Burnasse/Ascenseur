package com.company.backend;

import java.util.LinkedList;
import java.util.List;

public class Operator {

    private final boolean[] requests;
    private List<Button> buttons = new LinkedList<>();

    private int lastMaxRequest = 0;
    private int getLastMaxRequest = 0;

    private Engine engine = new Engine();
    private Cabin cabin = new Cabin();
    private Door door = new Door();

    public Operator(int floorNumber){
        requests = new boolean[floorNumber];

        for (int i = 0; i < floorNumber; i++) {
            buttons.add(new FloorButton(i));
            buttons.add(new OutsideButton(i,true));
            buttons.add(new OutsideButton(i,false));
        }

        buttons.add(new EmergencyButton());
    }

    public int getLastMaxRequest() {
        return lastMaxRequest;
    }

    public void setLastMaxRequest(int lastMaxRequest) {
        this.lastMaxRequest = lastMaxRequest;
    }

    public int getGetLastMaxRequest() {
        return getLastMaxRequest;
    }

    public void setGetLastMaxRequest(int getLastMaxRequest) {
        this.getLastMaxRequest = getLastMaxRequest;
    }
}
