package game.locations.battle;

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
        if(obsNumber > 1)
            System.out.println("Carefull, There are "+ obsNumber + " " + this.getObstacle().getName() + " here!");
        else
            System.out.println("Carefull, There is "+ obsNumber + " " + this.getObstacle().getName() + " here!");

        // Choose an action
        System.out.print("<F>ight or <S>pare : ");
        String selectCase = input.nextLine();
        // Formatting to Upper Case
        selectCase = selectCase.toUpperCase();

        if(selectCase.equals("F") && combat(obsNumber)) {
            // Fight section
            System.out.println("You cleared the " + this.getName() + "!");

            // Earned award
            if(this.award == "food"){
                System.out.println("<You earn the award " + this.award + ">");
                this.getPlayer().getInventory().setFood(true);
            }
            else if(this.award == "firewood") {
                System.out.println("<You earn the award " + this.award + ">");
                this.getPlayer().getInventory().setFirewood(true);
            }
            else if(this.award == "water") {
                System.out.println("<You earn the award " + this.award + ">");
                this.getPlayer().getInventory().setWater(true);
            }
            return true;
        }
        else {
            System.out.println("<You run away!>");
        }

        // Checking the player dead or not.
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You are DEAD!");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber) {
        Random random = new Random();

        for (int i=1; i<=obsNumber ;i++) {
            // Setting original obstacle health
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());

            // Printing stats.
            playerStats();
            obstacleStats(i);

            // %50 Chance to enemy hits first
            System.out.println(random.nextInt(2) + 1);
            if (random.nextInt(2) + 1 % 2 == 0) {
                System.out.println(this.getObstacle().getName() + " hits you!");
                int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getArmor().getBlock();
                if(obstacleDamage < 0) {
                    System.out.println(this.getObstacle().getName() + " try to hit you but your armor protected you.");
                    obstacleDamage = 0;
                }
                this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                afterHit();
            }

            // Fighting until someone dies.
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                // Getting action input
                System.out.print("<H>it or <R>un: ");
                String selectCombat = input.nextLine().toUpperCase();

                // if player selects hit option
                if(selectCombat.equals("H")) {
                    System.out.println("You hit!");
                    // Reducing enemy health and printing details
                    getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();

                    // If enemy is alive or not
                    if(this.getObstacle().getHealth() > 0) {
                        // Enemy attack turn
                        System.out.println();
                        System.out.println(this.getObstacle().getName() + " hits you!");

                        // Enemy damage and Armor calculations
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getArmor().getBlock();
                        if(obstacleDamage < 0) {
                            System.out.println(this.getObstacle().getName() + " try to hit you but your armor protected you.");
                            obstacleDamage = 0;
                        }
                        // Reducing player health and printing details
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                }
                else {
                    return false;
                }

            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                // Player won the battle
                System.out.println("<You killed " + this.getObstacle().getName() + "!>");
                System.out.println("<You earned " + this.getObstacle().getAward() + " money!>");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Current Money: " + this.getPlayer().getMoney());
            }
            else {
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
        System.out.println("# "+ i +"."+ this.obstacle.getName() + " Stats:");
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
