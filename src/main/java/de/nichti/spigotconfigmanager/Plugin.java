package de.nichti.spigotconfigmanager;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin {

    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(final JavaPlugin plugin) {
        Plugin.plugin = plugin;
    }

}