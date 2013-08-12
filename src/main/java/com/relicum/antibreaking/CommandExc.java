package com.relicum.antibreaking;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            } else {
                plugin.getLogger().info("Setplace command can only be run in game");
                return false;
            }

        }
        return true;
    }
}
