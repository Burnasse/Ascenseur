package com.company.backend;

import java.util.LinkedList;
import java.util.List;

public class Operator {

    private final boolean[] requests;
    private List<Button> buttons = new LinkedList<>();


    private int lastMaxRequest = -1;
    private int getLastMaxRequest = -1;


    private int nextGoalFloor = -1;

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

    public void newRequestInsideCabin(int floorNumberRequest){
        System.out.println("precedent last request" + nextGoalFloor);

        /* si la requete est deja existante , ne rien faire*/
        if (this.requests[floorNumberRequest])
            return;
        /*sinon on rajoute la requete a la liste ( le cas ou la cabine est a l'etage de la requete est chiante, du coup on va juste le rajouter a la liste des requetes pour l'instant)*/
        else this.requests[floorNumberRequest] = true;

        /* si il n'y a pas de prochain arret ( que l'assenceur bouge pas ) on va a la nouvelle requete et met la direction vers la requete ( la cabine n'est pas a l'etage de la requete )*/
        if(this.nextGoalFloor == -1) {
            this.nextGoalFloor = floorNumberRequest;
            if (cabin.getFloor() < floorNumberRequest)
                this.engine.ascend(this.cabin);
            else
                this.engine.descend(this.cabin);
        }

        /* si cabine va vers le bas, que le prochaine etage d'arret est apres le nouvel etage d'arret et que la cabine est au dessus du nouvel etage d'arret, alors on s'arrete la bas */
        if (cabin.getDirection() == Direction.DOWN && this.nextGoalFloor < floorNumberRequest && cabin.getFloor() > floorNumberRequest )
            this.nextGoalFloor = floorNumberRequest;

        /* si cabine va vers le haut, que le prochaine etage d'arret est apres le nouvel etage d'arret et que la cabine est en dessous du nouvel etage d'arret, alors on s'arrete la bas */
        if (cabin.getDirection() == Direction.UP && this.nextGoalFloor > floorNumberRequest && cabin.getFloor() < floorNumberRequest )
            this.nextGoalFloor = floorNumberRequest;

        System.out.println("new last request" + nextGoalFloor);
    }

    public void newUpRequestOutsideCabin(int floorNumberOfRequest){
        System.out.println("precedent last request" + nextGoalFloor);

        if (this.requests[floorNumberOfRequest])
            return ;

        if(this.nextGoalFloor == -1)
            this.nextGoalFloor = floorNumberOfRequest;

        this.requests[floorNumberOfRequest] = true;
        System.out.println("new last request" + nextGoalFloor);
    }

    public void newDownRequestOutsideCabin(int floorNumberOfRequest){
        System.out.println("precedent last request" + nextGoalFloor);

        if (this.requests[floorNumberOfRequest])
            return ;

        if(this.nextGoalFloor == -1)
            this.nextGoalFloor = floorNumberOfRequest;

        this.requests[floorNumberOfRequest] = true;
        System.out.println("new last request" + nextGoalFloor);

    }

    public void EmergencyPressed(){

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
