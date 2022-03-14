package game.locations.normal;

import game.player.Player;

public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You are in the safe house.");
        System.out.println("You are healed.");

        // Resetting player health
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());

        return true;
    }
}
