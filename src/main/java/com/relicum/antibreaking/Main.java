package com.relicum.antibreaking;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Antibreaking
 * 
 * @author Relicum
 * @version 0.1
 */
public class Main extends JavaPlugin implements Listener {

    /**
     * Shortcut to PluginManager
     */
    private PluginManager pm = Bukkit.getServer().getPluginManager();

    public void onLoad() {

        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        CommandExc cm = new CommandExc(this);
        getCommand("antireload").setExecutor(cm);
        getCommand("antibreaking").setExecutor(cm);
        getCommand("antiplacing").setExecutor(cm);
        getCommand("antiperms").setExecutor(cm);

        pm.registerEvents(this, this);
    }

    public void onDisable() {

    }

    public void playerJoin(PlayerLoginEvent event) {
        if (!event.getPlayer().isOp()) {

            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Sorry player is not allowed in at the moment");
            event.setKickMessage("No entry");
            event.getPlayer().kickPlayer(event.getKickMessage());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void placeEvent(BlockBreakEvent e) {

    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void BreakEvent(BlockBreakEvent e) {

    }
}
