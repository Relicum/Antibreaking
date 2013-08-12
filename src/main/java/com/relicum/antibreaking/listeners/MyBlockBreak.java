package com.relicum.antibreaking.listeners;

import com.relicum.antibreaking.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;

/**
 * Deals with block breaking
 *
 * @author Relicum
 * @version 0.1
 */
public class MyBlockBreak implements Listener {

    public Main plugin;
    public Map<String, Object> perms;

    public MyBlockBreak(Main pl) {

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

        boolean res = (boolean) perms.get("break");


        if (p.isOp() || p.hasPermission("antibreaking.bypass." + wo))
            return;
        if (!res) {
            p.sendMessage(ChatColor.DARK_RED + "You do not have permission to break blocks in world " + wo);
            e.setCancelled(true);
        }


    }
}
