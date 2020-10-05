package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new GlobalPanel());

        frame.setPreferredSize(new Dimension(1000,800));
        frame.pack();
        frame.setVisible(true);
    }
}
