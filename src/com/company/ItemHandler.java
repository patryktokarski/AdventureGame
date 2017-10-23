package com.company;

import java.util.Map;

public abstract class ItemHandler {
    protected Map<String, Item> items;

    public void removeItem(Item item) {
        this.items.remove(item.getName());
    }

    public abstract void listItems();
}
