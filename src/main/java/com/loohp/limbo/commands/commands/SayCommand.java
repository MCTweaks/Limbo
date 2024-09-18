package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Console;
import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;

public class SayCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("limboserver.say")) {
            if (sender instanceof Console) {
                if (args.length > 1) {
                    String message = "[Server] " + String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    Limbo.getInstance().getConsole().sendMessage(message);
                    for (Player each : Limbo.getInstance().getPlayers()) {
                        each.sendMessage(message);
                    }
                }
            } else {
                if (args.length > 1) {
                    String message = "[" + sender.getName() + "] " + String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    Limbo.getInstance().getConsole().sendMessage(message);
                    for (Player each : Limbo.getInstance().getPlayers()) {
                        each.sendMessage(message);
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
        }
        return;

    }
}