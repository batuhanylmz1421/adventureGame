package game.locations.normal;

import game.items.Armor;
import game.items.Weapon;
import game.player.Player;

public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\nWelcome to the Tool Store!");
        boolean showMenu = true;

        // Shows menu until player selecet exit
        while (showMenu) {
            System.out.println("# Options #");
            System.out.println("1- Weapons");
            System.out.println("2- Armors");
            System.out.println("3- Exit");

            // Getting user input
            System.out.print("Enter option ID number: ");
            int selectedCase = input.nextInt();

            // Input validation
            while (selectedCase < 1 || selectedCase > 3) {
                System.out.println("Invalid Input!, Please choose valid ID number!");
                System.out.print("-> Please choose a option!:   ");
                selectedCase = input.nextInt();
            }

            // Applying selected case
            switch (selectedCase) {
                case 1:
                    // Weapon buy section
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    // Armor buy section
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    // Exit section
                    System.out.println("Thanks for vising our Store!");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        // Printing weapon menu details
        System.out.println("\n----- Weapons -----");
        for (Weapon weapon : Weapon.weapons()) {
            System.out.println(weapon.getId() + "- "
                    + weapon.getName() + " -> "
                    + "<Damage:" + weapon.getDamage()
                    + ", Price:" + weapon.getPrice() + ">"
            );
        }
        System.out.println("0- Go Back.\n");
    }

    public void buyWeapon() {
        System.out.println("Your Money: " + this.getPlayer().getMoney());
        // Getting user input
        System.out.print("Select weapon ID number: ");
        int selectWeaponId = input.nextInt();

        // Input Validation
        while (selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length) {
            System.out.println("Invalid Input!, Please choose valid ID number!");
            System.out.print("----- Please choose a ID!:   ");
            selectWeaponId = input.nextInt();
        }
        // Checking if selected id is not 0,then buy weapon.
        if (selectWeaponId != 0) {
            // Getting weapon object
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);
            if (selectedWeapon != null) {
                // Balance checker
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Not enough money!\n");
                } else {
                    // If player has already bought the same or greater weapon, than print notificaiton.
                    if (this.getPlayer().getWeapon().getId() >= selectedWeapon.getId()) {
                        System.out.println("You have already greater or equal Weapon. Buying skipped!\n");
                    } else {
                        // Weapon buying section
                        System.out.println("-> You bought " + selectedWeapon.getName() + ".");

                        // Calculating balance
                        int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                        this.getPlayer().setMoney(balance);
                        System.out.println("Remaining Money: " + this.getPlayer().getMoney());

                        // Setting weapon to the player
                        System.out.println("Previous Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                        this.getPlayer().getInventory().setWeapon(selectedWeapon);
                        System.out.println("New Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                        System.out.println();
                    }
                }
            }
        }
    }

    public void printArmor() {
        // Printing armor menu details
        System.out.println("\n----- Armors -----");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + "- "
                    + a.getName() + " -> "
                    + "<Block:" + a.getBlock()
                    + ", Price:" + a.getPrice() + ">"
            );
        }
        System.out.println("0- Go Back.\n");
    }

    private void buyArmor() {
        System.out.println("Your Money: " + this.getPlayer().getMoney());
        // Getting user input
        System.out.print("Select armor ID number: ");
        int selectArmorId = input.nextInt();

        // Input Validation
        while (selectArmorId < 0 || selectArmorId > Armor.armors().length) {
            System.out.println("Invalid Input!, Please choose valid ID number!");
            System.out.print("----- Please choose a ID!:   ");
            selectArmorId = input.nextInt();
        }
        // Checking if selected id is not 0,then buy armor.
        if (selectArmorId != 0) {
            // Getting armor object
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);

            if (selectedArmor != null) {
                // Balance checker
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Not enough money!");
                } else {
                    // If player has already bought the same or greater armor, than print notificaiton
                    if (this.getPlayer().getArmor().getId() >= selectedArmor.getId()) {
                        System.out.println("You have already greater or equal Armor. Buying skipped!");
                    } else {
                        // Armor buying section
                        System.out.println("You bought " + selectedArmor.getName() + ".");

                        // Calculating balance
                        int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                        this.getPlayer().setMoney(balance);
                        System.out.println("Remaining Money: " + this.getPlayer().getMoney());

                        // Setting armor to the player
                        System.out.println("Previous Weapon: " + this.getPlayer().getInventory().getArmor().getName());
                        this.getPlayer().getInventory().setArmor(selectedArmor);
                        System.out.println("New Armor: " + this.getPlayer().getInventory().getArmor().getName());
                        System.out.println();
                    }
                }
            }
        }
    }
}
