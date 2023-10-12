package com.cmpt213;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yurii Huba
 * @version 1.0
 */

/**
 * <p>The BikeRegistry class represents a simple bike registry application.
 * <p>It allows users to manage a list of bikes, including following operations with bike entries:
 * <p>- listing ("List Bikes" option 1 in Main Menu)
 * <p>- adding ("Add a new bike" option 2 in Main Menu)
 * <p>- removing ("Remove a bike" option 3 in Main Menu)
 * <p>- modifying attributes ("Change Bike Attribute" option 4 in Main Menu)
 * <p>- showing bike objects toString for debugging ("DEBUG: Dump objects (toString)" option 5 in Main Menu)
 *
 * <p>This class serves as the main entry point for the bike registry application.
 * 
 */
public class BikeRegistry {
    private static List<Bike> bikeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMainMenu();
    }

    private static void displayMainMenu() {
        while (true) {
            System.out.println();
            System.out.println("**************************************************");
            System.out.println("* Bike Registry by Yurii Huba, SFU ID: 301540732 *");
            System.out.println("**************************************************");
            System.out.println("*************");
            System.out.println("* Main Menu *");
            System.out.println("*************");
            System.out.println("1. List Bikes");
            System.out.println("2. Add a new Bike");
            System.out.println("3. Remove a Bike");
            System.out.println("4. Change Bike attribute");
            System.out.println("5. DEBUG: Dump objects (toString)");
            System.out.println("6. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    listBikes();
                    break;
                case 2:
                    addNewBike();
                    break;
                case 3:
                    removeBike();
                    break;
                case 4:
                    changeBikeAttribute();
                    break;
                case 5:
                    debugDumpObjects();
                    break;
                case 6:
                    System.out.println("BYE!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getUserChoice() {
        int choice = -1;
        while (choice < 1 || choice > 6) {
            try {
                System.out.print("> ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume invalid input
            }
        }
        return choice;
    }

    private static String getBrakeValue() {
        String brake_type_value = "";
        while (!brake_type_value.equals("Rim") && !brake_type_value.equals("Drum")
                && !brake_type_value.equals("Disc")) {
            System.out.print("Choose Bike's brake type (Rim, Disc, Drum):    ");
            brake_type_value = scanner.nextLine();
        }
        return brake_type_value;
    }

    private static int getWheelValue() {
        int wheel_size_value = -1;
        while (wheel_size_value < 1 || wheel_size_value > 50) {
            try {
                System.out.print("Enter Bike's wheel size (from 1 to 50 inches): ");
                wheel_size_value = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } finally {
                scanner.nextLine();
            }
        }
        return wheel_size_value;
    }

    private static int getIDValue() {
        int choice = -1;
        while (choice < 0) {
            try {
                System.out.print("> ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return choice;
    }

    private static int getAttributeValue() {
        int choice = -1;
        while (choice < 0) {
            try {
                System.out.print("> ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return choice;
    }

    private static void listBikes() {
        if (bikeList.isEmpty()) {
            System.out.println();
            System.out.println("No bikes to display.");
        } else {
            System.out.println();
            System.out.println("******************************************************************************");
            System.out.println("*                                 List of Bikes                              *");
            System.out.println("******************************************************************************");
            System.out.println("|  ID  |      Owner     |      Type      |   Serial   |  Brake  | Wheel Size |");
            for (Bike bike : bikeList) {
                int id = bike.getBikeID();
                String owner = bike.getOwner();
                String type = bike.getType();
                String serial = bike.getSerialNumber();
                String brake = bike.getBrakeType();
                int wheels = bike.getWheelSize();
                System.out.printf("| %-4d | %-14s | %-14s | %-10s | %-7s | %-10d |", id, owner, type, serial, brake,
                        wheels);
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void addNewBike() {
        System.out.print("Enter Bike owner name:                         ");
        String owner = scanner.nextLine();
        System.out.print("Enter Bike type:                               ");
        String type = scanner.nextLine();
        System.out.print("Enter Bike's serial number (letters/numbers):  ");
        String serialNumber = scanner.nextLine();
        String brakeType = getBrakeValue();
        int wheelSize = getWheelValue();

        Bike newBike = new Bike(owner, wheelSize, brakeType, type, serialNumber);
        bikeList.add(newBike);
    }

    private static void removeBike() {
        if (bikeList.isEmpty()) {
            System.out.println();
            System.out.println("No bikes to remove.");
            return;
        }

        System.out.println("Enter ID to remove (0 to cancel):");
        for (Bike bike : bikeList) {
            System.out.println(bike.getBikeID() + ": " + bike.getOwner());
        }

        int choice = getIDValue();
        if (choice == 0) {
            System.out.println("Cancelled removal.");
        } else {
            boolean found = false;
            for (Bike bike : bikeList) {
                if (bike.getBikeID() == choice) {
                    bikeList.remove(bike);
                    found = true;
                    System.out.println("Bike with ID " + choice + " has been removed.");
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid bike ID. Removal failed.");
            }
        }
    }

    private static void changeBikeAttribute() {
        if (bikeList.isEmpty()) {
            System.out.println();
            System.out.println("No bikes to change attributes.");
            return;
        }

        System.out.println("Enter ID to change attributes (0 to cancel):");
        for (Bike bike : bikeList) {
            System.out.println(bike.getBikeID() + ": " + bike.getOwner());
        }

        int bikeId = getIDValue();
        if (bikeId == 0) {
            System.out.println("Cancelled attribute change.");
            return;
        }

        Bike bikeToChange = null;
        for (Bike bike : bikeList) {
            if (bike.getBikeID() == bikeId) {
                bikeToChange = bike;
                break;
            }
        }

        if (bikeToChange == null) {
            System.out.println("Invalid bike ID. Attribute change failed.");
            return;
        }

        System.out.println("Which attribute would you like to change?");
        System.out.println("1. Owner");
        System.out.println("2. Type");
        System.out.println("3. Serial Number");
        System.out.println("4. Brake Type");
        System.out.println("5. Wheel Size");

        int attributeChoice = getAttributeValue();

        switch (attributeChoice) {
            case 1:
                System.out.println("Enter new owner: ");
                String newOwner = scanner.nextLine();
                bikeToChange.setOwner(newOwner);
                System.out.println("Owner updated.");
                break;
            case 2:
                System.out.println("Enter new type: ");
                String newType = scanner.nextLine();
                bikeToChange.setType(newType);
                System.out.println("Type updated.");
                break;
            case 3:
                System.out.println("Enter new serial number: ");
                String newSerialNumber = scanner.nextLine();
                bikeToChange.setSerialNumber(newSerialNumber);
                System.out.println("Serial number updated.");
                break;
            case 4:
                System.out.println("Prepare to enter new brake type...");
                String newBrakeType = getBrakeValue();
                bikeToChange.setBrakeType(newBrakeType);
                System.out.println("Brake type updated.");
                break;
            case 5:
                System.out.println("Prepare to enter new wheel size...");
                int newWheelSize = getWheelValue();
                bikeToChange.setWheelSize(newWheelSize);
                System.out.println("Wheel size updated.");
                break;
            default:
                System.out.println("Invalid attribute choice. No changes made.");
                break;
        }
    }

    private static void debugDumpObjects() {
        System.out.println("All Bike objects:");
        for (Bike bike : bikeList) {
            System.out.println(bike.getBikeID() + ". " + bike.toString());
        }
    }
}
