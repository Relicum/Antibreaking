package com.relicum.antibreaking.listeners;

import com.relicum.antibreaking.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;

/**
 * Antibreaking
 *
 * @author Relicum
 * @version 0.1
 */
public class MyBlockPlace implements Listener {

    public Main plugin;
    public Map<String, Object> perms;

    public MyBlockPlace(Main pl) {

        plugin = pl;

    }

    @EventHandler(ignoreCancelled = false)
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        String wo = p.getWorld().getName();
        //If no record found break is allowed
        if (!plugin.getInstance().getworldP().containsKey(wo)) {
            return;
        }
        perms = (Map<String, Object>) plugin.getInstance().getworldP().get(wo);

        boolean res = (boolean) perms.get("place");


        if (p.isOp() || p.hasPermission("antibreaking.place.bypass." + wo))
            return;
        if (!res) {
            p.sendMessage(ChatColor.DARK_RED + "You do not have permission to place blocks in world " + wo);
            e.setCancelled(true);
        }


    }
}
