# Individual Keep-Inventory (Craftbukkit 1092 - Beta 1.7.3)
This is a simple plugin I have made, which allows you to set the `keepInventory` mechanic in Minecraft in a per-player way.
</br></br>

## Server Requirements:
- **Java 8 or higher**;
- **Craftbukkit build 1092 (for Minecraft Beta 1.7.3)**.
</br></br>

## How to use it?

1 - Install this plugin and a permission management plugin in your server. For Craftbukkit build 1092, [PermissionsEx 1.14](https://github.com/PEXPlugins/PermissionsEx/releases/tag/STABLE-1.14) is what I personally recommend. This link is for the source code - you'll have to build it yourself, unless you can find a binary somewhere else;</br></br>
2 - With the permission management plugin you have installed, give the permission `iki.events.playerdeath.keepinventory` to whoever you want.
</br></br>

## Examples
Assuming you are using PermissionsEx:</br>
1 - If you want to give `keepInventory` to a specific player or group:
```
/pex user <playername> add iki.events.playerdeath.keepinventory
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

## How to Compile
**Requirements:**
- **JDK 8**;
- **Maven 3.6.3 or higher**.</br>

**Instructions:**</br>
1 - With Git, `git clone` [the Bukkit repository](https://github.com/Bukkit/Bukkit), run `git checkout da29e0aa4dcb08c5c91157c0830851330af8b572` and run `mvn clean install` in its root directory, so that you install Bukkit for Beta 1.7.3 as a dependency in your local maven repository;</br>
2 - With Git, `git clone` this repository, and run `mvn clean package` in its root directory;</br>
The plugin should be in `<project's root>/jar/target/`, named `individualkeepinventory_b1.7.3-<versionTag>.jar`. Do not use the one with the `original-` prefix.
</br></br>

## License
This project's source code is licensed under the General Public License version 3 (GPLv3), except for the following file(s):
- HOCONConfig.java (part of the [CommonClasses-Java project, Config module](https://github.com/Logics4/CommonClasses-Java/tree/main/Config)), located in the `io/github/logics4/individualkeepinventory_b1_7_3` package, which is under the Apache License version 2.0.</br>

You can find a full copy of the GPLv3 in the [LICENSE.txt](https://github.com/Logics4/IndividualKeepInventory_b1.7.3/blob/main/LICENSE.txt) file, and a full copy of the Apache License version 2.0 in the [LICENSE.Apache_v2.0.txt](https://github.com/Logics4/IndividualKeepInventory_b1.7.3/blob/main/LICENSE.Apache_v2.0.txt) file, both at the root directory of this source code.
