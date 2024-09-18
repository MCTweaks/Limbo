/*
 * This file is part of Limbo.
 *
 * Copyright (C) 2022. LoohpJames <jamesloohp@gmail.com>
 * Copyright (C) 2022. Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loohp.limbo.commands;

import com.loohp.limbo.Console;
import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.commands.*;
import com.loohp.limbo.player.Player;
import com.loohp.limbo.utils.GameMode;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public class DefaultCommands implements CommandExecutor, TabCompletor {
	private final Map<String, CommandExecutor> commandMap = new HashMap<>();

	public DefaultCommands() {
		commandMap.put("connect", new ConnectCommand());
		commandMap.put("version", new VersionCommand());
		commandMap.put("spawn", new SpawnCommand());
		commandMap.put("stop", new StopCommand());
		commandMap.put("kick", new KickCommand());
		commandMap.put("gamemode", new GamemodeCommand());
		commandMap.put("say", new SayCommand());
		commandMap.put("whitelist", new WhitelistCommand());

	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("No command provided!");
			return;
		}

		// Find and execute the command if it exists in the map
		String command = args[0].toLowerCase();
		if (commandMap.containsKey(command)) {
			CommandExecutor executor = commandMap.get(command);
			executor.execute(sender, args); // Delegate the execution to the specific command class
		} else {
			sender.sendMessage("Unknown command: " + command);
		}
	}
	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		List<String> tab = new ArrayList<>();
		switch (args.length) {
			case 0:
				if (sender.hasPermission("limboserver.spawn")) {
					tab.add("spawn");
				}
				if (sender.hasPermission("limboserver.connect")) {
					tab.add("connect");
				}
				if (sender.hasPermission("limboserver.kick")) {
					tab.add("kick");
				}
				if (sender.hasPermission("limboserver.stop")) {
					tab.add("stop");
				}
				if (sender.hasPermission("limboserver.say")) {
					tab.add("say");
				}
				if (sender.hasPermission("limboserver.gamemode")) {
					tab.add("gamemode");
				}
				break;
			case 1:
				if (sender.hasPermission("limboserver.spawn")) {
					if ("spawn".startsWith(args[0].toLowerCase())) {
						tab.add("spawn");
					}
				}
				if (sender.hasPermission("limboserver.kick")) {
					if ("kick".startsWith(args[0].toLowerCase())) {
						tab.add("kick");
					}
				}
				if (sender.hasPermission("limboserver.stop")) {
					if ("stop".startsWith(args[0].toLowerCase())) {
						tab.add("stop");
					}
				}
				if (sender.hasPermission("limboserver.say")) {
					if ("say".startsWith(args[0].toLowerCase())) {
						tab.add("say");
					}
				}
				if (sender.hasPermission("limboserver.gamemode")) {
					if ("gamemode".startsWith(args[0].toLowerCase())) {
						tab.add("gamemode");
					}
				}
				break;
			case 2:
				if (sender.hasPermission("limboserver.kick")) {
					if (args[0].equalsIgnoreCase("kick")) {
						for (Player player : Limbo.getInstance().getPlayers()) {
							if (player.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
								tab.add(player.getName());
							}
						}
					}
				}
				if (sender.hasPermission("limboserver.gamemode")) {
					if (args[0].equalsIgnoreCase("gamemode")) {
						for (GameMode mode : GameMode.values()) {
							if (mode.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
								tab.add(mode.getName());
							}
						}
					}
				}
				break;
			case 3:
				if (sender.hasPermission("limboserver.gamemode")) {
					if (args[0].equalsIgnoreCase("gamemode")) {
						for (Player player : Limbo.getInstance().getPlayers()) {
							if (player.getName().toLowerCase().startsWith(args[2].toLowerCase())) {
								tab.add(player.getName());
							}
						}
					}
				}
				break;
		}
		return tab;
	}

}
