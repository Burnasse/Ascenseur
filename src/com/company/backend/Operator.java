package com.company.backend;

import java.util.*;

public class Operator extends Thread {

    private final Request[] requests;
    private final Map<String, Button> buttons = new HashMap<>();

    private int nextGoalFloor = -1;

    private volatile Cabin cabin = new Cabin();
    private volatile Engine engine = new Engine();
    private volatile Door door = new Door();

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
        try {
            startOperator();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // démarre le thread qui lira toutes les commandes
    private void startOperator() throws InterruptedException {
        while (true) {
            if (!engine.isOn()) waitOperatorThread(-1);
            if (cabin.getDirection() != Direction.NONE) { // si la cabine a une direction
                waitOperatorThread(1500);// on attend pour simuler l'animation
                if (nextGoalFloor == cabin.getFloor()) { // lorsque la cabine est arrivé, on regarde les futures destination
                    doorOpen();
                    waitOperatorThread(5000);
                    doorClose();
                    requests[nextGoalFloor].setRequest(false);
                    if(requests[nextGoalFloor].isInside())
                        buttons.get("button"+nextGoalFloor).displayAction();
                    else if(!requests[nextGoalFloor].isInside() && requests[nextGoalFloor].isGoingUp())
                        buttons.get("outsideUpButton"+nextGoalFloor).displayAction();
                    else if(!requests[nextGoalFloor].isInside() && !requests[nextGoalFloor].isGoingUp())
                        buttons.get("outsideDownButton"+nextGoalFloor).displayAction();
                    newGoalFloor();
                } else if (cabin.getDirection() == Direction.UP) // augmente l'étage si la cabine monte
                    cabin.setFloor(cabin.getFloor() + 1);
                else
                    cabin.setFloor(cabin.getFloor() - 1);// diminue l'étage si la cabine descend
            }

        }
    }



    /* trouve le nouveau goal floor après avoir atteint le précédent*/
    private void newGoalFloor() {
        if (!hasRequest()) {
            cabin.setDirection(Direction.NONE);
            nextGoalFloor = -1;
            System.out.println("aucun autre arret");
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

    // cherche si il y a une requete au dessus du goal (ca sera toujours la position de la cabine)
    private boolean hasUpRequest() {
        for (int i = nextGoalFloor; i < requests.length; i++) {
            if ((requests[i].isRequest() && requests[i].isInside()) || (requests[i].isRequest() && requests[i].isGoingUp())) {
                nextGoalFloor = i;
                return true;
            }
        }
        return false;
    }

    // cherche si il y a une requete en dessous du goal (ca sera toujours la position de la cabine)
    private boolean hasDownRequest() {
        for (int i = nextGoalFloor; i > 0; i--) {
            if ((requests[i].isRequest() && requests[i].isInside()) || (requests[i].isRequest() && !requests[i].isGoingUp())) {
                nextGoalFloor = i;
                return true;
            }
        }
        return false;
    }

    public void newRequestInsideCabin(int floorNumberRequest) {
        System.out.println("precedent last request" + nextGoalFloor);

        /* si la requete est deja existante , ne rien faire*/
        if (this.requests[floorNumberRequest].isRequest())
            return;
            /*sinon on rajoute la requete a la liste*/
        else {
            this.requests[floorNumberRequest].setRequest(true);
            this.requests[floorNumberRequest].setInside(true);
        }

        initCabinMovement(floorNumberRequest);

        buttons.get("button"+floorNumberRequest).displayAction();

    }

    private void initCabinMovement(int floorNumberRequest) {
        /* si il n'y a pas de prochain arret ( que l'assenceur bouge pas ) on va a la nouvelle requete et met la direction vers la requete ( la cabine n'est pas a l'etage de la requete )*/
        if (this.nextGoalFloor == -1) {
            this.nextGoalFloor = floorNumberRequest;
            if (cabin.getFloor() < floorNumberRequest) {
                cabin.setDirection(Direction.UP);
                this.engine.ascend();
            } else {
                cabin.setDirection(Direction.DOWN);
                this.engine.descend();
            }
        }

        /* si cabine va vers le bas, que le prochaine etage d'arret est apres le nouvel etage d'arret et que la cabine est au dessus du nouvel etage d'arret, alors on s'arrete la bas */
        if (cabin.getDirection() == Direction.DOWN && this.nextGoalFloor < floorNumberRequest && cabin.getFloor() > floorNumberRequest )
            this.nextGoalFloor = floorNumberRequest;

        /* si cabine va vers le haut, que le prochaine etage d'arret est apres le nouvel etage d'arret et que la cabine est en dessous du nouvel etage d'arret, alors on s'arrete la bas */
        if (cabin.getDirection() == Direction.UP && this.nextGoalFloor > floorNumberRequest && cabin.getFloor() < floorNumberRequest )
            this.nextGoalFloor = floorNumberRequest;
    }

    /*
    Pas utilisé pour l'instant
     */
    public void newUpRequestOutsideCabin(int floorNumberOfRequest) {
        System.out.println("precedent last request" + nextGoalFloor);

        if (this.requests[floorNumberOfRequest].isRequest())
            return;

        buttons.get("outsideUpButton"+floorNumberOfRequest).displayAction();

        initCabinMovement(floorNumberOfRequest);

        this.requests[floorNumberOfRequest].setRequest(true);
        this.requests[floorNumberOfRequest].setGoingUp(true);
        this.requests[floorNumberOfRequest].setInside(false);
        System.out.println("new last request" + nextGoalFloor);

    }

    public void newDownRequestOutsideCabin(int floorNumberOfRequest) {
        System.out.println("precedent last request" + nextGoalFloor);

        if (this.requests[floorNumberOfRequest].isRequest())
            return;

        buttons.get("outsideDownButton"+floorNumberOfRequest).displayAction();

        initCabinMovement(floorNumberOfRequest);

        this.requests[floorNumberOfRequest].setRequest(true);
        this.requests[floorNumberOfRequest].setGoingUp(false);
        this.requests[floorNumberOfRequest].setInside(false);
        System.out.println("new last request" + nextGoalFloor);

    }

    public void EmergencyPressed() {
        System.out.println("EMERGENCY");
        engine.emergencyStop();
        buttons.get("Emergency").displayAction();
        if (engine.isOn()) {
            synchronized (this) {
                this.notify();
            }
        }
    }

    public boolean getDoorStatus() {
        return door.isOpen();
    }

    public boolean hasRequest() {
        for (Request bool : requests) {
            if (bool.isRequest()) return true;
        }
        return false;
    }

    public void doorOpen() {
        door.toOpen();
    }

    public void doorClose() {
        door.toClose();
    }

    public void waitOperatorThread(int milliseconds) throws InterruptedException {
        synchronized (this) {
            if (milliseconds == -1)
                wait();
            else
                wait(milliseconds);
        }
    }

}
