package com.relicum.antibreaking.listeners;

import com.relicum.antibreaking.Main;
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
public class MyBlockBreak implements Listener {

    public Main plugin;
    public Map<String, Object> perms;

    public MyBlockBreak(Main pl) {

        plugin = pl;
        perms = plugin.getInstance().getworldP();
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        String wo = p.getWorld().getName();

        System.out.println("Block Event called");
        if (p.isOp())
            return;
        if (!wo.equalsIgnoreCase("world")) {
            e.setCancelled(true);
        }

        return;


    }
}
