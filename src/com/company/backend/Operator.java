package com.company.backend;

import java.util.*;

/**
 * The type Operator represents the operative part of the elevator.
 * He make the link between all object (cabin, engine, door, button)
 * and handle all events coming from the interface.
 * It is the operator who determines where the elevator should go.
 */
public class Operator extends Thread {

    /**
     * The array of requests, the index represent the floor.
     */
    private final Request[] requests;

    /**
     * The map of inside and outside buttons.
     */
    private final Map<String, Button> buttons = new HashMap<>();

    /**
     * The goal floor of the elevator.
     */
    private int nextGoalFloor = -1;

    private volatile Cabin cabin = new Cabin();
    private volatile Engine engine = new Engine();
    private volatile Door door = new Door();

    /**
     * Instantiates a new Operator.
     *
     * @param floorNumber the floor number
     */
    public Operator(int floorNumber) {
        requests = new Request[floorNumber];

        for (int i = 0; i < floorNumber; i++) {
            requests[i] = new Request();
            buttons.put("button" + i, new FloorButton(i));
            buttons.put("outsideUpButton" + i, new OutsideButton(i, true));
            buttons.put("outsideDownButton" + i, new OutsideButton(i, false));
        }

        buttons.put("Emergency", new EmergencyButton());
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!engine.isOn()) waitOperatorThread(-1);
                if (cabin.getDirection() != Direction.NONE) {
                    waitOperatorThread(1500);
                    if (nextGoalFloor == cabin.getFloor()) {
                        door.toOpen();
                        waitOperatorThread(5000);
                        door.toClose();
                        requests[nextGoalFloor].setRequest(false);
                        if (requests[nextGoalFloor].isInside())
                            buttons.get("button" + nextGoalFloor).displayAction();
                        else if (!requests[nextGoalFloor].isInside() && requests[nextGoalFloor].isGoingUp())
                            buttons.get("outsideUpButton" + nextGoalFloor).displayAction();
                        else if (!requests[nextGoalFloor].isInside() && !requests[nextGoalFloor].isGoingUp())
                            buttons.get("outsideDownButton" + nextGoalFloor).displayAction();
                        newGoalFloor();
                        System.out.println("New goal floor: " + nextGoalFloor);
                    }else if (cabin.getDirection() == Direction.UP) cabin.setFloor(cabin.getFloor() + 1);
                    else cabin.setFloor(cabin.getFloor() - 1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * Simulate the waiting time by pausing the operator's thread.
     * If milliseconds = -1, the thread keep waiting until the call of notify()
     *
     * @param milliseconds time to wait.
     * @throws InterruptedException the exception
     */
    private void waitOperatorThread(int milliseconds) throws InterruptedException {
        synchronized (this) {
            if (milliseconds == -1)
                wait();
            else
                wait(milliseconds);
        }
    }

    /**
     * Check if the elevator has requests.
     *
     * @return the boolean
     */
    private boolean hasRequest() {
        for (Request bool : requests) {
            if (bool.isRequest()) return true;
        }
        return false;
    }

    /**
     * Find the new goal floor, if none are find, set the nextGoalFloor to -1.
     */
    private void newGoalFloor() {
        if (!hasRequest()) {
            cabin.setDirection(Direction.NONE);
            nextGoalFloor = -1;
            System.out.println("Aucun autre arret");
        }
        if (cabin.getDirection() == Direction.UP) {
            if (hasUpRequest()) return;
            hasDownRequest();
            cabin.setDirection(Direction.DOWN);
        }
        if (cabin.getDirection() == Direction.DOWN) {
            if (hasDownRequest()) return;
            hasUpRequest();
            cabin.setDirection(Direction.UP);
        }
    }

    /**
     * Return true if there are requests at the top.
     *
     * @return the boolean
     */
    private boolean hasUpRequest() {
        for (int i = nextGoalFloor; i < requests.length; i++) {
            if ((requests[i].isRequest() && requests[i].isInside()) || (requests[i].isRequest() && requests[i].isGoingUp())) {
                nextGoalFloor = i;
                return true;
            }
        }
        for (int i = requests.length-1; i > nextGoalFloor; i--){
            if (requests[i].isRequest() && !requests[i].isGoingUp() && !requests[i].isInside()){
                nextGoalFloor = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if there are requests at the bottom.
     *
     * @return the boolean
     */
    private boolean hasDownRequest() {
        for (int i = nextGoalFloor; i >= 0; i--) {
            if ((requests[i].isRequest() && requests[i].isInside()) || (requests[i].isRequest() && !requests[i].isGoingUp())) {
                nextGoalFloor = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Initialize the nextGoalFloor when the elevator has no request.
     *
     * @param floorNumberRequest floor number of the request.
     */
    private void initCabinMovement(int floorNumberRequest) {
        if (nextGoalFloor == -1) {
            nextGoalFloor = floorNumberRequest;
            if (cabin.getFloor() < floorNumberRequest) {
                cabin.setDirection(Direction.UP);
                engine.ascend();
            } else {
                cabin.setDirection(Direction.DOWN);
                engine.descend();
            }
        }
    }

    /**
     * Takes a new request for a floor from inside the elevator and activate the corresponding items.
     *
     * @param floorNumberRequest the floor number request
     */
    public void newRequestInsideCabin(int floorNumberRequest) {
        System.out.println("precedent last request" + nextGoalFloor);

        if (requests[floorNumberRequest].isRequest())
            return;
        else {
            requests[floorNumberRequest].setRequest(true);
            requests[floorNumberRequest].setInside(true);
        }

        buttons.get("button" + floorNumberRequest).displayAction();
        initCabinMovement(floorNumberRequest);

        if (cabin.getDirection() == Direction.DOWN && nextGoalFloor < floorNumberRequest && cabin.getFloor() > floorNumberRequest)
            nextGoalFloor = floorNumberRequest;
        if (cabin.getDirection() == Direction.UP && nextGoalFloor > floorNumberRequest && cabin.getFloor() < floorNumberRequest)
            nextGoalFloor = floorNumberRequest;
    }

    /**
     * Takes a new request to go up from outside the elevator and activate the corresponding items.
     *
     * @param floorNumberOfRequest the floor number of request
     */
    public void newUpRequestOutsideCabin(int floorNumberOfRequest) {
        System.out.println("precedent last request" + nextGoalFloor);

        if (this.requests[floorNumberOfRequest].isRequest())
            return;

        buttons.get("outsideUpButton" + floorNumberOfRequest).displayAction();

        initCabinMovement(floorNumberOfRequest);

        requests[floorNumberOfRequest].setRequest(true);
        requests[floorNumberOfRequest].setGoingUp(true);
        requests[floorNumberOfRequest].setInside(false);
        System.out.println("new last request" + nextGoalFloor);

    }

    /**
     * Takes a new request to go down from outside the elevator and activate the corresponding items.
     *
     * @param floorNumberOfRequest the floor number of request
     */
    public void newDownRequestOutsideCabin(int floorNumberOfRequest) {
        System.out.println("precedent last request : " + nextGoalFloor);

        if (this.requests[floorNumberOfRequest].isRequest())
            return;

        buttons.get("outsideDownButton" + floorNumberOfRequest).displayAction();

        initCabinMovement(floorNumberOfRequest);

        requests[floorNumberOfRequest].setRequest(true);
        requests[floorNumberOfRequest].setGoingUp(false);
        requests[floorNumberOfRequest].setInside(false);
        System.out.println("new last request : " + nextGoalFloor);

    }

    /**
     * Activate emergency mode.
     */
    public void EmergencyPressed() {
        System.out.println("EMERGENCY");
        engine.emergencyStop();
        buttons.get("Emergency").displayAction();
        if (engine.isOn()) {
            synchronized (this) {
                notify();
            }
        }
    }

    public  void notifyOperatorThread() throws InterruptedException{
        synchronized (this){
            this.notify();
        }
    }

}
