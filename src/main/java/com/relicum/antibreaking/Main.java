package com.relicum.antibreaking;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Antibreaking
 * 
 * @author Relicum
 * @version 0.1
 */
public class Main extends JavaPlugin implements Listener {

    public List<String> breaking=new ArrayList<>();
    public List<String> placing= new ArrayList<>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this,this);
        getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();



        Map<String, Object> b =  getConfig().getConfigurationSection("break").getValues(true);
        Map<String, Object> p =  getConfig().getConfigurationSection("place").getValues(true);

        for(Map.Entry<String,Object> e: b.entrySet()){
            if(e.getValue()==true){
                breaking.add(e.getKey());
            }
        }

        System.out.println("breaking allowed in num " + breaking.size());

        for(Map.Entry<String,Object> e: p.entrySet()){
            if(e.getValue()==true){
                placing.add(e.getKey());
            }
        }

        System.out.println("placing num is " + placing.size());

        getConfig().set("firstRun",false);
        saveConfig();


        CommandExc cm = new CommandExc(this);
        getCommand("antireload").setExecutor(cm);
        getCommand("antibreak").setExecutor(cm);
        getCommand("antiplace").setExecutor(cm);
        getCommand("antiperm").setExecutor(cm);


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

    public void loadWorldList(){
        Map<String, Object> con = getConfig().getConfigurationSection("worlds").getValues(true);

    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void placeEvent(BlockBreakEvent e) {

    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void BreakEvent(BlockBreakEvent e) {

    }

    @EventHandler
    public void worldLoad(WorldLoadEvent e){
        String world = e.getWorld().getName();
        if(!getConfig().contains("break." + world )){
            getConfig().set("break." + world,true);
            getConfig().set("place." + world,true);
            breaking.add(world);
            placing.add(world);
            getLogger().info("The new world " + world + " has successfully been imported by Antibreaking");
            saveConfig();

        }
    }
}
