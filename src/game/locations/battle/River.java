package game.locations.battle;

import game.obstacles.Bear;
import game.obstacles.Obstacle;
import game.player.Player;

public class River extends BattleLoc {

    public River(Player player) {
        super(player, "River", new Bear(), "water", 2);
    }
}
