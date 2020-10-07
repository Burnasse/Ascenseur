package com.company.backend;

import java.util.*;

public class Operator {

    private final boolean[] requests;
    private Map<String, Button> buttons = new HashMap<>(); // Une hashmap a la place d'une liste pour retrouver les buttons facilement.

    private int lastMaxRequest = -1;
    private int lastMinRequest = -1;

    private int nextGoalFloor = -1;

    private volatile Cabin cabin = new Cabin();
    private volatile Engine engine = new Engine();
    private volatile Door door = new Door(); // faut l'utiliser quelque part

    private Thread operatorThread;

    public Operator(int floorNumber){
        requests = new boolean[floorNumber];

        for (int i = 0; i < floorNumber; i++) {
            buttons.put("button"+i,new FloorButton(i));
            buttons.put("outsideUpButton"+i,new OutsideButton(i,true));
            buttons.put("outsideDownButton"+i,new OutsideButton(i,false));
        }

        buttons.put("Emergency",new EmergencyButton());

        startOperator();
    }

    // démarre le thread qui lira toutes les commandes
    private void startOperator(){
        operatorThread = new Thread(() -> {
            while(true){
                if(cabin.getDirection() != Direction.NONE){ // si la cabine a une direction
                    try {
                        Thread.sleep(1500); // on attend pour simuler l'animation
                        if(nextGoalFloor == cabin.getFloor()) { // lorsque la cabine est arrivé, on regarde les futures destination
                            requests[nextGoalFloor] = false;
                            newGoalFloor();
                        }
                        else if(cabin.getDirection() == Direction.UP) // augmente l'étage si la cabine monte
                            cabin.setFloor(cabin.getFloor() + 1);
                        else
                            cabin.setFloor(cabin.getFloor() - 1);// diminue l'étage si la cabine descend
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        operatorThread.start();
    }

    /* trouve le nouveau goal floor après avoir atteint le précédent*/
    private void newGoalFloor(){
        if(!hasRequest()) {
            cabin.setDirection(Direction.NONE);
            nextGoalFloor = -1;
            System.out.println("aucun autre arret");
        }
        if(cabin.getDirection() == Direction.UP){
            if(hasUpRequest()) return;
            hasDownRequest();
            cabin.setDirection(Direction.DOWN);
        }
        if(cabin.getDirection() == Direction.DOWN){
            if (hasDownRequest()) return;
            hasUpRequest();
            cabin.setDirection(Direction.UP);
        }
    }

    // cherche si il y a une requete au dessus du goal (ca sera toujours la position de la cabine)
    private boolean hasUpRequest(){
        for (int i = nextGoalFloor; i < requests.length; i++) {
            if(requests[i]){
                nextGoalFloor = i;
                return true;
            }
        }
        return false;
    }

    // cherche si il y a une requete en dessous du goal (ca sera toujours la position de la cabine)
    private boolean hasDownRequest(){
        for (int i = nextGoalFloor; i > 0; i--) {
            if(requests[i]){
                nextGoalFloor = i;
                return true;
            }
        }
        return false;
    }

    public void newRequestInsideCabin(int floorNumberRequest){
        System.out.println("precedent last request" + nextGoalFloor);

        /* si la requete est deja existante , ne rien faire*/
        if (this.requests[floorNumberRequest])
            return;
        /*sinon on rajoute la requete a la liste*/
        else this.requests[floorNumberRequest] = true;

        /* si il n'y a pas de prochain arret ( que l'assenceur bouge pas ) on va a la nouvelle requete et met la direction vers la requete ( la cabine n'est pas a l'etage de la requete )*/
        if(this.nextGoalFloor == -1) {
            this.nextGoalFloor = floorNumberRequest;
            if (cabin.getFloor() < floorNumberRequest) {
                cabin.setDirection(Direction.UP);
                this.engine.ascend();
            }
            else {
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

        System.out.println("new last request" + nextGoalFloor);
    }

    /*
    Pas utilisé pour l'instant
     */
    public void newUpRequestOutsideCabin(int floorNumberOfRequest){
        System.out.println("precedent last request" + nextGoalFloor);

        if (this.requests[floorNumberOfRequest])
            return;

        if(this.nextGoalFloor == -1)
            this.nextGoalFloor = floorNumberOfRequest;

        this.requests[floorNumberOfRequest] = true;
        System.out.println("new last request" + nextGoalFloor);
    }

    public void newDownRequestOutsideCabin(int floorNumberOfRequest){
        System.out.println("precedent last request" + nextGoalFloor);

        if (this.requests[floorNumberOfRequest])
            return;

        if(this.nextGoalFloor == -1)
            this.nextGoalFloor = floorNumberOfRequest;

        this.requests[floorNumberOfRequest] = true;
        System.out.println("new last request" + nextGoalFloor);

    }

    public void EmergencyPressed(){

    }

    public boolean hasRequest(){
        for (boolean bool : requests) {
            if (bool) return true;
        }
        return false;
    }

    public int getLastMaxRequest() {
        return lastMaxRequest;
    }

    public void setLastMaxRequest(int lastMaxRequest) {
        this.lastMaxRequest = lastMaxRequest;
    }

    public int getLastMinRequest() {
        return lastMinRequest;
    }

    public void setLastMinRequest(int lastMinRequest) {
        this.lastMinRequest = lastMinRequest;
    }
}
