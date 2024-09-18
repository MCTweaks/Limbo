package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import net.md_5.bungee.api.ChatColor;

public class SpawnCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("limboserver.spawn")) {
            if (args.length == 1 && sender instanceof Player) {
                Player player = (Player) sender;
                player.teleport(Limbo.getInstance().getServerProperties().getWorldSpawn());
                player.sendMessage(ChatColor.GOLD + "Teleporting you to spawn!");
            } else if (args.length == 2) {
                Player player = Limbo.getInstance().getPlayer(args[1]);
                if (player != null) {
                    player.teleport(Limbo.getInstance().getServerProperties().getWorldSpawn());
                    sender.sendMessage(ChatColor.GOLD + "Teleporting " + player.getName() + " to spawn!");
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid command usage!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
        }
        return;
    }
}