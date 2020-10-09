package com.company.backend;

/**
 * Represents users request for elevator.
 */
public class Request {

    private boolean goingUp;
    private boolean inside;
    private boolean request;

    /**
     * Instantiates a new Request.
     */
    public Request(){
        goingUp = false;
        request = false;
        inside = false;
    }

    /**
     * Return true if the request come from inside elevator, else return false.
     *
     * @return the boolean
     */
    public boolean isInside() {
        return inside;
    }

    /**
     * Sets if the request come from inside elevator.
     *
     * @param inside the inside
     */
    public void setInside(boolean inside) {
        this.inside = inside;
    }

    /**
     * Return true if the user want to go up.
     *
     * @return the boolean
     */
    public boolean isGoingUp() {
        return goingUp;
    }

    /**
     * Sets if the user want to go up.
     *
     * @param goingUp the going up
     */
    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    /**
     * Return true if an user make a request for elevator.
     *
     * @return the boolean
     */
    public boolean isRequest() {
        return request;
    }

    /**
     * Sets if an user make a request for elevator.
     *
     * @param request the request
     */
    public void setRequest(boolean request) {
        this.request = request;
    }
}
