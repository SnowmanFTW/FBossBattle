package me.snowman.fbossbattle.arena;

import me.snowman.fbossbattle.FBossBattle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Arena {
    private String name;
    private Location location;
    private ArrayList<Player> players = new ArrayList<>();
    private states state;

    public Arena(String name) {
        this.name = name;
        this.state = states.EDIT;
    }

    public Arena(String name, Location location) {
        this.name = name;
        this.location = location;
        if (location == null) {
            this.state = states.EDIT;
        } else {
            this.state = states.OPEN;
        }
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        FBossBattle.fileManager.getArenas().set(name + ".Location", location);
        FBossBattle.fileManager.saveArenas();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.teleport(location);
    }

    public void addPlayers(ArrayList<Player> players) {
        players.addAll(players);
        players.forEach(player -> player.teleport(location));
    }

    public states getState() {
        return state;
    }

    public void setState(states state) {
        this.state = state;
    }

    public enum states {OPEN, EDIT, CLOSED}
}
