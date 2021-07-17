package me.tuskdev.engine.util;

import com.google.common.collect.ImmutableMap;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;

public class MessageUtil {

    private static Map<String, String> messages;

    public static void load(FileConfiguration fileConfiguration) {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();

        fileConfiguration.getValues(true).forEach((key, value) -> builder.put(key, ChatColor.translateAlternateColorCodes('&', value.toString())));

        messages = builder.build();
    }

    public static String getMessage(String key) {
        return messages.getOrDefault(key, "Fail on get message '" + key + "'.");
    }

}
