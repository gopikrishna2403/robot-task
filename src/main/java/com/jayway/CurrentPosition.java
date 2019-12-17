package com.jayway;

public class CurrentPosition {

    private final Coordinates coordinates;
    private final char direction;

    public CurrentPosition(Coordinates coordinates, char direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public char getCurrentDirection() {
        return direction;
    }
}
