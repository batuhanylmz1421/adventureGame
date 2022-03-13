package game.locations.battle;

import game.locations.Location;
import game.obstacles.Obstacle;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;

    @Override
    public boolean onLocation() {
        return false;
    }
}
