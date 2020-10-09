package com.company.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * The type Floor button is the graphic representation of the buttons.
 */
public class FloorButton extends JButton {

    /**
     * Instantiates a new Floor button.
     *
     * @param floor the floor
     */
    public FloorButton(int floor){
        setText(String.valueOf(floor));
    }

}
