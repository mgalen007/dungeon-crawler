package com.dungeon_crawler.characters;

public class Goblin extends Monster {
    public Goblin(String name, int assignedRoom) {
        super(name, 5_000, assignedRoom);
    }

    @Override
    public void getInfo() {
        System.out.println("Goblin name: " + this.getName());
        System.out.println("Assigned room: " + this.assignedRoom);
    }

    @Override
    public void attack(Player target, int intensity) {
        target.setXp(target.getXp() - intensity);
        System.out.println("Dropped player" + target.getName() + "'s XP to " + target.getXp());
    }

    @Override
    public int getAttackIntensity() {
        return (int) (Math.random() * 3_000);
    }
}
