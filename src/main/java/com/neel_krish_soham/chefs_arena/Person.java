package com.neel_krish_soham.chefs_arena;

import java.util.ArrayList;

public class Person {

    ArrayList<String> hand = new ArrayList<>();
    public boolean grill1 = false;
    public boolean grill2 = false;


    public boolean drinks = false;
    public boolean rice = false;
    public boolean fish = false;
    public boolean fish2 = false;
    public boolean ramen = false;
    public boolean tofu = false;
    public boolean meat = false;
    public boolean icecream = false;
    public boolean grill = false;
    public boolean trash = false;
    public boolean tray1 = false;
    public boolean tray2 = false;

    public void clearhand()
    {
        drinks = false;
        rice = false;
        fish = false;
        fish2 = false;
        ramen = false;
        tofu = false;
        meat = false;
        icecream = false;
        grill = false;
        trash = false;
        tray1 = false;
        tray2 = false;

    }
}
