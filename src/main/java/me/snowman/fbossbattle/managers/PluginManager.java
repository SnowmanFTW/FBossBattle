package me.snowman.fbossbattle.managers;

import me.snowman.fbossbattle.FBossBattle;
import me.snowman.fbossbattle.commands.BossBattle;

public class PluginManager {

    private final FBossBattle plugin = FBossBattle.getPlugin(FBossBattle.class);

    public FBossBattle getPlugin() {
        return plugin;
    }

    public void registerCommands() {
        plugin.getCommand("bossbattle").setExecutor(new BossBattle());
    }
}
