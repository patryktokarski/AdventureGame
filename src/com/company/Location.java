package com.company;

import java.util.HashMap;
import java.util.Map;

public class Location extends ItemHandler {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
        this.exits = new HashMap<>();

        super.items = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(exits);
    }

    public Map<String, Item> getItems() {
        return new HashMap<>(items);
    }

    public void addExits(String direction, Integer locationID) {
        this.exits.put(direction, locationID);
    }

    public void addItem(Item item) {
        this.items.put(item.getName(), item);
    }

    public void listItems() {
        int i = 1;
        System.out.println("Items laying around: ");
        for (String itemName : items.keySet()) {
            System.out.println(i + ". " + itemName);
            i++;
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

    public boolean inFrontOfDoor() {
        if(this.locationID == 10) {
            return true;
        }
        return false;
    }
}
