package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;

public class KickCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("limboserver.kick")) {
            Component reason = Component.translatable("multiplayer.disconnect.kicked");
            boolean customReason = false;
            if (args.length > 1) {
                Player player = Limbo.getInstance().getPlayer(args[1]);
                if (player != null) {
                    String reasonRaw = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                    if (reasonRaw.trim().length() > 0) {
                        reason = LegacyComponentSerializer.legacySection().deserialize(reasonRaw);
                        customReason = true;
                    }
                    player.disconnect(reason);
                    if (customReason) {
                        sender.sendMessage(ChatColor.RED + "Kicked the player " + player.getName() + " for the reason: " + LegacyComponentSerializer.legacySection().serialize(reason));
                    } else {
                        sender.sendMessage(ChatColor.RED + "Kicked the player " + player.getName());
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Player is not online!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You have to specifiy a player!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
        }
        return;

    }
}