package com.ralucacruceru;

public class Direction {

    private String name;
    private String left;
    private String right;

    // Depending on the orientation, left and right direction will differ
    public Direction(String name) {
        this.name = name;
        switch(name) {
            case("N"):
                this.left = "W";
                this.right = "E";
                break;
            case("S"):
                this.left = "E";
                this.right = "W";
                break;
            case("W"):
                this.left = "S";
                this.right = "N";
                break;
            case("E"):
                this.left = "N";
                this.right = "S";
                break;
        }
    }

    public String getName() {
        return name;
    }

    // Change the direction, and update the left and right values.
    public Direction changeDirection(String direction) {
        if(direction.equals("L")) {
            return new Direction(this.left);
        } else if(direction.equals("R")) {
            return new Direction(this.right);
        }
        // Cancel change if the instruction is different than L and R
        System.out.println("The direction has not changed! Try L/R");
        return this;
    }

}
