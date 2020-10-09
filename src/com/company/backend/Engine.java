package com.company.backend;

/**
 * The type Engine.
 */
public class Engine {

    private boolean isOn = true;
    private boolean onAscend = false;
    private boolean onDescend = false;

    /**
     * Tell the engine to ascend.
     */
    public void ascend(){
        onAscend = true;
        onDescend = false;
    }

    /**
     * Tell the engine to descend.
     */
    public void descend(){
        onAscend = false;
        onDescend = true;
    }

    /**
     * Tell the engine to stop to next floor.
     *
     * @param nextGoalFloor the next goal floor
     */
    public void stopToNextFloor(int nextGoalFloor){

    }

    /**
     * Tell the engine to go into emergency mode.
     */
    public void emergencyStop(){
        isOn = !isOn;
        onAscend = !onAscend;
        onDescend = !onDescend;
    }

    /**
     * Return true if the engine is on, else return false.
     *
     * @return the boolean
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Return true if the engine ascend, else return false.
     *
     * @return the boolean
     */
    public boolean isOnAscend() {
        return onAscend;
    }

    /**
     * Return true if the engine descend, else return false.
     *
     * @return the boolean
     */
    public boolean isOnDescend() {
        return onDescend;
    }
}
