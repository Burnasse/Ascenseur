package com.company.backend;

import com.company.frontend.GlobalPanel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

public class Tests {

    @Test
    public void goingTo1Test(){
        GlobalPanel.controlPanel.getButtonMap().get("1").doClick();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 1");
    }

    @Test
    public void goingTo1Then0(){
        GlobalPanel.controlPanel.getButtonMap().get("1").doClick();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 1");
        GlobalPanel.controlPanel.getButtonMap().get("0").doClick();
        waitForDoorToClose();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 0");
    }

    @Test
    public void goingTo1Then4(){
        GlobalPanel.controlPanel.getButtonMap().get("1").doClick();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 1");
        GlobalPanel.controlPanel.getButtonMap().get("0").doClick();
        waitForDoorToClose();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 0");
    }

    @Test
    public void d4d3() {
        GlobalPanel.controlPanel.getButtonMap().get("D4").doClick();
        GlobalPanel.controlPanel.getButtonMap().get("D3").doClick();
        waitForCabinToMoveXFloors(4);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 4");
        System.out.println("4em");
        waitForDoorToClose();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 3");
        System.out.println("3em");
    }

    @Test
    public void Floor3thenH2H1(){
        GlobalPanel.controlPanel.getButtonMap().get("3").doClick();
        waitForCabinToMoveXFloors(3);
        waitForDoorToClose();
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 3");
        System.out.println("3em");
        GlobalPanel.controlPanel.getButtonMap().get("H2").doClick();
        GlobalPanel.controlPanel.getButtonMap().get("H1").doClick();
        waitForCabinToMoveXFloors(2);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 1");
        System.out.println("1em");
        waitForDoorToClose();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 2");
        System.out.println("2em");
    }

    @Test
    public void d3d4then0(){
        GlobalPanel.controlPanel.getButtonMap().get("D3").doClick();
        GlobalPanel.controlPanel.getButtonMap().get("D4").doClick();
        waitForCabinToMoveXFloors(4);
        assertEquals("Floor: 3",GlobalPanel.displayPanel.getFloorTextField().getText());
        System.out.println("4em");
        waitForDoorToClose();
        waitForCabinToMoveXFloors(1);
        assertEquals(GlobalPanel.displayPanel.getFloorTextField().getText(),"Floor: 4");
        System.out.println("3em");
    }

    public void wait(int milisec){
        try {
            TimeUnit.MILLISECONDS.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForDoorToClose(){
        wait(6550);
    }

    public void waitForCabinToMoveXFloors(int x){

        for(  ; x > 0;x --){
            wait(1505);
        }
    }
}
