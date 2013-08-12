package com.relicum.antibreaking.listeners;

import com.relicum.antibreaking.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Deals with block breaking
 *
 * @author Relicum
 * @version 0.1
 */
public class MyBlockBreak implements Listener {

    public Main plugin;

    public MyBlockBreak(Main pl) {

        plugin = pl;

    }

    @EventHandler(ignoreCancelled = false)
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        String wo = p.getWorld().getName().toLowerCase();
        //If no record found break is allowed
        if (!plugin.getInstance().getworldP().contains(wo + "break")) {
            return;
        }

        if (!p.isOp() || (!p.hasPermission("antibreaking.break.bypass." + wo))) {
            p.sendMessage(ChatColor.DARK_RED + "You do not have permission to break blocks in world " + wo);
            e.setCancelled(true);

        }


    }
}
