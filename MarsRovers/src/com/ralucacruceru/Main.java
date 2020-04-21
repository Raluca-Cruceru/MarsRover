package com.ralucacruceru;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // The input data regarding grid upper right coordinates, rovers' coordinates and instructions will be extracted
        // from a .txt file located in the project directory

        System.out.println(System.getProperty("user.dir"));
        String path = Paths.get("").toAbsolutePath().toString();
        File file = new File(path + "/input.txt");

        try {

            Scanner sc = new Scanner(file);

            // Extract the upper right coordinates of the grid from the first line of input
            String[] gridCoordinates = sc.nextLine().split(" ");
            int xMax = Integer.parseInt(gridCoordinates[0]);
            int yMax = Integer.parseInt(gridCoordinates[1]);

            // Construct the maximum location that a rover could hold on the grid
            Location maxLocation = new Location(xMax, yMax);

            // Create a HashMap keeping track of each rover's position on the grid
            Map<String, Location> occupiedLocations = new HashMap<>();

            // Initialize no of rovers with 0 and declare a new rover and instruction set
            int roverNo = 0;
            Rover newRover;
            String instructions;
            int xCoord, yCoord;

            // Extract information while there is a next rover in the input
            while (sc.hasNextLine()) {

                // Extract rover coordinates and  direction from the following line
                String[] roverCoordinates = sc.nextLine().split(" ");
                xCoord = Integer.parseInt(roverCoordinates[0]);
                yCoord = Integer.parseInt(roverCoordinates[1]);
                Direction roverDirection = new Direction(roverCoordinates[2]);

                // A new rover was found in input
                roverNo++;
                System.out.println("Rover number " + roverNo + ", initally at location (" + xCoord + ", " + yCoord
                        + ") in direction " + roverDirection.getName() + ":");

                // Create a new Rover object holding its name, position and orientation
                newRover = new Rover(("Rover" + roverNo), xCoord, yCoord, roverDirection.getName());
                // Extract instruction set for this rover from next line
                instructions = sc.nextLine();

                boolean canAddRover = true;
                // Check is new rover can be added at the desired position (cannot overlap another rover)
                for(Location location: occupiedLocations.values()){
                    if(location.getX() == newRover.getLocation().getX() && location.getY() == newRover.getLocation().getY()){
                        System.out.println("Rover number " + roverNo + " cannot be added to the grid. There is another "
                                + "rover at the location (" + newRover.getLocation().getX() + ", "
                                + newRover.getLocation().getY() + ").");
                        canAddRover = false;
                    }
                }

                if(canAddRover) {
                    // Add rover to map of locations
                    occupiedLocations.put(newRover.getName(), newRover.getLocation());

                    // Follow instruction set and print results
                    newRover.followInstructionSet(instructions, maxLocation, occupiedLocations);
                    System.out.println();
                    System.out.println("END OF INSTRUCTION SET FOR ROVER NO " + roverNo);
                    System.out.println("Rover number " + roverNo + ", now at location (" + newRover.getLocation().getX()
                            + ", " + newRover.getLocation().getY() + ") in direction " + newRover.getDirection().getName() + ".");
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println();
                } else {
                    roverNo--;
                }

            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
