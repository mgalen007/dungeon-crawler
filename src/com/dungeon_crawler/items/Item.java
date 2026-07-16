package com.dungeon_crawler.items;

public abstract class Item {
    protected int xpValue;
    protected boolean isUsed;
    protected int assignedRoom;

    public Item(int xpValue, int assignedRoom) {
        this.xpValue = xpValue;
        this.isUsed = false;
        this.assignedRoom = assignedRoom;
    }

    public int getXpValue() {
        return this.xpValue;
    }
}
