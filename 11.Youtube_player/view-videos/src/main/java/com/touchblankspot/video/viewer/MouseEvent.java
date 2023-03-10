package com.touchblankspot.video.viewer;

import java.awt.*;

public class MouseEvent {

    public static void main(String[] args) throws AWTException {

        Robot robot = new Robot();
        robot.setAutoDelay(5000); // Time to wait after firing an event
        Boolean isAdded = true;
        while(true) {
            Point p = MouseInfo.getPointerInfo().getLocation(); // Mouse location
            int x = isAdded ? p.x -10 : p.x + 10;
            isAdded = !isAdded;
            robot.mouseMove(x, p.y+10); // trigger event, this makes the OS to
            // register an event and thereby resetting
            // the idle time
        }
    }

}
