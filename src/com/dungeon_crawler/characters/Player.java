package com.dungeon_crawler.characters;

public class Player extends Character {
    private int currentRoom;

    public Player(String name) {
        super(name, 100_000);
        this.currentRoom = 1;
    }

    public void getInfo() {
        System.out.println("Player name: " + this.getName());
        System.out.println("Current room: " + currentRoom);
        System.out.println("XP: " + this.getXp());
    }

    public void attack(Monster target, int intensity) {
        target.setXp(target.getXp() - intensity);
        System.out.println("Dropped monster " + target.getName() + "'s XP to " + target.getXp());
    }

    public void nextRoom() {
        currentRoom++;
    }
}
