package me.snowman.fbossbattle.arena;

import me.snowman.fbossbattle.FBossBattle;
import me.snowman.fbossbattle.managers.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
        if (arena.getLocation() != null) {
            fileManager.getArenas().set(arena.getName() + ".Location", arena.getLocation());
            fileManager.saveArenas();
        } else {
            fileManager.getArenas().set(arena.getName() + ".Location", null);
            fileManager.saveArenas();
        }
    }

    public void loadArenas() {
        for (String key : FBossBattle.fileManager.getArenas().getKeys(false)) {
            createArena(new Arena(key, FBossBattle.fileManager.getArenas().getLocation(key + ".Location")));
        }
    }

    public void startArena(Arena arena) {
        arena.setState(Arena.states.CLOSED);
        new BukkitRunnable() {
            int timer = 10;
            String message;

            @Override
            public void run() {
                if (timer == 0) {
                    cancel();
                    return;
                }
                if (timer > 5) {
                    message = FBossBattle.messagesManager.color("&a" + timer);
                } else if (timer > 2 && timer < 6) {
                    message = FBossBattle.messagesManager.color("&6" + timer);
                } else {
                    message = FBossBattle.messagesManager.color("&4" + timer);
                }
                arena.getPlayers().forEach(player -> player.sendTitle(message, ""));
                timer--;
            }
        }.runTaskTimerAsynchronously(FBossBattle.pluginManager.getPlugin(), 0, 20);
    }

    public enum states {OPEN, EDIT, CLOSED}
}
