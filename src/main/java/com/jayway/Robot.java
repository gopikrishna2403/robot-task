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

    public String getReport() {
        char[] directionChars = directions.toCharArray();

        //getNextDirection
        char nextDirection;


        for (int i = 0; i < directionChars.length; i++) {
            if (directionChars[i] == 'F') {
                if (currentDirection == 'N') {
                    if(currentCoordinates.getDepth() != 0) currentCoordinates = new Coordinates(currentCoordinates.getWidth() , currentCoordinates.getDepth() - 1);
                }
                if (currentDirection == 'S') {
                    if(currentCoordinates.getDepth() != depth - 1) currentCoordinates = new Coordinates(currentCoordinates.getWidth(), currentCoordinates.getDepth() + 1);
                }
                if (currentDirection == 'E') {
                    if(currentCoordinates.getWidth() != width - 1) currentCoordinates = new Coordinates(currentCoordinates.getWidth() + 1, currentCoordinates.getDepth());
                }
                if (currentDirection == 'W') {
                    if(currentCoordinates.getWidth() != 0) currentCoordinates = new Coordinates(currentCoordinates.getWidth() - 1, currentCoordinates.getDepth());
                }
            }
            else {
                nextDirection = directionChars[i];
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
            }

        }

        return "Report: " + currentCoordinates.getWidth() + " " + currentCoordinates.getDepth() + " " + currentDirection;
    }
}
