package com.relicum.antibreaking;

import com.relicum.antibreaking.listeners.MyBlockBreak;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    private Map<String, Object> worldPerms = new HashMap<>();

    public void onEnable() {

        main = this;
        instance = this;

        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Set<String> s = getConfig().getConfigurationSection("Worlds").getKeys(false);
        Iterator it = s.iterator();
        for (String k : s) {
            Map<String, Object> in = new HashMap<>();
            Object pl = (Object) getConfig().get("Worlds." + k + ".place");
            Object br = (Object) getConfig().get("Worlds." + k + ".break");
            in.put("place", pl);
            in.put("break", br);
            worldPerms.put(k, in);
        }

        System.out.println(worldPerms.toString());
        pm.registerEvents(new MyBlockBreak(this), this);


    }


    public void onDisable() {


    }

    public Main getInstance() {

        return instance;
    }

    public Map<String, Object> getworldP() {

        return worldPerms;
    }
}
