package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import com.loohp.limbo.utils.GameMode;
import net.md_5.bungee.api.ChatColor;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("limboserver.gamemode")) {
            if (args.length > 1) {
                Player player = args.length > 2 ? Limbo.getInstance().getPlayer(args[2]) : (sender instanceof Player ? (Player) sender : null);
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "You have to specifiy a player!");
                } else if (player != null) {
                    try {
                        player.setGamemode(GameMode.fromId(Integer.parseInt(args[1])));
                    } catch (Exception e) {
                        try {
                            player.setGamemode(GameMode.fromName(args[1]));
                        } catch (Exception e1) {
                            sender.sendMessage(ChatColor.RED + "Invalid usage!");
                            return;
                        }
                    }
                    sender.sendMessage(ChatColor.GOLD + "Updated gamemode to " + player.getGamemode().getName());
                } else {
                    sender.sendMessage(ChatColor.RED + "Player is not online!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid usage!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
        }
        return;

    }
}