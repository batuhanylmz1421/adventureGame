package game.locations.battle;

import game.obstacles.Obstacle;
import game.obstacles.Snake;
import game.obstacles.Vampire;
import game.player.Player;

public class Mine extends BattleLoc {

    public Mine(Player player) {
        super(player, "Mine", new Snake(), "item", 5);
    }
}
