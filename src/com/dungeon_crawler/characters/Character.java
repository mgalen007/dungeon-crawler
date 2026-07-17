package com.dungeon_crawler.characters;

public abstract class Character {
    protected String name;
    protected int xp;

    public Character(String name, int xp) {
        this.setName(name);
        this.setXp(xp);
    }

    public abstract void getInfo();
    public abstract int getAttackIntensity();

    public boolean isDead() {
        return xp <= 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
