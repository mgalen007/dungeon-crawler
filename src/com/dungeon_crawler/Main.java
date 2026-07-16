package com.dungeon_crawler;

import com.dungeon_crawler.characters.Character;
import com.dungeon_crawler.characters.Monster;
import com.dungeon_crawler.characters.Player;
import com.dungeon_crawler.characters.Goblin;
import com.dungeon_crawler.characters.Dragon;
import com.dungeon_crawler.items.Item;
import com.dungeon_crawler.items.Potion;
import com.dungeon_crawler.rooms.Room;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    private static void banner() {
        System.out.println(" ------");
        System.out.println("< MENU >");
        System.out.println(" ------");
        System.out.println("[1] Get current room info -> room info");
        System.out.println("[2] Get player info -> player info");
        System.out.println("[3] Attack a monster -> attack");
        System.out.println("[4] Take potion -> take potion");
        System.out.println("[5] Exit -> quit");
        System.out.println("--------");
    }

    private static String readCommand() {
        System.out.print("Your move: ");
        String cmd = scanner.nextLine();

        return cmd;
    }

    public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<Room>();

        for (int i = 1; i < 11; i++) {
            ArrayList<Monster> monsters = new ArrayList<Monster>();
            ArrayList<Item> items = new ArrayList<Item>();

            int nbrOfGoblins = (int) (Math.random() * 4);
            int nbrOfDragons = (Math.random() < 0.3) ? 1 : 0;
            int nbrOfPotions = (int) (Math.random() * 3);
            int potionValue = (nbrOfDragons == 1) ? 50_000 : 20_000;

            for (int n = 0; n <= nbrOfGoblins; n++) {
                String name = "Goblin-" + n;
                Goblin goblin = new Goblin(name, i);
                monsters.add(goblin);
            }

            if (nbrOfDragons == 1) {
                String name = "Dragon-" + i;
                Dragon dragon = new Dragon(name, i);
                monsters.add(dragon);
            }

            for (int n = 0; n <= nbrOfPotions; n++) {
                Potion potion = new Potion(potionValue, i);
                items.add(potion);
            }

            rooms.add(new Room(i, monsters, items));
        }

        System.out.println("Initialized 10 rooms");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        System.out.println("Welcome to Dungeon Crawler " + player.getName() + "!");
        System.out.println("Your goal is to finish room 10, you are in room 1");

        banner();
        String cmd = readCommand();

        switch(cmd) {
            case "room info":
                System.out.println("Room info");
                break;
            case "player info":
                System.out.println("Player info");
                break;
            case "attack":
                System.out.println("Attacking");
                break;
            case "take potion":
                System.out.println("Taking potion");
                break;
            case "quit":
                System.out.println("Bye :)");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command!");
                banner();
        }
    }
}