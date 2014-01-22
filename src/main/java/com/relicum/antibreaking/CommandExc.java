package com.relicum.antibreaking;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;

/**
 * Antibreaking
 * 
 * @author Relicum
 * @version 0.1
 */
public class CommandExc implements TabExecutor {

    public Main plugin;
    public Map<String, Object> messages = new HashMap<>();
    private String noCmdPerm;
    private String noBreaking;
    private String noPlacing;
    private String noConsole;
    private String reload;
    private String invalidArgs;
    private String breakCmd;
    private String placeCmd;

    public CommandExc(Main pl) {

        plugin = pl;

        messages = plugin.getConfig().getConfigurationSection("messages").getValues(true);
        noCmdPerm = ChatColor.translateAlternateColorCodes('&', messages.get("noCommandPermission").toString());
        noBreaking = ChatColor.translateAlternateColorCodes('&', messages.get("noBreaking").toString());
        noPlacing = ChatColor.translateAlternateColorCodes('&', messages.get("noPlacing").toString());
        noConsole = ChatColor.translateAlternateColorCodes('&', messages.get("noConsole").toString());
        reload = ChatColor.translateAlternateColorCodes('&', messages.get("reload").toString());
        invalidArgs = ChatColor.translateAlternateColorCodes('&', messages.get("invalidArgs").toString());
        breakCmd = ChatColor.translateAlternateColorCodes('&', messages.get("breakCmd").toString());
        placeCmd = ChatColor.translateAlternateColorCodes('&', messages.get("placeCmd").toString());
        messages.clear();
        messages=null;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.noConsole);
            return false;
        }
        Player player = (Player) sender;
        String wo = player.getWorld().getName();
        String setTo;
        if (player.hasPermission("antibreak.reload") || player.isOp()) {

            plugin.reloadConfig();
            player.sendMessage(reload);
            plugin.getLogger().info(reload);
            return true;
        }

        if (label.equalsIgnoreCase("antiplace") || label.equalsIgnoreCase("antibreak")) {

            System.out.println("Get Here total args is " + args.length + Arrays.toString(args));
            if (args.length < 1 || args[1] == null) {

                player.sendMessage(invalidArgs);
                return false;
            }
            if ((label.equalsIgnoreCase("antiplace"))) {
                if (args.length > 1) {

                    plugin.getConfig().set("worlds." + args[2] + ".place", args[1]);
                    player.sendMessage(placeCmd.replace("%W%", args[2]).replace("%A%", args[1]));
                    return true;
                } else {

                    plugin.getConfig().set("worlds." + player.getWorld().getName() + ".place", args[1]);
                    player.sendMessage(placeCmd.replace("%W%", player.getWorld().getName()).replace("%A%", args[1]));
                    return true;
                }

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

            /*
             * if (setTo.equals("off")) { if (removeSetting(wo, "place")) {
             * player.sendMessage(ChatColor.GREEN +
             * "You can now place blocks in " + wo); } else {
             * player.sendMessage(ChatColor.GREEN +
             * "Nothing to set you could already place blocks in " + wo); }
             * return true; } else {
             * 
             * if (addSetting(wo, "place")) { player.sendMessage(ChatColor.GREEN
             * + "You can no longer place blocks in " + wo); } else {
             * player.sendMessage(ChatColor.GREEN +
             * "Nothing to set you could not place blocks in " + wo +
             * " already"); } return true; }
             */

        }
        System.out.println("Get to the bottom");
        return false;
    }

    private boolean isValidWorld(String wo) {

        return Bukkit.getWorld(wo) != null;
    }

    private void setBreak(String ty, String world) {

        if (plugin.getConfig().contains("worlds." + world)) {
            if (ty.equalsIgnoreCase("on")) {
                plugin.getConfig().set("world." + world, true);

            } else {

                plugin.getConfig().set("worlds." + world, false);
            }
        }
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println(alias + " length is " + args.length);
        List<String> ops = Arrays.asList("on", "off");
        if (sender instanceof Player) {
            if (args.length == 0) {
                System.out.println("trying to tab");
                return StringUtil.copyPartialMatches(args[1], ops, new ArrayList<String>(ops.size()));
            }
        }
        return Arrays.asList("help");
    }
}
