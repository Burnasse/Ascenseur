package com.company.backend;

public class Engine {

    private boolean isOn = true;
    private boolean onAscend = false;
    private boolean onDescend = false;

    public void ascend(){
        onAscend = true;
        onDescend = false;
    }

    public void descend(){
        onAscend = false;
        onDescend = true;
    }

    public void stopToNextFloor(int nextGoalFloor){

    }

    public void emergencyStop(){
        isOn = !isOn;
        onAscend = !onAscend;
        onDescend = !onDescend;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isOnAscend() {
        return onAscend;
    }

    public boolean isOnDescend() {
        return onDescend;
    }
}
