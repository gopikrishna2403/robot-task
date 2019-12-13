package com.jayway;

import java.io.InputStream;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Robot robot = InputParser.parseInput(System.in);
        System.out.println(robot.getReport());
    }
}
