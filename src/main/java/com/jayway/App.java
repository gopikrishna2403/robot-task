package com.jayway;

public class App {

    public static void main(String[] args) {
        Robot robot = InputParser.parseInput(System.in);
        System.out.println(robot.getReport());
    }
}
