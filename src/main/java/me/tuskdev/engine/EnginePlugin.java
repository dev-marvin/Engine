package me.tuskdev.engine;

import me.tuskdev.engine.util.ConfigFile;
import me.tuskdev.engine.util.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class EnginePlugin extends JavaPlugin {

    private PooledConnection pooledConnection;

    @Override
    public void onLoad() {
        saveDefaultConfig();

        pooledConnection = new PooledConnection(getConfig().getConfigurationSection("database"));
    }

    @Override
    public void onEnable() {
        MessageUtil.load(new ConfigFile(this, "messages.yml").getFileConfiguration());
    }

    @Override
    public void onDisable() {
        pooledConnection.close();
    }
}
