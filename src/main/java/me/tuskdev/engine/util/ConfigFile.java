package me.tuskdev.engine.util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Taken from:
 * https://github.com/M0rais/senior-crates/blob/main/src/main/java/pt/m0rais/crates/model/util/ConfigModel.java
 * @author M0rais
 */
public class ConfigFile {

    private final File file;
    private FileConfiguration fileConfiguration;

    public ConfigFile(Plugin plugin, String name) {
        file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(name, false);
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void reload() {
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}
