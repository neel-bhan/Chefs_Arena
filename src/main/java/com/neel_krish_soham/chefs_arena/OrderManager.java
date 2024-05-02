package com.neel_krish_soham.chefs_arena;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class OrderManager {
    public HashMap<String, Boolean> orders;
    public String[] possibleItems = {"drinks", "sushi1", "sushi2", "ramen", "tofu", "meat", "icecream"};
    public Random rand = new Random();

    public OrderManager() {
        this.orders = new HashMap<String, Boolean>();
    }

    public void generateOrder() {
        HashMap<String, Boolean> newOrder = new HashMap<>();
        int itemsCount = rand.nextInt(3) + 1; // Generates between 1 and 3 items per order.

        for (int i = 0; i < itemsCount; i++) {
            String item = possibleItems[rand.nextInt(possibleItems.length)];
            newOrder.put(item, false); // All items start as not completed.
        }

        orders = newOrder;
    }

 

    public boolean isOrderComplete() {
        return !orders.containsValue(false);
    }


    public HashMap<String, Boolean> getOrders() {
        return orders;
    }

    // M
    }