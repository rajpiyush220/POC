package com.touchblankspot.video.viewer;

import java.awt.*;
import java.util.Random;

public class MouseEvent {

    private static Random rand = new Random();

    public static void main(String[] args) throws AWTException {

        Robot robot = new Robot();
        robot.setAutoDelay(5000); // Time to wait after firing an event
        Boolean isAdded = true;
        while(true) {
            Point p = MouseInfo.getPointerInfo().getLocation(); // Mouse location
            int x = isAdded ? p.x -getRandomNumber() : p.x + getRandomNumber();
            int y = isAdded ? p.y -getRandomNumber() : p.y + getRandomNumber();
            isAdded = !isAdded;
            robot.mouseMove(x, y); // trigger event, this makes the OS to
            // register an event and thereby resetting
            // the idle time
        }
    }

    private static int getRandomNumber(){
        return rand.nextInt((200 - 10) + 1) + 10;
    }

}
