package game.locations.normal;

import game.locations.Location;

public abstract class NormalLoc extends Location {

    @Override
    public boolean onLocation() {
        return false;
    }
}
