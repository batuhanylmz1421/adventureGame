package game.locations;

import game.player.Player;

public abstract class Location {
    private Player player;
    private String name;

    void location() {
        ;
    }

    public abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
