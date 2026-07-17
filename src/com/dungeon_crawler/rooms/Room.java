package com.dungeon_crawler.rooms;

import java.util.ArrayList;
import com.dungeon_crawler.characters.Monster;
import com.dungeon_crawler.items.Item;

public class Room {
    private final int roomId;
    private final ArrayList<Monster> monsters;
    private final ArrayList<Item> items;

    public Room(
            int roomId,
            ArrayList<Monster> monsters,
            ArrayList<Item> items
    ) {
        this.roomId = roomId;
        this.monsters = new ArrayList<Monster>(monsters);
        this.items = new ArrayList<Item>(items);
    }

    public int getRoomId() {
        return roomId;
    }

    public void getMonsters() {
        System.out.println("Room " + this.getRoomId() + " monsters:");

        for (Monster monster: monsters) {
            System.out.print("[-] ");
            monster.getInfo();
        }

        System.out.println("-----");
    }

    public void getItems() {
        System.out.println("Room " + this.getRoomId() + " items:");

        for (Item item: items) {
            System.out.print("[+] ");
            item.getInfo();
        }

        System.out.println("-----");
    }

    public ArrayList<Monster> getMonsterList() { return monsters; }
    public ArrayList<Item> getItemList() { return items; }
    public void removeMonster(Monster m) { monsters.remove(m); }
    public void removeItem(Item i) { items.remove(i); }
}
