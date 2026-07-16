package com.dungeon_crawler.characters;

public abstract class Monster extends Character {
    protected final int assignedRoom;

    public Monster(String name, int xp, int assignedRoom) {
        super(name, xp);
        this.assignedRoom = assignedRoom;
    }

    public abstract void attack(Player target, int intensity);
}