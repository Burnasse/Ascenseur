package com.company.backend;

public class Request {

    private boolean goingUp;
    private boolean inside;
    private boolean request;

    public Request(){
        goingUp = false;
        request = false;
        inside = false;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }
}
