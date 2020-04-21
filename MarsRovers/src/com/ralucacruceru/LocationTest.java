package com.ralucacruceru;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    Location location = new Location(1,5);


    @Test
    void increaseX() {
        int increase = 5;
        location.changeX(increase);
        assertEquals(location.getX(), 6);
    }

    @Test
    void decreaseX() {
        int increase = -5;
        location.changeX(increase);
        assertEquals(location.getX(), -4);
    }

    @Test
    void zeroAddToX() {
        location.changeX(0);
        assertEquals(location.getX(), 1);
    }

    @Test
    void increaseY() {
        int increase = 5;
        location.changeY(increase);
        assertEquals(location.getY(), 10);
    }

    @Test
    void decreaseY() {
        int increase = -5;
        location.changeY(increase);
        assertEquals(location.getY(), 0);
    }

    @Test
    void zeroAddToY() {
        location.changeY(0);
        assertEquals(location.getY(), 5);
    }
}