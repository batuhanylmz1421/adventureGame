package game;

import game.locations.Location;
import game.locations.battle.Cave;
import game.locations.battle.Forest;
import game.locations.battle.Mine;
import game.locations.battle.River;
import game.locations.normal.SafeHouse;
import game.locations.normal.ToolStore;
import game.player.Player;

import java.util.Scanner;

public class Game {

    private Player player;
    private Location location;

    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Adventure Game!");
        System.out.println("You need to collect 3 award to finish this game!\n");
        System.out.print("Please enter your name : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Welcome " + player.getName() + "!");

        // Choosing character
        player.selectChar();

        // Choosing Location
        while (true) {
            player.printPlayerInfo();

            // Location selecting menu
            Location location = null;

            // Location menu
            System.out.println("---------------------");
            System.out.println("### Locations ###");
            System.out.println("1- Safe House \t-> No death, Heals your health.");
            System.out.println("2- Tool Store \t-> No death, You can buy items in here.");
            System.out.println("3- Cave       \t-> Award: <Food>, There might be zombies.");
            System.out.println("4- Forest     \t-> Award: <Firewood>, There might be vampires.");
            System.out.println("5- River      \t-> Award: <Water>, There might be bears.");
            System.out.println("6- Mine       \t-> There might be snakes.");
            System.out.println("0- Exit Game  \t-> Ends the game.");
            System.out.println("---------------------");

            // User input reading
            System.out.print("-> Please choose a location!:   ");
            int selectLoc = input.nextInt();

            // Input Validation
            while (selectLoc < 0 || selectLoc > 6) {
                System.out.println("Invalid Input!, Please choose valid ID number!");
                System.out.print("-> Please choose a location!:   ");
                selectLoc = input.nextInt();
            }

            // Option selection section
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    if (    player.getInventory().isFood() &&
                            player.getInventory().isFirewood() &&
                            player.getInventory().isWater()
                    ) {
                        System.out.println("YOU WON THE GAME, CONGRATULATIONS!");
                        location = null;
                    }
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Invalid Input!, Please choose valid ID number!");
                    break;
            }

            // Checking if user select Exit
            if (location == null) {
                System.out.println("Thanks for playing!");
                break;
            }
            // Checking if user alive or not
            if (!location.onLocation()) {
                System.out.println("Game over!");
                break;
            }
        }


    }
}
