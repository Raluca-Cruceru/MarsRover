package com.ralucacruceru;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    Rover rover = new Rover("Rover",1, 5, "N");
    Location maxLocation = new Location(10,10);
    Map<String, Location> occupiedLocations = new HashMap<>();

    @Test
    void changeInvalidDirection() {
        assertFalse(rover.changeDirection("K"));
        assertEquals(rover.getDirection().getName(), "N");
    }

    @Test
    void changeDirectionLeft() {
        assertTrue(rover.changeDirection("L"));
        assertEquals(rover.getDirection().getName(), "W");
    }

    @Test
    void changeDirectionRight() {
        assertTrue(rover.changeDirection("R"));
        assertEquals(rover.getDirection().getName(), "E");
    }

    @Test
    void moveOnePointInOccupiedPosition() {
        rover = new Rover("Rover",1, 2, "N");
        Rover previousRover = new Rover("PreviousRover", 1, 3, "N");
        occupiedLocations.put("PreviousRover", previousRover.getLocation());
        boolean movedOnePoint = rover.moveOnePoint(maxLocation,occupiedLocations);
        assertFalse(movedOnePoint);
    }

    @Test
    void moveOnePointOutsideGrid() {
        maxLocation = new Location(1,2);
        rover = new Rover("Rover",1, 2, "N");
        boolean movedOnePoint = rover.moveOnePoint(maxLocation,occupiedLocations);
        assertFalse(movedOnePoint);
    }

    @Test
    void moveOnePointToNorth() {
        rover = new Rover("Rover",1, 2, "N");
        boolean movedOnePoint = rover.moveOnePoint(maxLocation,occupiedLocations);
        assertTrue(movedOnePoint);
        assertEquals(rover.getLocation().getY(), 3);
    }


    @Test
    void followInvalidInstruction() {
        rover = new Rover("Rover",1, 5, "N");
        assertFalse(rover.followInstruction("A", maxLocation, occupiedLocations));
    }

    @Test
    void followChangeDirectionInstruction() {
        rover = new Rover("Rover",1, 5, "N");
        maxLocation = new Location(10,10);
        occupiedLocations = new HashMap<>();
        assertTrue(rover.followInstruction("L", maxLocation, occupiedLocations));
        assertEquals(rover.getDirection().getName(), "W");
    }

    @Test
    void followMoveOnePointInstruction() {
        rover = new Rover("Rover",1, 5, "N");
        maxLocation = new Location(10,10);
        occupiedLocations = new HashMap<>();
        assertTrue(rover.followInstruction("M", maxLocation, occupiedLocations));
        assertEquals(rover.getDirection().getName(), "N");
        assertEquals(rover.getLocation().getY(), 6);
    }

    @Test
    void changeLocation() {
        rover = new Rover("Rover",1, 5, "N");
        Location newLocation = new Location(3, 9);
        rover.changeLocation(newLocation);
        assertEquals(rover.getLocation(), newLocation);
    }

    @Test
    void followInstructionsFirstEntry() {
        int xMax = 5;
        int yMax = 5;

        Location maxLocation = new Location(xMax,yMax);
        Map<String, Location> occupiedLocations = new HashMap<>();

        Rover rover = new Rover("Rover1",1, 2, "N");
        String instructions = "LMLMLMLMM";
        rover.followInstructionSet(instructions, maxLocation, occupiedLocations);
        assertEquals(rover.getLocation().getX(), 1);
        assertEquals(rover.getLocation().getY(), 3);
        assertEquals(rover.getDirection().getName(), "N");
    }

    @Test
    void followInstructionsSecondtEntry() {
        int xMax = 5;
        int yMax = 5;

        Location maxLocation = new Location(xMax,yMax);
        Map<String, Location> occupiedLocations = new HashMap<>();

        Rover rover = new Rover("Rover1",1, 2, "N");
        String instructions = "LMLMLMLMM";
        rover.followInstructionSet(instructions, maxLocation, occupiedLocations);
        assertEquals(rover.getLocation().getX(), 1);
        assertEquals(rover.getLocation().getY(), 3);
        assertEquals(rover.getDirection().getName(), "N");
    }


}