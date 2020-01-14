package me.snowman.fbossbattle.managers;

import me.snowman.fbossbattle.FBossBattle;
import me.snowman.fbossbattle.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GUIManager {

    private ItemStack arenaItem(Arena arena) {
        ItemStack item;
        if (arena.getState().equals(Arena.states.OPEN)) {
            item = new ItemStack(Material.GREEN_TERRACOTTA, 1);
        } else if (arena.getState().equals(Arena.states.EDIT)) {
            item = new ItemStack(Material.YELLOW_TERRACOTTA, 1);
        } else {
            item = new ItemStack(Material.RED_TERRACOTTA, 1);
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(FBossBattle.messagesManager.color("&a" + arena.getName()));
        ArrayList lore = new ArrayList();
        lore.add(" ");
        if (arena.getPlayers() == null) {
            lore.add("&7Players: &f0");
        } else {
            lore.add("&7Players: &f" + arena.getPlayers().size());
        }
        meta.setLore(FBossBattle.messagesManager.color(lore));
        item.setItemMeta(meta);
        return item;
    }

    public void openMainGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, "FBossBattle");

        for (Arena arena : FBossBattle.arenaManager.getArenas()) {
            gui.addItem(arenaItem(arena));
        }
        player.openInventory(gui);
    }
}
