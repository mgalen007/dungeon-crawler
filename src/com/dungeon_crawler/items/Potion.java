package com.dungeon_crawler.items;

public class Potion extends Item {
    public Potion(int xpValue) {
        int assignedRoom = (int) (Math.ceil(Math.random() * 10));
        super(xpValue, assignedRoom);
    }
}
