package me.tuskdev.engine.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PluginUtil {

    public static void registerListener(Plugin plugin, Listener... listeners) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        for (Listener listener : listeners) pluginManager.registerEvents(listener, plugin);
    }

}
