package game.locations.battle;

import game.obstacles.Obstacle;
import game.obstacles.Zombie;
import game.player.Player;

public class Cave extends BattleLoc {

    public Cave(Player player) {
        super(player, "Cave", new Zombie(), "food", 3);
    }
}
