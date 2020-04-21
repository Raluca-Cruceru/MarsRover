package com.ralucacruceru;

import java.util.Map;

public class Rover {

    private String name;
    private Location location;
    private Direction direction;

    public Rover(String name, int x, int y, String direction) {
        this.name = name;
        this.location = new Location(x, y);
        this.direction = new Direction(direction);
    }

    public String getName() {
        return name;
    }

    public Direction getDirection() {
        return direction;
    }

    public Location getLocation() {
        return location;
    }

    public boolean changeDirection(String newDirection) {

        System.out.println("Trying to change direction " + this.direction.getName() + ". Turning " + newDirection);

        // Change orientation only if the command is either L or R
        if((!(newDirection.equals("L")) && !(newDirection.equals("R")))) {
            System.out.println("Direction not changed!");
            return false;
        }

        this.direction = direction.changeDirection(newDirection);
        System.out.println("Direction changed. New direction is: " + this.direction.getName());
        return true;

    }

    public boolean moveOnePoint(Location maxLocation, Map<String, Location> occupiedLocations) {
        // Hold the possible new location
        Location newLocation = new Location(this.location.getX(), this.location.getY());
        switch (this.direction.getName()) {
            case "N":
                newLocation.changeY(1);
                break;
            case "S":
                newLocation.changeY(-1);
                break;
            case "W":
                newLocation.changeX(-1);
                break;
            case "E":
                newLocation.changeX(1);
                break;
        }

        // Check if the new possible location would overlap another rover, else the rover's location will not change
        for(Location location: occupiedLocations.values()){
            if(location.getX() == newLocation.getX() && location.getY() == newLocation.getY()){
                System.out.println("Location not changed, there is another rover at the new desired location ("
                        + location.getX() + ", " + location.getY() + ")");
                return false;
            }
        }

        // Check if the possible new location would be within the grid limits, else the location will not change
        if(newLocation.getX() >= 0 && newLocation.getX() <= maxLocation.getX()
                && newLocation.getY() >= 0 && newLocation.getY() <= maxLocation.getY()) {

            // If the two conditions are met change rover location and update it in the locations map
            this.changeLocation(newLocation);
            occupiedLocations.put(this.name, newLocation);
            System.out.println("Moved one grid point to " + this.direction.getName());
            return true;

        } else {
            System.out.println("The new desired location is outside the grid limits.");
            return false;
        }

    }

    // Depending on the instruction (L, R, M) perform the required action
    public boolean followInstruction(String instruction, Location maxLocation, Map<String, Location> occupiedLocations ) {
        if(instruction.equals("M")){
            if(this.moveOnePoint(maxLocation, occupiedLocations)) {
                occupiedLocations.replace(this.name, this.location);
                return true;
            }
         } else if(instruction.equals("L") || instruction.equals("R")) {
            if(this.changeDirection(instruction)) {
                return true;
            }
        }
        return false;
    }

    public void followInstructionSet(String instructions, Location maxLocation,  Map<String, Location> occupiedLocations) {
        // Split a String of instruction into individual commands and perform required actions
        String[] instructionsSet = instructions.split("");
        Location temp = this.getLocation();

        // For each instruction, check if it was completed, otherwise canceled due to requirements violation
        for(String instruction : instructionsSet) {
            System.out.println(instruction);
            if(this.followInstruction(instruction, maxLocation, occupiedLocations)) {
                System.out.println("Rover position (" + this.getLocation().getX() + ", " + this.getLocation().getY() + "), in direction " + this.getDirection().getName());

            } else {
                System.out.println("Position/location not changed");
            }
        }
    }

    // Change the rover location with a new one
    public void changeLocation(Location newLocation) {
        this.location = newLocation;
    }

}
