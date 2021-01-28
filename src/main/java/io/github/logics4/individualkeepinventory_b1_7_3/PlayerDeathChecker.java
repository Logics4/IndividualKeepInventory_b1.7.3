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

import static io.github.logics4.individualkeepinventory_b1_7_3.Constants.InvtrackNodeStrings.*;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class PlayerDeathChecker extends EntityListener {

    private IKI plugin;
    public PlayerDeathChecker(IKI plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = ((Player) entity);

            if (player.hasPermission(Constants.IKI_KEEPINVENTORY_PERMISSION)) {
                InventoryUtils.setInventoryToConfig(player, plugin.invtrack);

                plugin.invtrack.getNode(player.getUniqueId().toString()).getNode(GAVE_ITEMS_BACK_NODESTR.get()).setValue(false);

                Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            plugin.invtrackFile.save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 0L);

                event.getDrops().clear();
            }
        }
    }
}
