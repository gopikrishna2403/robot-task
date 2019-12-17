package com.jayway;

import java.io.InputStream;
import java.util.Scanner;

public class InputParser {

    /**
     * Parses User input for Field coordinates, current coordinates
     * and commands
     *
     * @param inputStream Stream for accepting user input.
     * @return Robot
     */
    public static Robot parseInput(final InputStream inputStream) {
        Coordinates roomCoordinates;
        CurrentPosition currentPosition;
        final Scanner scanner = new Scanner(inputStream);

        roomCoordinates = getRoomSize(scanner);

        currentPosition = getCurrentCoordinates(scanner, roomCoordinates);
        String commandInput = getCommandInput(scanner);

        return new Robot(commandInput, roomCoordinates.getWidth(), roomCoordinates.getDepth(), currentPosition.getCoordinates(), currentPosition.getCurrentDirection());
    }

    /**
     * Verify if commands are valid
     * and commands
     *
     * @param command Each command.
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

    /**
     * Handles input for Room size
     *
     * @param scanner Scanner input for room size.
     * @return Coordinates
     */
    public static Coordinates getRoomSize(Scanner scanner) {
        System.out.println("Enter field size:");
        String[] fieldInput = scanner.nextLine().split(" ");
        if (fieldInput.length == 2) {
            try {
                return new Coordinates(Integer.parseInt(fieldInput[0]), Integer.parseInt(fieldInput[1]));
            } catch (NumberFormatException e) {
                System.out.println("The field coordinates only accepts numbers");
                return getRoomSize(scanner);
            }
        } else {
            System.out.println("Field has invalid parameters");
            return getRoomSize(scanner);
        }
    }

    /**
     * Handles input for Current position of Robot
     *
     * @param scanner         Scanner input for room size.
     * @param roomCoordinates Room size.
     * @return CurrentPosition
     */
    public static CurrentPosition getCurrentCoordinates(Scanner scanner, Coordinates roomCoordinates) {
        System.out.println("Enter current coordinates and direction:");
        String[] currentPositionInput = scanner.nextLine().split(" ");
        if (currentPositionInput.length == 3) {
            try {
                int width = Integer.parseInt(currentPositionInput[0]);
                int depth = Integer.parseInt(currentPositionInput[1]);
                char currentDirection = currentPositionInput[2].charAt(0);

                //Validate current coordinates does not exceed room size
                if (width >= roomCoordinates.getWidth() || depth >= roomCoordinates.getDepth()) {
                    System.out.println("Current coordinates cannot exceed field coordinates");
                    return getCurrentCoordinates(scanner, roomCoordinates);
                }

                //Validate Current direction
                if ('N' != currentDirection && 'W' != currentDirection && 'E' != currentDirection && 'S' != currentDirection) {
                    System.out.println("Current direction can only be either North (N), West (W), East (E) or South (S)");
                    return getCurrentCoordinates(scanner, roomCoordinates);
                }

                return new CurrentPosition(new Coordinates(width, depth), currentDirection);
            } catch (NumberFormatException e) {
                System.out.println("The current robot coordinates only accepts numbers");
                return getCurrentCoordinates(scanner, roomCoordinates);
            }
        } else {
            System.out.println("Current coordinates has invalid parameters");
            return getCurrentCoordinates(scanner, roomCoordinates);
        }
    }

    /**
     * Handles input for Robot Commands
     *
     * @param scanner Scanner input for commands.
     * @return String
     */
    private static String getCommandInput(Scanner scanner) {
        System.out.println("Enter Commands:");
        String commandInput = scanner.nextLine();
        for (char eachChar : commandInput.toCharArray()) {
            if (isCommandInvalid(eachChar)) {
                System.out.println(String.format("Invalid command. Robot can move forward (F), rotate left (L) or rotate right (R). I don't know what '%s' means", eachChar));
                return getCommandInput(scanner);
            }
        }
        return commandInput;
    }
}
