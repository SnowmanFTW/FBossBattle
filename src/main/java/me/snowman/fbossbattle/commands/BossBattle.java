package me.snowman.fbossbattle.commands;

import me.snowman.fbossbattle.FBossBattle;
import me.snowman.fbossbattle.arena.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BossBattle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String prefix = FBossBattle.messagesManager.getMessage("Prefix") + " ";
        if (args.length == 0) {
            if (sender instanceof Player) {
                FBossBattle.guiManager.openMainGUI((Player) sender);
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            for (String message : FBossBattle.fileManager.getMessages().getStringList("Help")) {
                sender.sendMessage(FBossBattle.messagesManager.color(message));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (args.length == 2) {
                FBossBattle.arenaManager.createArena(new Arena(args[1]));
                sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("Created").replace("%arena%", args[1]));
                return true;
            }
            sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("Create"));
            return true;
        }

        if (args[0].equalsIgnoreCase("edit")) {
            if (args.length < 2) {
                for (String message : FBossBattle.fileManager.getMessages().getStringList("HelpEdit")) {
                    sender.sendMessage(FBossBattle.messagesManager.color(message));
                }
                return true;
            }
            if (args[1].equalsIgnoreCase("setEdit")) {
                Arena arena = FBossBattle.arenaManager.getArena(args[2]);
                arena.setState(Arena.states.EDIT);
                sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("StateChanged").replace("%arena%", args[2]).replace("%state%", "EDIT"));
            }
            if (args[1].equalsIgnoreCase("setOpen")) {
                Arena arena = FBossBattle.arenaManager.getArena(args[2]);
                arena.setState(Arena.states.OPEN);
                sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("StateChanged").replace("%arena%", args[2]).replace("%state%", "OPEN"));
            }
            if (args[1].equalsIgnoreCase("setClosed")) {
                Arena arena = FBossBattle.arenaManager.getArena(args[2]);
                arena.setState(Arena.states.CLOSED);
                sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("StateChanged").replace("%arena%", args[2]).replace("%state%", "CLOSED"));
            }
            if (args[1].equalsIgnoreCase("setSpawn")) {
                if (sender instanceof Player) {
                    Arena arena = FBossBattle.arenaManager.getArena(args[2]);
                    arena.setLocation(((Player) sender).getLocation());
                    sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("SpawnChanged").replace("%arena%", args[2]));
                }
            }
        }

        if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage(FBossBattle.messagesManager.getMessage("ListHeader"));
            for (Arena arena : FBossBattle.arenaManager.getArenas()) {
                sender.sendMessage(FBossBattle.messagesManager.getMessage("ListArena").replace("%arena%", arena.getName()));
            }
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if (args.length == 2) {
                sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("Deleted").replace("%arena%", args[1]));
                return true;
            }
            sender.sendMessage(prefix + FBossBattle.messagesManager.getMessage("Delete"));
            return true;
        }
        return true;
    }
}
