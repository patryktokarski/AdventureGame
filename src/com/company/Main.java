package com.company;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static MapInitializer locations = new MapInitializer();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Player player = new Player("TestPlayer");

        int loc = 1;
        boolean isDoorOpen = false;
        String option;
        printOptions();

        while(true) {
            Location currentLocation = locations.get(loc);
            if(currentLocation.inFrontOfDoor() || isDoorOpen == true) {
                System.out.println("You escaped form the scary house. Congratulations!");
                break;
            }
            System.out.println("Enter option");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    while(true) {
                        currentLocation = locations.get(loc);
                        System.out.println(currentLocation.getDescription());
                        if(currentLocation.inFrontOfDoor()) {
                            if(player.hasKey()) {
                                System.out.println("Opening door.");
                                isDoorOpen = true;
                                break;
                            } else {
                                System.out.println("You need a key to open the door.");
                            }
                        }
                        Map<String, Integer> exits = locations.get(loc).getExits();
                        System.out.println("Select one of exits or press \"o\" to go to options.");
                        System.out.println("Available exits are: ");
                        for (String exit : exits.keySet()) {
                            System.out.print(exit + ",");
                        }
                        System.out.println();
                        String direction = scanner.nextLine().toUpperCase();
                        if(exits.containsKey(direction)) {
                            loc = exits.get(direction);
                        } else if (direction.equals("O")) {
                            break;
                        } else {
                            System.out.println("You cannot go this direction");
                        }
                    }
                    break;
                case "2":
                    player.listItems();
                    break;
                case "3":
                    if(currentLocation.getItems().isEmpty()) {
                        System.out.println("No items here found in this location.");
                    } else {
                        currentLocation.listItems();
                        while(true) {
                            System.out.println("Enter name of element that you want to pick or " +
                                    "press \"o\" to go to options.");
                            String request = scanner.nextLine();
                            if(request.equals("o")) {
                                break;
                            } else {
                                if(pickItem(player, currentLocation, request)) {
                                    if(currentLocation.getItems().isEmpty()) {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;
                case "4":
                    if(player.getItems().isEmpty()) {
                        System.out.println("Your backpack is empty.");
                    }
                    while(true) {
                        System.out.println("Enter item name that you want to drop here," +
                                           " or select \"o\" to go to options.");
                        String request = scanner.nextLine();
                        if(request.equals("o")) {
                            break;
                        } else {
                            if(dropItem(player, currentLocation, request)) {
                                System.out.println("Item dropped.");
                                break;
                            } else {
                                System.out.println("No such item in backpack.");
                            }
                        }
                    }
                    break;
                case "5":
                    printOptions();
                    break;
                case "6":
                    System.out.println("Shutting down. GoodBye!");
                    return;
                default:
                    System.out.println("Wrong option. Try again");
            }
        }
    }

    private static void printOptions() {
        System.out.println("\nAvailable options:\n" +
                "\t1 - continue walking\n" +
                "\t2 - show items in bakcpack\n" +
                "\t3 - pick an item\n" +
                "\t4 - drop here an item\n" +
                "\t5 - print options\n" +
                "\t6 - quit");
    }

    private static boolean pickItem(Player player, Location location, String itemName) {
        Item item = location.queryItem(itemName);
        if(item != null) {
            if(player.addItem(item)) {
                location.removeItem(item);
                System.out.println("Item added.");
                return true;
            } else {
                System.out.println("Your backpack is full. " +
                        "In case to pick this element your have to throw another out.");
                return false;
            }
        } else {
            System.out.println("No such item found.");
            return false;
        }
    }

    private static boolean dropItem(Player player, Location location, String itemName) {
        Item item = player.queryItem(itemName);
        if(item != null) {
            player.removeItem(item);
            location.addItem(item);
            return true;
        }
        return false;
    }
}
