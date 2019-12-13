package com.jayway.test;

import com.jayway.InputParser;
import com.jayway.Robot;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.jayway.InputParser.isCommandInvalid;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestInputParser {

    @Test
    public void verifyCommandParserForPositiveCases() {
        String commandInput = "FFFFLLLRRRRR";
        for (char eachChar : commandInput.toCharArray()) {
            assertFalse(isCommandInvalid(eachChar));
        }
    }

    @Test
    public void verifyCommandParserForNegativeCases() {
        String commandInput = "FFFFLLLGRG";
        for (char eachChar : commandInput.toCharArray()) {
            if (isCommandInvalid(eachChar)) {
                assertTrue(isCommandInvalid(eachChar));
            }
        }
    }

    @Test
    public void verifyCommandParserForCaseSensitive() {
        String commandInput = "lrf";
        for (char eachChar : commandInput.toCharArray()) {
            if (isCommandInvalid(eachChar)) {
                assertTrue(isCommandInvalid(eachChar));
            }
        }
    }

    @Test
    public void verifyCommandsParserForOnlyLeftCommands() {
        String commandInput = "LLLLLLLLLLLLLLLLLL";
        for (char eachChar : commandInput.toCharArray()) {
            assertFalse(isCommandInvalid(eachChar));
        }
    }

    @Test
    public void verifyCommandsParserForOnlyRightCommands() {
        String commandInput = "RRRRRRRRRR";
        for (char eachChar : commandInput.toCharArray()) {
            assertFalse(isCommandInvalid(eachChar));
        }
    }

    @Test
    public void verifyCommandsParserForOnlyForwardCommands() {
        String commandInput = "FFFFFFFFFFF";
        for (char eachChar : commandInput.toCharArray()) {
            assertFalse(isCommandInvalid(eachChar));
        }
    }

    @Test
    public void verifyCommandsParserForOnlyInvalidCommands() {
        String commandInput = "KKKDDDMMMEEEWWWQQPPP";
        for (char eachChar : commandInput.toCharArray()) {
            assertTrue(isCommandInvalid(eachChar));
        }
    }

    @Test
    public void testExample1() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream("5 5\n1 2 N\nRFRFFRFRF".getBytes());
        Robot testRobot = InputParser.parseInput(inputStream);
        assertEquals(testRobot.getReport(), "Report: 1 3 N");
        inputStream.close();
    }

    @Test
    public void testExample2() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream("5 5\n0 0 E\nRFLFFLRF".getBytes());
        Robot testRobot = InputParser.parseInput(inputStream);
        assertEquals(testRobot.getReport(), "Report: 3 1 E");
        inputStream.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCommands() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream("5 5\n0 0 E\nRFLFFLRFG".getBytes());
        InputParser.parseInput(inputStream);
        inputStream.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFieldSize() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream("5 5 4\n0 0 E\nRFLFFLRF".getBytes());
        InputParser.parseInput(inputStream);
        inputStream.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCurrentCoordinates() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream("5 5\n1 5 0 E\nRFLFFLRF".getBytes());
        InputParser.parseInput(inputStream);
        inputStream.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrentCoordinatesDepthExceedFieldDepth() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream("5 5\n0 5 E\nRFLFFLRF".getBytes());
        InputParser.parseInput(inputStream);
        inputStream.close();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCurrentCoordinatesWidthExceedFieldWidth() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream("5 5\n5 0 E\nRFLFFLRF".getBytes());
        InputParser.parseInput(inputStream);
        inputStream.close();
    }

    @Test
    public void testExample3() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(("10 11\n0 0 E\nFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFL").getBytes());
        Robot testRobot = InputParser.parseInput(inputStream);
        assertEquals(testRobot.getReport(), "Report: 9 0 N");
        inputStream.close();
    }

    @Test
    public void testExample6() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(("155 10\n0 0 S\nFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFL").getBytes());
        Robot testRobot = InputParser.parseInput(inputStream);
        assertEquals(testRobot.getReport(), "Report: 0 9 E");
        inputStream.close();
    }

    @Test
    public void testExample7() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(("155 122\n0 0 S\nFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFL").getBytes());
        Robot testRobot = InputParser.parseInput(inputStream);
        assertEquals(testRobot.getReport(), "Report: 0 28 E");
        inputStream.close();
    }

    @Test
    public void testExample8() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(("155 122\n0 0 S\nFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFL").getBytes());
        Robot testRobot = InputParser.parseInput(inputStream);
        assertEquals(testRobot.getReport(), "Report: 0 121 E");
        inputStream.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFieldCoordinates() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(("R 122\n0 0 S\nFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFL").getBytes());
        InputParser.parseInput(inputStream);
        inputStream.close();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRobotCoordinates() throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(("12 122\nR 0 S\nFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFL").getBytes());
        InputParser.parseInput(inputStream);
        inputStream.close();
    }
}
