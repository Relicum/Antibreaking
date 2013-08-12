package com.relicum.antibreaking.listeners;

import com.relicum.antibreaking.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Antibreaking
 *
 * @author Relicum
 * @version 0.1
 */
public class MyBlockPlace implements Listener {

    public Main plugin;


    public MyBlockPlace(Main pl) {

        plugin = pl;

    }

    @EventHandler(ignoreCancelled = false)
    public void onPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();
        String wo = p.getWorld().getName().toLowerCase();

        //If no record found break is allowed
        if (!plugin.getInstance().getworldP().contains(wo + "place")) {
            return;
        }

        if (!p.isOp() || (!p.hasPermission("antibreaking.place.bypass." + wo))) {
            p.sendMessage(ChatColor.DARK_RED + "You do not have permission to place blocks in world " + wo);
            e.setCancelled(true);

        }


    }
}
