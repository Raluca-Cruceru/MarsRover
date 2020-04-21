package com.ralucacruceru;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void changeX(int change) {
        this.x += change;
    }

    public void changeY(int change) {
        this.y += change;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
