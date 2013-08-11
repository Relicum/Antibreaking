package com.relicum.antibreaking;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
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
     * Static instance of itself
     */
    public static Main instance = null;

    /**
     * Non Static instance of itself
     */
    Main main;

    /**
     * Shortcut to PluginManager
     */
    private PluginManager pm = Bukkit.getServer().getPluginManager();

    public void onLoad() {

        main = this;
        set(this);
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        pm.registerEvents(this, this);
    }

    private void set(Main ma) {

        if (instance == null) {
            instance = ma;
        }
    }

    public void onDisable() {


    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void placeEvent(BlockBreakEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void BreakEvent(BlockBreakEvent e) {


    }
}
