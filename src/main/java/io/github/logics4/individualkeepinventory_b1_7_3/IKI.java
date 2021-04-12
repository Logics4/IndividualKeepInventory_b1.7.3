/*
This file is part of the "IndividualKeepInventory_b1.7.3" project.
You can find it here: https://github.com/Logics4/IndividualKeepInventory_b1.7.3

Copyright (C) 2020  Logics4

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package io.github.logics4.individualkeepinventory_b1_7_3;

import io.github.logics4.commonclasses.config.CommentedConfigFile;
import io.github.logics4.commonclasses.config.ConfigFileFormat;

import java.nio.file.Path;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class IKI extends JavaPlugin {
    private CommentedConfigFile invtrack;
    private Path dataFolderPath;

    CommentedConfigFile invtrackFile() {
        return invtrack;
    }

    Path dataFolderPath() {
        return dataFolderPath;
    }

    @Override
    public void onEnable() {
        dataFolderPath = getDataFolder().toPath();

        invtrack = new CommentedConfigFile(ConfigFileFormat.HOCON, dataFolderPath(), Constants.INVTRACK_FILENAME);
        invtrack.extractFromJar(Constants.INJAR_ASSETS_FOLDER, false);
        invtrack.loadConfig();

        PlayerDeathChecker onEntityDeath = new PlayerDeathChecker(this);
        PlayerRespawnChecker onPlayerRespawn = new PlayerRespawnChecker(this);
        getServer().getPluginManager().registerEvent(Type.ENTITY_DEATH, onEntityDeath, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, onPlayerRespawn, Priority.Normal, this);
    }

    @Override
    public void onDisable() {

    }
}
