package com.jayway;

import java.io.InputStream;
import java.util.Scanner;

public class InputParser {

    /**
     * Parses User input for Field coordinates, current coordinates
     * and commands
     * @param inputStream   Stream for accepting user input.
     * @throws IllegalArgumentException
     */
    public static Robot parseInput(final InputStream inputStream) {
        final int width;
        final int depth;
        final char currentDirection;
        Coordinates coordinates;
        final Scanner scanner = new Scanner(inputStream);

        System.out.println("Enter field size:");
        String[] fieldInput = scanner.nextLine().split(" ");
        if (fieldInput.length == 2) {
            try {
                width = Integer.parseInt(fieldInput[0]);
                depth = Integer.parseInt(fieldInput[1]);
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("The field coordinates only accepts numbers");
            }
        } else {
            throw new IllegalArgumentException("Field has invalid parameters");
        }

        System.out.println("Enter current coordinates and direction:");
        String[] currentCoordinatesInput = scanner.nextLine().split(" ");
        if (fieldInput.length == 2) {
            try {
                coordinates = new Coordinates(Integer.parseInt(currentCoordinatesInput[0]), Integer.parseInt(currentCoordinatesInput[1]));
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("The current robot coordinates only accepts numbers");
            }
            currentDirection = currentCoordinatesInput[2].charAt(0);
        } else {
            throw new IllegalArgumentException("Current coordinates has invalid parameters");
        }

        if(coordinates.getWidth() >= width || coordinates.getDepth() >= depth){
            throw new IllegalArgumentException("Current coordinates cannot exceed field coordinates");
        }

        System.out.println("Enter Commands:");
        String commandInput = scanner.nextLine();
        for (char eachChar : commandInput.toCharArray()){
            if(isCommandInvalid(eachChar)){
                throw new IllegalArgumentException(String.format("Invalid command. Robot can move forward (F), rotate left (L) or rotate right (R). I don't know what '%s' means", eachChar));
            }
        }
        return new Robot(commandInput, width, depth, coordinates, currentDirection);
    }

    /**
     * Verify if commands are valid
     * and commands
     * @param command   Each command.
     * @return if the command is valid
     */
    public static boolean isCommandInvalid(char command) {
        if ('F' == command) {
            return false;
        } else if ('L' == command) {
            return false;
        } else if ('R' == command) {
            return false;
        } else {
            return true;
        }
    }

}
