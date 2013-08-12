package com.relicum.antibreaking;

import com.relicum.antibreaking.listeners.MyBlockBreak;
import com.relicum.antibreaking.listeners.MyBlockPlace;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Holds the list of world with negative perms
     */
    private ArrayList<String> worldPerms = new ArrayList<>();

    public void onEnable() {

        main = this;
        instance = this;
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        this.loadPerms();

        pm.registerEvents(new MyBlockBreak(this), this);
        pm.registerEvents(new MyBlockPlace(this), this);

        CommandExc ce = new CommandExc(this);
        getCommand("antireload").setExecutor(ce);
        getCommand("setplace").setExecutor(ce);


    }


    public void onDisable() {


    }

    public Main getInstance() {

        return instance;
    }

    /**
     * Returns Map
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getworldP() {

        return worldPerms;
    }

    public void loadPerms() {
        ArrayList<String> per = new ArrayList<>();
        Set<String> s = getConfig().getConfigurationSection("Worlds").getKeys(true);
        for (String k : s) {
            List<?> res = getConfig().getList("Worlds." + k);
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i).toString());
                if (res.get(i).toString().equals("place")) {
                    per.add(k.toLowerCase() + "place");
                } else if (res.get(i).toString().equals("break")) {
                    per.add(k.toLowerCase() + "break");
                }
            }


        }

        worldPerms = per;
        System.out.println(s.toString());
        System.out.println(per.toString());
    }
}
