package me.snowman.fbossbattle.managers;

import me.snowman.fbossbattle.FBossBattle;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class MessagesManager {

    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public List<String> color(List<String> list) {
        return list.stream().map(this::color).collect(Collectors.toList());
    }

    public String getMessage(String message) {
        return color(FBossBattle.fileManager.getMessages().getString(message));
    }
}
