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

import ninja.leaping.configurate.commented.CommentedConfigurationNode;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtils {

    public static void setInventoryToConfig(Player player, CommentedConfigurationNode invtrack) {
        CommentedConfigurationNode invNode = invtrack.getNode(player.getUniqueId().toString()).getNode(INV_NODESTR.get());

        PlayerInventory playerInventory = player.getInventory();

        // This loop should save the inventory (hotbar + inner inventory, excluding armor slots).
        for (int invSlot = 0; invSlot < playerInventory.getSize(); invSlot++) {
            ItemStack item = playerInventory.getItem(invSlot);

            CommentedConfigurationNode invSlotNode = invNode.getNode(String.valueOf(invSlot));
            invSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).setValue(item.getType().getId());
            invSlotNode.getNode(INV_SLOT_AMOUNT_NODESTR.get()).setValue(item.getAmount());
            invSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).setValue(item.getDurability());
        }

        // This should save the armor slots
        for (int armorSlot = 0; armorSlot < 4; armorSlot++) {
            ItemStack armorPiece;
            CommentedConfigurationNode armorSlotNode;

            switch (armorSlot) {
                case 0: { // boots
                    armorPiece = playerInventory.getBoots();
                    armorSlotNode = invNode.getNode(INV_BOOTS_NODESTR.get());
                    armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).setValue(armorPiece.getType().getId());
                    armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).setValue(armorPiece.getDurability());
                    break;
                }
                case 1: { // leggings
                    armorPiece = playerInventory.getLeggings();
                    armorSlotNode = invNode.getNode(INV_LEGGINGS_NODESTR.get());
                    armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).setValue(armorPiece.getType().getId());
                    armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).setValue(armorPiece.getDurability());
                    break;
                }
                case 2: { // chestplate
                    armorPiece = playerInventory.getChestplate();
                    armorSlotNode = invNode.getNode(INV_CHESTPLATE_NODESTR.get());
                    armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).setValue(armorPiece.getType().getId());
                    armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).setValue(armorPiece.getDurability());
                    break;
                }
                case 3: { // helmet
                    armorPiece = playerInventory.getHelmet();
                    armorSlotNode = invNode.getNode(INV_HELMET_NODESTR.get());
                    armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).setValue(armorPiece.getType().getId());
                    armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).setValue(armorPiece.getDurability());
                    break;
                }
            }
        }
    }

    public static void unsetInventoryFromConfig(Player player, CommentedConfigurationNode invtrack) {
        invtrack.getNode(player.getUniqueId().toString()).getNode(INV_NODESTR.get()).setValue(null);
    }

    public static void giveInventoryBack(Player player, CommentedConfigurationNode invtrack) {
        PlayerInventory playerInventory = player.getInventory();
        CommentedConfigurationNode invNode = invtrack.getNode(player.getUniqueId().toString()).getNode(INV_NODESTR.get());

        // Give back the inventory and hotbar
        for (int invSlot = 0; invSlot < playerInventory.getSize(); invSlot++) {
            CommentedConfigurationNode invSlotNode = invNode.getNode(String.valueOf(invSlot));
            Material itemMaterial = Material.getMaterial(invSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).getInt());

            if (itemMaterial != Material.AIR) { // We should only do something if there was actually an item saved at that slot (air has id "0", and can't be held by players).
                int itemAmount = invSlotNode.getNode(INV_SLOT_AMOUNT_NODESTR.get()).getInt();

                short itemDurability = ((short) invSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).getInt()); // It is safe to cast this int to short, it's just that Configurate does not have a getShort() method, sadly. Durability should never be more than what a short can hold (unless the user manually edits the file somehow, which is NOT RECOMMENDED anyway).

                playerInventory.addItem(new ItemStack(itemMaterial, itemAmount, itemDurability));
            }
        }

        // Now, the armor slots
        for (int armorSlot = 0; armorSlot < 4; armorSlot++) {
            CommentedConfigurationNode armorSlotNode;
            int pieceId;
            int pieceAmount;
            short pieceDurability;

            switch (armorSlot) {
                case 0: { // boots
                    armorSlotNode = invNode.getNode(INV_BOOTS_NODESTR.get());
                    pieceId = armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).getInt();
                    if (pieceId == 0) { // server throws NullPointerException if we try to set nothing as the armor piece for some reason???
                        break;
                    }
                    pieceAmount = armorSlotNode.getNode(INV_SLOT_AMOUNT_NODESTR.get()).getInt();
                    pieceDurability = ((short)armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).getInt());

                    playerInventory.setBoots(new ItemStack(pieceId, pieceAmount, pieceDurability));
                    break;
                }
                case 1: { // leggings
                    armorSlotNode = invNode.getNode(INV_LEGGINGS_NODESTR.get());
                    pieceId = armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).getInt();
                    if (pieceId == 0) {
                        break;
                    }
                    pieceAmount = armorSlotNode.getNode(INV_SLOT_AMOUNT_NODESTR.get()).getInt();
                    pieceDurability = ((short)armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).getInt());

                    playerInventory.setLeggings(new ItemStack(pieceId, pieceAmount, pieceDurability));
                    break;
                }
                case 2: { // chestplate
                    armorSlotNode = invNode.getNode(INV_CHESTPLATE_NODESTR.get());
                    pieceId = armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).getInt();
                    if (pieceId == 0) {
                        break;
                    }
                    pieceAmount = armorSlotNode.getNode(INV_SLOT_AMOUNT_NODESTR.get()).getInt();
                    pieceDurability = ((short)armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).getInt());

                    playerInventory.setChestplate(new ItemStack(pieceId, pieceAmount, pieceDurability));
                    break;
                }
                case 3: { // helmet
                    armorSlotNode = invNode.getNode(INV_HELMET_NODESTR.get());
                    pieceId = armorSlotNode.getNode(INV_SLOT_ITEMID_NODESTR.get()).getInt();
                    if (pieceId == 0) {
                        break;
                    }
                    pieceAmount = armorSlotNode.getNode(INV_SLOT_AMOUNT_NODESTR.get()).getInt();
                    pieceDurability = ((short)armorSlotNode.getNode(INV_SLOT_DURABILITY_NODESTR.get()).getInt());

                    playerInventory.setHelmet(new ItemStack(pieceId, pieceAmount, pieceDurability));
                    break;
                }
            }
        }
    }
}
