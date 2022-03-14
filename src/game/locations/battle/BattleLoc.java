package game.locations.battle;

import game.items.Armor;
import game.items.Item;
import game.items.Weapon;
import game.locations.Location;
import game.obstacles.Obstacle;
import game.player.Player;

import java.util.Locale;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        // printing location info
        System.out.println("Now you are here: " + this.getName());
        if (obsNumber > 1)
            System.out.println("Carefull, There are " + obsNumber + " " + this.getObstacle().getName() + " here!");
        else
            System.out.println("Carefull, There is " + obsNumber + " " + this.getObstacle().getName() + " here!");

        // Choose an action
        System.out.print("<F>ight or <S>pare : ");
        String selectCase = input.nextLine();
        // Formatting to Upper Case
        selectCase = selectCase.toUpperCase();

        if (selectCase.equals("F") && combat(obsNumber)) {
            // Fight section
            System.out.println("You cleared the " + this.getName() + "!");

            // Earned award
            if (this.award == "food") {
                System.out.println("<You earn the award " + this.award + ">");
                this.getPlayer().getInventory().setFood(true);
            } else if (this.award == "firewood") {
                System.out.println("<You earn the award " + this.award + ">");
                this.getPlayer().getInventory().setFirewood(true);
            } else if (this.award == "water") {
                System.out.println("<You earn the award " + this.award + ">");
                this.getPlayer().getInventory().setWater(true);
            } else if (this.award == "item") {
                // Earn random item
                System.out.println("<You cleared this Mine!, There is no extra Award.>");
                System.out.println("You can explore other Mines.");
            }
            return true;
        } else {
            System.out.println("<You run away!>");
        }

        // Checking the player dead or not.
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You are DEAD!");
            return false;
        }

        return true;
    }

    private void getRandomItemDrop() {
        Random random = new Random();
        // Randomly getting number to calculate which item player will get.
        int itemNumber = random.nextInt(20) + 1;

        if (itemNumber > 0 && itemNumber <= 3) { // Player gets Weapon (by 15% chance)
            Weapon droppedWeapon = null;
            int weaponChanceNumber = random.nextInt(10) + 1;
            // Possibilities:
            // Pistol -> 50%
            // Sword  -> 30%
            // Rifle  -> 20%
            if (weaponChanceNumber > 0 && weaponChanceNumber <= 5) {
                // Player earns Pistol.
                droppedWeapon = Weapon.getWeaponObjById(1);
            } else if (weaponChanceNumber <= 8) {
                // Player earns Sword.
                droppedWeapon = Weapon.getWeaponObjById(2);
            } else if (weaponChanceNumber <= 10) {
                // Player earns Rifle.
                droppedWeapon = Weapon.getWeaponObjById(3);
            }
            // print the dropped weapon
            System.out.println("-> " + droppedWeapon.getName() + " is dropped!");

            // Check If the player's weapon greater than the dropped weapon or not.
            if (this.getPlayer().getInventory().getWeapon().getId() < droppedWeapon.getId()) {
                System.out.println("Your weapon upgraded to " + droppedWeapon.getName());
                this.getPlayer().getInventory().setWeapon(droppedWeapon);
            } else if (this.getPlayer().getInventory().getWeapon().getId() == droppedWeapon.getId()) {
                System.out.println("Your have the same weapon with the dropped " + droppedWeapon.getName() + ".");
                System.out.println("Your weapon did not change.");
            } else {
                System.out.println("Your weapon better than dropped " + droppedWeapon.getName() + ".");
                System.out.println("Your weapon did not change. ");
            }
        } else if (itemNumber <= 6) { // Player gets Armor (by 15% chance)
            Armor droppedArmor = null;
            int armorChanceNumber = random.nextInt(10) + 1;

            // Possibilities:
            // Light  -> 50%
            // Medium -> 30%
            // Heavy  -> 20%
            if (armorChanceNumber > 0 && armorChanceNumber <= 5) {
                // Player earns Light Armor.
                droppedArmor = Armor.getArmorObjById(1);
            } else if (armorChanceNumber <= 8) {
                // Player earns Medium Armor.
                droppedArmor = Armor.getArmorObjById(2);
            } else if (armorChanceNumber <= 10) {
                // Player earns Heavy Armor.
                droppedArmor = Armor.getArmorObjById(3);
            }
            // print the dropped armor
            System.out.println("-> " + droppedArmor.getName() + " is dropped!");

            // Check If the player's weapon greater than the dropped weapon or not.
            if (this.getPlayer().getInventory().getArmor().getId() < droppedArmor.getId()) {
                System.out.println("Your armor upgraded to " + droppedArmor.getName());
                this.getPlayer().getInventory().setArmor(droppedArmor);
            } else if (this.getPlayer().getInventory().getArmor().getId() == droppedArmor.getId()) {
                System.out.println("Your have the same armor with the dropped " + droppedArmor.getName() + ".");
                System.out.println("Your armor did not change.");
            } else {
                System.out.println("Your armor better than dropped " + droppedArmor.getName() + ".");
                System.out.println("Your armor did not change.");
            }
        } else if (itemNumber <= 11) { // Player gets Money (by 25% chance).
            // The amount of money you can drop: Between 5 and 15.
            int droppedMoneyAmount = random.nextInt(15) + 5;

            // Calculations of 25% chance to drop money.
            int dropChance = random.nextInt(4) + 1;

            if (dropChance <= 1) { // Player earns dropped money.
                // Print the dropped armor
                System.out.println("-> " + droppedMoneyAmount + " money dropped!");
                // Setting new money amount
                this.getPlayer().setMoney(this.getPlayer().getMoney() + droppedMoneyAmount);
                // Print current player money amount
                System.out.println("Now, your balance: " + this.getPlayer().getMoney() + ".");
            } else {
                System.out.println("You earned " + droppedMoneyAmount +
                        " Money, but you lost it :(" +
                        "\n<No Money Earned!>"
                );
            }
        } else if (itemNumber <= 20) { // Player gets Nothing (by 15% chance)
            System.out.println("There is no item to drop.");
        }
    }

    public boolean combat(int obsNumber) {
        Random random = new Random();
        String obsName = this.getObstacle().getName();

        for (int i = 1; i <= obsNumber; i++) {
            // Setting original obstacle health
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());

            // If enemy is Snake, set Snake damage randomly (between 3 and 6)
            if (this.getObstacle().getName() == "Snake") {
                this.getObstacle().setDamage(random.nextInt(6) + 3);
            }

            // Printing stats.
            playerStats();
            obstacleStats(i);

            // %50 Chance to enemy hits first
            if ((random.nextInt(2) + 1) % 2 == 0) {
                // Enemy damage and Armor calculations
                int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getArmor().getBlock();
                if (obstacleDamage < 0) {
                    // Enemy Miss!
                    System.out.println(this.getObstacle().getName() + " try to hit you but your armor protected you.");
                    obstacleDamage = 0;
                }
                // Enemy hit successfully!
                // Reducing player health and printing details
                System.out.println(this.getObstacle().getName() + " hits you! (-" + obstacleDamage + ")");
                this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                afterHit();
            }

            // Fighting until someone dies.
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                // Getting action input
                System.out.print("<H>it or <R>un: ");
                String selectCombat = input.nextLine().toUpperCase();

                // if player selects hit option
                if (selectCombat.equals("H")) {
                    System.out.println("You hit!");
                    // Reducing enemy health and printing details
                    getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();

                    // If enemy is alive or not
                    if (this.getObstacle().getHealth() > 0) {
                        // Enemy attack turn
                        System.out.println();
                        // Enemy damage and Armor calculations
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            // Enemy Miss!
                            System.out.println(this.getObstacle().getName() + " try to hit you but your armor protected you.");
                            obstacleDamage = 0;
                        }
                        // Enemy hit successfully!
                        // Reducing player health and printing details
                        System.out.println(this.getObstacle().getName() + " hits you! ( -" + obstacleDamage + " )");
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }

            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                // Player won the battle
                System.out.println("<You killed " + this.getObstacle().getName() + "!>");
                // If enemy is "Snake" than don't get money directly. Instead, drop random item.
                if (obsName == "Snake") {
                    // Drop random item for each Snake.
                    getRandomItemDrop();
                } else {
                    System.out.println("<You earned " + this.getObstacle().getAward() + " money!>");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Current Money: " + this.getPlayer().getMoney());
                }
            } else {
                // Player lose the battle
                return false;
            }
        }
        return true;
    }

    private void afterHit() {
        // Print details after hitting
        System.out.println("-> Your Health: " + this.getPlayer().getHealth());
        System.out.println("-> " + this.getObstacle().getName() + "'s Health: " + this.getObstacle().getHealth());
        System.out.println("----------------");
    }

    protected void playerStats() {
        // Printing necessary player details
        System.out.println("---------------------");
        System.out.println("# Player Stats:");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getWeapon().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor : " + this.getPlayer().getArmor().getName());
        System.out.println("Block : " + this.getPlayer().getArmor().getBlock());
        System.out.println("Money : " + this.getPlayer().getMoney());
    }

    protected void obstacleStats(int i) {
        // Printing necessary obstacle details
        System.out.println("---------------------");
        System.out.println("# " + i + "." + this.obstacle.getName() + " Stats:");
        System.out.println("Health: " + this.obstacle.getHealth());
        System.out.println("Damage: " + this.obstacle.getDamage());
        System.out.println("Award : " + this.obstacle.getAward());
        System.out.println("---------------------");
        System.out.println();

    }

    public int randomObstacleNumber() {
        // Creating random number of obstacle
        Random rnd = new Random();
        return rnd.nextInt(this.maxObstacle) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
