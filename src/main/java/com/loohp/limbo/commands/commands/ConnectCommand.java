package com.loohp.limbo.commands.commands;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;
import net.md_5.bungee.api.ChatColor;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConnectCommand implements CommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);

                out.writeUTF("Connect");
                out.writeUTF(args[1]);

                player.sendPluginMessage("BungeeCord", b.toByteArray());
                b.close();
                out.close();
            } catch (IOException e) {
                player.sendMessage("IOException");
            }
        }
        sender.sendMessage(ChatColor.GRAY + "This server is running Limbo version " + Limbo.getInstance().LIMBO_IMPLEMENTATION_VERSION + " (MC: " + Limbo.getInstance().SERVER_IMPLEMENTATION_VERSION + ")");
    }


}
