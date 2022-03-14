package game.locations.battle;

import game.locations.battle.BattleLoc;
import game.obstacles.Vampire;
import game.player.Player;

public class Forest extends BattleLoc {

    public Forest(Player player) {
        super(player, "Forest", new Vampire(), "firewood", 3);
    }
}
