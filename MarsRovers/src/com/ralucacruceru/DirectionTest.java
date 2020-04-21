package com.ralucacruceru;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    Direction direction = new Direction("N");

    @org.junit.jupiter.api.Test
    void changeDirection() {
        Direction newDirection = direction.changeDirection("L");
        assertNotEquals(newDirection.getName(), direction.getName());
    }

    @org.junit.jupiter.api.Test
    void changeWrongDirectionInput() {
        Direction newDirection = direction.changeDirection("A");
        assertEquals(newDirection.getName(), direction.getName());
    }

    @org.junit.jupiter.api.Test
    void changeDirectionNorthLeftWest() {
        Direction newDirection = direction.changeDirection("L");
        assertEquals(newDirection.getName(), "W");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionNorthRightEast() {
        Direction newDirection = direction.changeDirection("R");
        assertEquals(newDirection.getName(), "E");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionSouthLeftEast() {
        direction = new Direction("S");
        Direction newDirection = direction.changeDirection("L");
        assertEquals(newDirection.getName(), "E");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionSouthRightWest() {
        direction = new Direction("S");
        Direction newDirection = direction.changeDirection("R");
        assertEquals(newDirection.getName(), "W");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionWestLeftSouth() {
        direction = new Direction("W");
        Direction newDirection = direction.changeDirection("L");
        assertEquals(newDirection.getName(), "S");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionWestRightNorth() {
        direction = new Direction("W");
        Direction newDirection = direction.changeDirection("R");
        assertEquals(newDirection.getName(), "N");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionEastLeftNorth() {
        direction = new Direction("E");
        Direction newDirection = direction.changeDirection("L");
        assertEquals(newDirection.getName(), "N");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionEastRightSouth() {
        direction = new Direction("E");
        Direction newDirection = direction.changeDirection("R");
        assertEquals(newDirection.getName(), "S");
    }

    @org.junit.jupiter.api.Test
    void changeDirectionnEmpty() {
        direction = new Direction("E");
        Direction newDirection = direction.changeDirection("");
        assertEquals(newDirection.getName(), direction.getName());
    }

}