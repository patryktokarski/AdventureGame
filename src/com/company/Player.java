package com.company;

import java.util.HashMap;
import java.util.Map;

public class Player extends ItemHandler {
    private final String name;

    public Player(String name) {
        this.name = name;
        super.items = new HashMap<>();
        welcomeMessage();
    }

    private void welcomeMessage() {
        System.out.println("Welcome " + this.name +
                ". Look around and try to figure out, how to escape form this house.");
    }

    public Map<String, Item> getItems() {
        return this.items;
    }

    public boolean addItem(Item item) {
        if(isBackpackFull()) {
            return false;
        } else {
            this.items.put(item.getName(), item);
            return true;
        }
    }

    public boolean isBackpackFull() {
        if(getItems().size() >= 2) {
            return true;
        }
        return false;
    }

    public void listItems() {
        if(getItems().isEmpty()) {
            System.out.println("Your backpack is empty.");
        } else {
            System.out.println("Your backpack contains following items:");
            int i = 1;
            for (Item currentItem : items.values()) {
                System.out.println("\t" + i + ". " + currentItem.getName() + " - " + currentItem.getDescription());
                i++;
            }
        }
    }

    public Item queryItem(String searchedItemName) {
        for (String itemName : this.items.keySet()) {
            if(itemName.equals(searchedItemName)) {
                return this.items.get(searchedItemName);
            }
        }
        return null;
    }

    public boolean hasKey() {
        for (String itemName : items.keySet()) {
            if(itemName.equals("key")) {
                return true;
            }
        }
        return false;
    }
}
