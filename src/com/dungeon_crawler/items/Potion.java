package com.dungeon_crawler.items;

public class Potion extends Item {
    public Potion(int xpValue, int assignedRoom) {
        super(xpValue, assignedRoom);
    }

    @Override
    public void getInfo() {
        System.out.println("Item name: Potion");
        System.out.println("Value: " + this.getXpValue() + "XP");
        System.out.println("Available: " + this.isUsed());
    }
}
