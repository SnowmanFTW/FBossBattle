package me.snowman.fbossbattle.managers;

import me.snowman.fbossbattle.FBossBattle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private File arenaFile, messagesFile, dataFolder = FBossBattle.pluginManager.getPlugin().getDataFolder();
    private FileConfiguration arenaConfig, messagesConfig;

    public void createArenas() {
        if (!dataFolder.exists()) dataFolder.mkdir();

        arenaFile = new File(dataFolder, "arenas.yml");

        if (!arenaFile.exists()) {
            try {
                arenaFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
        }

        if (arenaConfig == null) arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
    }

    public FileConfiguration getArenas() {
        return arenaConfig;
    }

    public void saveArenas() {
        try {
            arenaConfig.save(arenaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadArenas() {
        arenaFile = new File(dataFolder, "arenas.yml");
        arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
    }

    public void createConfig() {
        FBossBattle.pluginManager.getPlugin().saveDefaultConfig();
    }

    public FileConfiguration getConfig() {
        return FBossBattle.pluginManager.getPlugin().getConfig();
    }

    public void saveConfig() {
        FBossBattle.pluginManager.getPlugin().saveConfig();
    }

    public void createMessages() {
        if (!dataFolder.exists()) dataFolder.mkdir();

        messagesFile = new File(dataFolder, "messages.yml");

        if (!messagesFile.exists()) {
            FBossBattle.pluginManager.getPlugin().saveResource("messages.yml", true);
            messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        }

        if (messagesConfig == null) messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getMessages() {
        return messagesConfig;
    }

    public void saveMessages() {
        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadMessages() {
        messagesFile = new File(dataFolder, "messages.yml");
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
