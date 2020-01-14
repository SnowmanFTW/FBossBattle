package me.snowman.fbossbattle.events;

import me.snowman.fbossbattle.FBossBattle;
import me.snowman.fbossbattle.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class MainGUIEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryView inventory = event.getView();
        ItemStack item = event.getCurrentItem();

        if (inventory.getTitle().equalsIgnoreCase("FBossBattle")) {
            event.setCancelled(true);

            Arena arena = FBossBattle.arenaManager.getArena(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
            arena.addPlayer(player);
            FBossBattle.arenaManager.startArena(arena);
        }
    }
}
