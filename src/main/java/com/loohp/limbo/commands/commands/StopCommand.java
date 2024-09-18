package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import net.md_5.bungee.api.ChatColor;

public class StopCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("limboserver.stop")) {
            Limbo.getInstance().stopServer();
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
        }
        return;

    }
}