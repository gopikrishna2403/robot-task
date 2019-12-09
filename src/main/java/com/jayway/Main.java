package com.jayway;

import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter field size:");
        Scanner fieldScanner = new Scanner(System.in);
        String[] fieldInput = fieldScanner.nextLine().split(" ");
        int width = Integer.parseInt(fieldInput[0]);
        int depth = Integer.parseInt(fieldInput[1]);

        System.out.println("Enter current coordinates and direction:");
        Scanner currentCoordinatesScanner = new Scanner(System.in);
        String[] currentCoordinatesInput = currentCoordinatesScanner.nextLine().split(" ");
        Coordinates coordinates = new Coordinates(Integer.parseInt(currentCoordinatesInput[0]), Integer.parseInt(currentCoordinatesInput[1]));
        char currentDirection = currentCoordinatesInput[2].charAt(0);

        System.out.println("Enter ommands:");
        Scanner commandScanner = new Scanner(System.in);
        String commandInput = commandScanner.nextLine();

        Robot robot = new Robot(commandInput, width, depth, coordinates, currentDirection);
        System.out.println(robot.getReport());
    }
}
