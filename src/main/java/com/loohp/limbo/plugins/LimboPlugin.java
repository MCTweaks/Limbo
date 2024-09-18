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

package com.loohp.limbo.plugins;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.file.FileConfiguration;

import java.io.File;

public class LimboPlugin {

	private String name;
	private File dataFolder;
	private File pluginJar;
	
	protected final void setInfo(FileConfiguration file, File pluginJar) {
		this.dataFolder = new File(Limbo.getInstance().getPluginFolder(), name);
		this.pluginJar = pluginJar;
	}
	
	protected final File getPluginJar() {
		return pluginJar;
	}

	public void onLoad() {

	}

	public void onEnable() {

	}

	public void onDisable() {

	}

	public final String getName() {
		return name;
	}

	public final File getDataFolder() {
		return new File(dataFolder.getAbsolutePath());
	}
	
	public final Limbo getServer() {
		return Limbo.getInstance();
	}

}
