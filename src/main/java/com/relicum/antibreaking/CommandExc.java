package com.relicum.antibreaking;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Antibreaking
 *
 * @author Relicum
 * @version 0.1
 */
public class CommandExc implements CommandExecutor {

    public Main plugin;

    public CommandExc(Main pl) {

        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("antireload")) {
            plugin.reloadConfig();
            plugin.loadPerms();

            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.GREEN + "The configs have been reloaded");
            } else {
                plugin.getLogger().info("The configs have been reloaded");
            }


        } else if (label.equalsIgnoreCase("setplace")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String wo;
                String setTo;
                System.out.println("Get Here total args is " + args.length + Arrays.toString(args));
                if (args.length == 0) {
                    player.sendMessage(ChatColor.DARK_RED + "You arguments length can not be 0. Must pass on or off, world is optional");
                    return true;
                }

                setTo = args[0];
                System.out.println(setTo);

                if (!((!setTo.equals("On")) && (!setTo.equals("Off")))) {
                    player.sendMessage(ChatColor.DARK_RED + "The first argument must be On or Off currently is " + args[0]);
                    return true;
                }
                System.out.println("make it here");
                if (args.length > 1) {
                    String world = args[1];
                    if (!isValidWorld(world)) {
                        player.sendMessage(ChatColor.DARK_RED + "You have not passed a valid world, worlds are case sensitive " + world);
                        return true;
                    }
                    wo = world.toLowerCase();
                } else {
                    wo = player.getWorld().getName().toLowerCase();
                    setTo = args[0].toLowerCase();
                    System.out.println("World not present so using players current");
                }

                if (setTo.equals("off")) {
                    if (removeSetting(wo, "place")) {
                        player.sendMessage(ChatColor.GREEN + "You can now place blocks in " + wo);
                    } else {
                        player.sendMessage(ChatColor.GREEN + "Nothing to set you could already place blocks in " + wo);
                    }
                    return true;
                } else {

                    if (addSetting(wo, "place")) {
                        player.sendMessage(ChatColor.GREEN + "You can no longer place blocks in " + wo);
                    } else {
                        player.sendMessage(ChatColor.GREEN + "Nothing to set you could not place blocks in " + wo + " already");
                    }
                    return true;
                }


            } else {
                plugin.getLogger().info("Setplace command can only be run in game");
                return true;
            }

        }
        System.out.println("Get to the bottom");
        return false;
    }

    /**
     * Checks if a given string is a valid world
     *
     * @param wo String
     * @return boolean
     */
    private boolean isValidWorld(String wo) {

        if (plugin.getServer().getWorld(wo) == null) {
            return false;
        }
        return true;
    }

    private boolean removeSetting(String world, String type) {

        if (plugin.getInstance().getworldP().contains(world + type)) {
            List<String> li = plugin.getConfig().getStringList("Worlds." + world);
            li.remove(type);
            plugin.getConfig().set("Worlds." + world, li);
            plugin.saveConfig();
            plugin.reloadConfig();
            plugin.loadPerms();
            return true;
        }

        return false;

    }

    private boolean addSetting(String world, String type) {

        if (!plugin.getInstance().getworldP().contains(world + type)) {
            List<String> li = plugin.getConfig().getStringList("Worlds." + world);
            li.add(type);
            plugin.getConfig().set("Worlds." + world, li);
            plugin.saveConfig();
            plugin.reloadConfig();
            plugin.loadPerms();
            return true;
        }

        return false;
    }
}
