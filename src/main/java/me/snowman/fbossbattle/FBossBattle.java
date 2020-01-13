package me.snowman.fbossbattle;

import me.snowman.fbossbattle.arena.ArenaManager;
import me.snowman.fbossbattle.managers.FileManager;
import me.snowman.fbossbattle.managers.MessagesManager;
import me.snowman.fbossbattle.managers.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FBossBattle extends JavaPlugin {
    public static PluginManager pluginManager;
    public static FileManager fileManager;
    public static ArenaManager arenaManager;
    public static MessagesManager messagesManager;

    public void onEnable() {
        loadManagers();
        fileManager.createArenas();
        fileManager.createMessages();
        pluginManager.registerCommands();
        arenaManager.loadArenas();
    }

    public void onDisable() {
        arenaManager.saveArenas();
    }

    public void loadManagers() {
        pluginManager = new PluginManager();
        fileManager = new FileManager();
        arenaManager = new ArenaManager();
        messagesManager = new MessagesManager();
    }
}
