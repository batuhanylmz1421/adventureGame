package game.locations.battle;

import game.locations.battle.BattleLoc;
import game.obstacles.Vampire;

public class Forest extends BattleLoc {

    @Override
    public boolean onLocation() {
        return false;
    }

    public Forest() {
        new Vampire();
    }
}
