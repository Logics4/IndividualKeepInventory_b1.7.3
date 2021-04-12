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

public class Constants {

    public static final String INVTRACK_FILENAME = "inventory-tracking.conf";

    public static final String INJAR_ASSETS_FOLDER = "/assets/";

    public enum InvtrackNodeStrings {
        GAVE_ITEMS_BACK_NODESTR("gave-items-back"),
        INV_NODESTR("inventory"),
        INV_SLOT_ITEMID_NODESTR("id"),
        INV_SLOT_AMOUNT_NODESTR("amount"),
        INV_SLOT_DURABILITY_NODESTR("durability"),
        INV_HELMET_NODESTR("helmet"),
        INV_CHESTPLATE_NODESTR("chestplate"),
        INV_LEGGINGS_NODESTR("leggings"),
        INV_BOOTS_NODESTR("boots");


        private String nodeStr;

        public String get() {
            return nodeStr;
        }
        InvtrackNodeStrings(String nodeStr) {
            this.nodeStr = nodeStr;
        }
    }

    public static final String IKI_KEEPINVENTORY_PERMISSION = "iki.events.playerdeath.keepinventory";
}
