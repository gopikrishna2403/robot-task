package com.jayway.test;

import com.jayway.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.jayway.Robot;

public class TestRobot {

    Robot testRobot = new Robot("RFLFFLRF", 5, 5, new Coordinates(0, 0), 'E');

    @Test
    public void verifyUpdateDirection() {
        assertEquals(testRobot.updateDirection('L', 'N'), 'W');
        assertEquals(testRobot.updateDirection('R', 'N'), 'E');
        assertEquals(testRobot.updateDirection('L', 'E'), 'N');
        assertEquals(testRobot.updateDirection('R', 'E'), 'S');
        assertEquals(testRobot.updateDirection('L', 'S'), 'E');
        assertEquals(testRobot.updateDirection('R', 'S'), 'W');
        assertEquals(testRobot.updateDirection('L', 'W'), 'S');
        assertEquals(testRobot.updateDirection('R', 'W'), 'N');
    }

    @Test
    public void verifyNavigateRobotFromZeroCordinates() {
        Robot testRobot = new Robot("RFLFFLRF", 5, 5, new Coordinates(0, 0), 'E');

        Coordinates coordinatesNorth = testRobot.navigateRobot('N', new Coordinates(0, 0));
        assertEquals(coordinatesNorth.getWidth(), 0);
        assertEquals(coordinatesNorth.getDepth(), 0);

        Coordinates coordinatesSouth = testRobot.navigateRobot('S', new Coordinates(0, 0));
        assertEquals(coordinatesSouth.getWidth(), 0);
        assertEquals(coordinatesSouth.getDepth(), 1);

        Coordinates coordinatesEast = testRobot.navigateRobot('E', new Coordinates(0, 0));
        assertEquals(coordinatesEast.getWidth(), 1);
        assertEquals(coordinatesEast.getDepth(), 0);

        Coordinates coordinatesWest = testRobot.navigateRobot('W', new Coordinates(0, 0));
        assertEquals(coordinatesWest.getWidth(), 0);
        assertEquals(coordinatesWest.getDepth(), 0);

    }


    @Test
    public void verifyNavigateRobotFromNonZeroCordinates() {
        Robot testRobot = new Robot("RFLFFLRF", 5, 5, new Coordinates(2, 3), 'E');

        Coordinates coordinatesNorth = testRobot.navigateRobot('N', new Coordinates(2, 3));
        assertEquals(coordinatesNorth.getWidth(), 2);
        assertEquals(coordinatesNorth.getDepth(), 2);

        Coordinates coordinatesSouth = testRobot.navigateRobot('S', new Coordinates(2, 3));
        assertEquals(coordinatesSouth.getWidth(), 2);
        assertEquals(coordinatesSouth.getDepth(), 4);

        Coordinates coordinatesEast = testRobot.navigateRobot('E', new Coordinates(2, 3));
        assertEquals(coordinatesEast.getWidth(), 3);
        assertEquals(coordinatesEast.getDepth(), 3);

        Coordinates coordinatesWest = testRobot.navigateRobot('W', new Coordinates(2, 3));
        assertEquals(coordinatesWest.getWidth(), 1);
        assertEquals(coordinatesWest.getDepth(), 3);

    }

    @Test
    public void verifyNavigateRobotOnEdges() {
        Robot testRobot = new Robot("RFLFFLRF", 5, 5, new Coordinates(2, 3), 'E');

        Coordinates coordinatesSouth = testRobot.navigateRobot('S', new Coordinates(4, 4));
        assertEquals(coordinatesSouth.getWidth(), 4);
        assertEquals(coordinatesSouth.getDepth(), 4);

        Coordinates coordinatesEast = testRobot.navigateRobot('E', new Coordinates(4, 4));
        assertEquals(coordinatesEast.getWidth(), 4);
        assertEquals(coordinatesEast.getDepth(), 4);

        Coordinates coordinatesNorth = testRobot.navigateRobot('N', new Coordinates(4, 4));
        assertEquals(coordinatesNorth.getWidth(), 4);
        assertEquals(coordinatesNorth.getDepth(), 3);

        Coordinates coordinatesWest = testRobot.navigateRobot('W', new Coordinates(4, 4));
        assertEquals(coordinatesWest.getWidth(), 3);
        assertEquals(coordinatesWest.getDepth(), 4);

    }


}
