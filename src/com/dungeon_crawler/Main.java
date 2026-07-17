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
import java.util.Optional;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    private static void menu() {
        System.out.println(" ------");
        System.out.println("< MENU >");
        System.out.println(" ------");
        System.out.println("[1] Get current room info -> room info");
        System.out.println("[2] Get player info -> player info");
        System.out.println("[3] Attack a monster -> attack");
        System.out.println("[4] Take potion -> take potion");
        System.out.println("[5] Advance to next room -> next room");
        System.out.println("[6] Exit -> quit");
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

        boolean running = true;

        while (running) {
            menu();
            String cmd = readCommand();
            Room currentRoom = rooms.get(player.getCurrentRoom() - 1);

            switch (cmd) {
                case "room info": {
                    currentRoom.getMonsters();
                    currentRoom.getItems();
                    break;
                }

                case "player info": {
                    player.getInfo();
                    break;
                }

                case "attack": {
                    ArrayList<Monster> monsters = currentRoom.getMonsterList();

                    if (monsters.isEmpty()) {
                        System.out.println("No monsters left in this room");
                        break;
                    }

                    System.out.print("Enter the monster name: ");
                    String targetName = scanner.nextLine();

                    Optional<Monster> target = monsters
                            .stream()
                            .filter(m -> m.getName().equalsIgnoreCase(targetName))
                            .findFirst();

                    if (target.isPresent()) {
                        Monster monster = target.get();
                        int intensity = (int) (Math.random() * 10_000);
                        player.attack(monster, intensity);

                        if (monster.isDead()) {
                            System.out.println("Monster " + monster.getName() + " has been defeated!");
                            currentRoom.removeMonster(monster);
                        } else {
                            int monsterIntensity = (int) (Math.random() * (monster instanceof Goblin ? 3_000 : 8_000));
                            monster.attack(player, monsterIntensity);

                            if (player.isDead()) {
                                System.out.println(monster.getName() + " kills " + player.getName() + "!");
                                System.out.println("Game Over!");
                                running = false;
                            }
                        }

                    } else {
                        System.out.println("Monster not found: " + targetName);
                    }
                    break;
                }

                case "take potion": {
                    ArrayList<Item> items = currentRoom.getItemList();

                    if (items.isEmpty()) {
                        System.out.println("No items available");
                        break;
                    }

                    Optional<Potion> firstPotion = items
                            .stream()
                            .filter(i -> i instanceof Potion)
                            .map(i -> (Potion) i)
                            .findFirst();

                    if (firstPotion.isPresent()) {
                        Potion potion = firstPotion.get();
                        player.takePotion(potion);
                        potion.setIsUsed();
                        currentRoom.removeItem(potion);
                    } else {
                        System.out.println("No potion available");
                    }

                    break;
                }

                case "quit": {
                    System.out.println("Bye :)");
                    running = false;
                    break;
                }

                case "next room": {
                    if (!currentRoom.getMonsterList().isEmpty()) {
                        System.out.println("Kill all monsters first");
                        break;
                    }

                    player.nextRoom();

                    if (player.getCurrentRoom() > 10) {
                        System.out.println("Congratulations! You cleared all the rooms!");
                        running = false;
                    }
                    break;
                }

                default: {
                    System.out.println("Invalid command!");
                    menu();
                }
            }
        }
    }
}