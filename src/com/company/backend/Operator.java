package com.company.backend;

public class Operator {

    private final boolean[] requests;

    private int lastMaxRequest = 0;
    private int getLastMaxRequest = 0;

    private Engine engine = new Engine();
    private Cabin cabin = new Cabin();
    private Door door = new Door();

    public Operator(int floorNumber){
        requests = new boolean[floorNumber];
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
