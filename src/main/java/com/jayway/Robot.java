package com.jayway;

public class Robot {
    private String directions;
    int width;
    int depth;
    Coordinates currentCoordinates;
    char currentDirection;

    public Robot(String directions, int width, int depth, Coordinates currentCoordinates, char currentDirection) {
        this.directions = directions;
        this.width = width;
        this.depth = depth;
        this.currentCoordinates = currentCoordinates;
        this.currentDirection = currentDirection;
    }

    /**
     * Outputs the final report after executing all the user input commands
     * @return prints final coordinates and direction of robot
     */
    public String getReport() {
        char[] directionChars = directions.toCharArray();

        //getNextDirection
        char nextDirection;

        for (int i = 0; i < directionChars.length; i++) {
            if (directionChars[i] == 'F') {
                currentCoordinates = navigateRobot(currentDirection, currentCoordinates);
            } else {
                nextDirection = directionChars[i];
                currentDirection = updateDirection(nextDirection, currentDirection);
            }
        }

        return "Report: " + currentCoordinates.getWidth() + " " + currentCoordinates.getDepth() + " " + currentDirection;
    }

    /**
     * Navigates Robot if it moves forward
     * @param currentDirection      Currect Robot direction.
     * @param currentCoordinates    Current Robot field coordinates.
     * @return final robot coordinates after the forward navigation
     */
    public Coordinates navigateRobot(char currentDirection, Coordinates currentCoordinates) {
        if (currentDirection == 'N') {
            if(currentCoordinates.getDepth() != 0 && depth != 0)
                currentCoordinates = new Coordinates(currentCoordinates.getWidth() , currentCoordinates.getDepth() - 1);
        }
        if (currentDirection == 'S') {
            if(currentCoordinates.getDepth() != depth - 1 && depth != 0)
                currentCoordinates = new Coordinates(currentCoordinates.getWidth(), currentCoordinates.getDepth() + 1);
        }
        if (currentDirection == 'E') {
            if(currentCoordinates.getWidth() != width - 1 && width != 0)
                currentCoordinates = new Coordinates(currentCoordinates.getWidth() + 1, currentCoordinates.getDepth());
        }
        if (currentDirection == 'W') {
            if(currentCoordinates.getWidth() != 0 && width != 0)
                currentCoordinates = new Coordinates(currentCoordinates.getWidth() - 1, currentCoordinates.getDepth());
        }
        return currentCoordinates;
    }

    /**
     * Updates direction if the Robot turns left or right
     * @param nextDirection     Direction towards which robot moves.
     * @param currentDirection  Currect Robot direction.
     * @return final direction after the robot has moved
     */
    public char updateDirection(char nextDirection, char currentDirection) {
        switch (currentDirection) {
            case 'N': {
                if (nextDirection == 'L')
                    currentDirection = 'W';
                else currentDirection = 'E';
                break;
            }
            case 'E': {
                if (nextDirection == 'L')
                    currentDirection = 'N';
                else currentDirection = 'S';
                break;
            }
            case 'W': {
                if (nextDirection == 'L')
                    currentDirection = 'S';
                else currentDirection = 'N';
                break;
            }
            case 'S': {
                if (nextDirection == 'L')
                    currentDirection = 'E';
                else currentDirection = 'W';
                break;
            }
        }
        return currentDirection;
    }
}
