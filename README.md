# Individual Keep-Inventory (CraftBukkit 1092 - Beta 1.7.3)
The `keepInventory` mechanic did not exist back in Minecraft Beta 1.7.3, having only been added in Release 1.4.2 with the invention of gamerules.</br>
This does not prevent one from implementing the same behavior with Bukkit, though, and it is exactly what I've done.</br>
This plugin replicates the `keepInventory` behavior but in Beta 1.7.3, as faithfully as possible, and also makes it permission-based (meaning you can decide who gets to have this "privilege", so to speak - basically, [a port of this other plugin of mine](https://github.com/Logics4/IndividualKeepInventory)).
</br></br>

## Server Requirements:
- **Java 8 (or perhaps higher)**;
- **CraftBukkit build 1092 (for Minecraft Beta 1.7.3)**.
</br></br>

## How to use it?

1 - Install this plugin and a permission management plugin in your server. For CraftBukkit build 1092, [PermissionsEx 1.14](https://github.com/PEXPlugins/PermissionsEx/releases/tag/STABLE-1.14) is what I personally recommend. This link is for the source code - you'll have to build it yourself, unless you can find a binary somewhere else;</br></br>
2 - With the permission management plugin you have installed, give the permission `iki.events.playerdeath.keepinventory` to whoever you want.
</br></br>

## Examples
Assuming you are using PermissionsEx:</br>
1 - If you want to give `keepInventory` to a specific player or group:
```
/pex user <playername> add iki.events.playerdeath.keepinventory
(or)
/pex group <groupname> add iki.events.playerdeath.keepinventory
```
</br>

2 - If you want to give `keepInventory` to everyone except a specific player:
```
/pex group default add iki.events.playerdeath.keepinventory

/pex user <playername> add -iki.events.playerdeath.keepinventory
```
</br>
And there might be other use-cases I could spend 40 more hours covering here.
</br></br>

## Okay, and how does the plugin even save the inventories?
1 - On player death, we loop their inventory and save it to a file named `inventory-tracking.conf` (written in HOCON), while simultaneously clearing their drops (mimicking keepInventory behavior - you should not drop any items if you have it enabled, right?);</br>

2 - If they respawn, we loop their pre-death inventory information, which is saved in the file mentioned above, to restore their inventory. Afterwards, we delete the information from the file (to keep it cleaner);</br>

3 - If they decide to go to the title screen instead, or press the X button of their window, no worries; the information is still saved in the file and will be restored once they join again!
</br></br>

## How to Compile
**Requirements:**
- **JDK 8 or higher**;
- **Maven 3.6.3 or higher**.</br>

**Instructions:**</br>
With Git, `git clone` this repository, and run `mvn clean package` in its root directory;</br>
The plugin should be in `<project's root>/jar/target/`, named `individualkeepinventory_b1.7.3-<versionTag>.jar`. Do not use the one with the `original-` prefix.</br></br>
This project uses Bukkit API from [commit da29e0aa4dcb08c5c91157c0830851330af8b572](https://github.com/Bukkit/Bukkit/commit/da29e0aa4dcb08c5c91157c0830851330af8b572) (which targets Minecraft Beta 1.7.3). The API's jar is already in `libs/` so you don't need to compile it by yourself.
</br></br>

## License
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.</br>

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.</br>

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
