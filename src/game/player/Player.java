package game.player;

import game.items.Armor;
import game.items.Weapon;
import game.characters.Archer;
import game.characters.GameChar;
import game.characters.Knight;
import game.characters.Samurai;

import java.util.Scanner;

public class Player {

    private String charName;
    private String name;
    private int damage;
    private int health;
    private int defaultHealth;
    private int money;
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {
        GameChar[] gameChars = {new Samurai(), new Archer(), new Knight()};

        // Character selecting menu
        System.out.println("---------------------");
        System.out.println("# Characters #");
        for (GameChar gameChar : gameChars) {
            System.out.println(gameChar.getId()
                    + "- Character: " + gameChar.getName()
                    + "\t Damage: " + gameChar.getDamage()
                    + "\t Health: " + gameChar.getHealth()
                    + "\t Money: " + gameChar.getMoney()
            );
        }
        System.out.println("---------------------");

        // Getting user input
        System.out.print("-> Please choose a character! (Type only ID Number of character!):   ");
        int charId = input.nextInt();

        // Input Validation
        while (charId < 1 || charId > 3) {
            System.out.println("Invalid Input!, Please choose valid ID number!");
            System.out.print("-> Please choose a character! (Type only ID Number of character!):   ");
            charId = input.nextInt();
        }

        // Character initialization
        switch (charId) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
                break;
        }
    }

    public void initPlayer(GameChar gameChar) {
        // Character initilizer
        this.setName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setDefaultHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
    }

    public void printPlayerInfo() {
        // Printing necessary player information
        System.out.println("---------------------");
        System.out.println(
                this.getName() + ", your stats:" +
                        "\n\t-Weapon: " + this.getInventory().getWeapon().getName() +
                        ", Damage: " + this.getTotalDamage() +
                        ",\n\t-Armor : " + this.getInventory().getArmor().getName() +
                        ", Block: " + this.getInventory().getArmor().getBlock() +
                        ",\n\t-Health: " + this.getHealth() +
                        ",\n\t-Money : " + this.getMoney()
        );
        System.out.println("---------------------");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        // Health cannot be negative
        if (health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Weapon getWeapon() {
        return this.getInventory().getWeapon();
    }

    public Armor getArmor() {
        return this.getInventory().getArmor();
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }
}
