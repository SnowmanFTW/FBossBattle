package me.snowman.fbossbattle.arena;

import me.snowman.fbossbattle.FBossBattle;
import me.snowman.fbossbattle.managers.FileManager;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class ArenaManager {
    private final FileManager fileManager = FBossBattle.fileManager;
    private Set<Arena> arenas = new HashSet<>();

    public void createArena(Arena arena) {
        arenas.add(arena);
        saveArena(arena);
    }

    public void deleteArena(Arena arena) {
        arenas.remove(arena);
        fileManager.getArenas().set(arena.getName(), null);
        fileManager.saveArenas();
    }

    public Arena getArena(Player player) {
        return arenas.stream().filter(arena -> arena.getPlayers().contains(player)).findFirst().orElse(null);
    }

    public Arena getArena(String name) {
        return arenas.stream().filter(arena -> arena.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Set<Arena> getArenas() {
        return arenas;
    }

    public void saveArenas() {
        arenas.forEach(this::saveArena);
    }

    public void saveArena(Arena arena) {
        fileManager.getArenas().set(arena.getName() + ".Location", null);
        fileManager.saveArenas();
    }

    public void loadArenas() {
        for (String key : FBossBattle.fileManager.getArenas().getKeys(false)) {
            createArena(new Arena(key, FBossBattle.fileManager.getArenas().getLocation(key + ".Location")));
        }
    }

    public enum states {OPEN, EDIT, CLOSED}
}
