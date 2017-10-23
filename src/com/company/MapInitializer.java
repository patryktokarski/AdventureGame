package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapInitializer implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    static {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")))) {
            while(scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                Integer locationId = Integer.parseInt(data[0]);
                String description = data[1];
                locations.put(locationId, new Location(locationId, description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")))) {
            while(scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                Integer locationId = Integer.parseInt(data[0]);
                Integer direction = Integer.parseInt(data[2]);
                Location location = locations.get(locationId);
                location.addExits(data[1], direction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("items.txt")))) {
            while(scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                Integer locationId = Integer.parseInt(data[0]);
                String itemName = data[1];
                String description = data[2];
                Location location = locations.get(locationId);
                location.addItem(new Item(itemName, description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
