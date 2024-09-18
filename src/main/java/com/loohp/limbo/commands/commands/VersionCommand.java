package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import net.md_5.bungee.api.ChatColor;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class VersionCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("limboserver.version")) {
                sender.sendMessage(ChatColor.GRAY + "This server is running Limbo version " + Limbo.getInstance().LIMBO_IMPLEMENTATION_VERSION + " (MC: " + Limbo.getInstance().SERVER_IMPLEMENTATION_VERSION + ")");
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
            }
            return;
        }

    }
}