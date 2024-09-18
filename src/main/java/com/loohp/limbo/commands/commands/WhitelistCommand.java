package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import net.md_5.bungee.api.ChatColor;

public class WhitelistCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("limboserver.whitelist")) {
            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "Invalid usage!");
            } else if (!args[1].equalsIgnoreCase("reload")) {
                sender.sendMessage(ChatColor.RED + "Invalid usage!");
            } else {
                Limbo.getInstance().getServerProperties().reloadWhitelist();
                sender.sendMessage("Whitelist has been reloaded");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
        }
        return;
    }
}