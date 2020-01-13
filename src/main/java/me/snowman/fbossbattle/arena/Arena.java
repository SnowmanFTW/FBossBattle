package me.snowman.fbossbattle.arena;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Arena {
    private String name;
    private Location location;
    private ArrayList<Player> players;
    private states state;

    public Arena(String name) {
        this.name = name;
        this.state = states.EDIT;
    }

    public Arena(String name, Location location) {
        this.name = name;
        this.location = location;
        this.state = states.EDIT;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addPlayers(ArrayList<Player> players) {
        players.addAll(players);
    }

    public states getState() {
        return state;
    }

    public void setState(states state) {
        this.state = state;
    }

    public enum states {OPEN, EDIT, CLOSED}
}
