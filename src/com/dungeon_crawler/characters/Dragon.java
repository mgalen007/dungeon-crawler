package com.dungeon_crawler.characters;

public class Dragon extends Monster {
    public Dragon(String name, int assignedRoom) {
        super(name, 250_000, assignedRoom);
    }

    @Override
    public void attack(Player target, int intensity) {
        target.setXp(target.getXp() - intensity);
        System.out.println("Dropped player " + target.getName() + "'s XP to " + target.getXp());
    }

    @Override
    public void getInfo() {
        System.out.println("Dragon name: " + this.getName());
        System.out.println("Assigned room: " + this.assignedRoom);
        System.out.println("XP: " + this.getXp());
    }

    @Override
    public int getAttackIntensity() {
        return (int) (Math.random() * 8_000);
    }
}
