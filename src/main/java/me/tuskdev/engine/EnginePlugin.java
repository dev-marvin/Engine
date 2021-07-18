package me.tuskdev.engine;

import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.tuskdev.engine.cache.BackSystemCache;
import me.tuskdev.engine.command.*;
import me.tuskdev.engine.listener.BackSystemListener;
import me.tuskdev.engine.util.ConfigFile;
import me.tuskdev.engine.util.MessageUtil;
import me.tuskdev.engine.util.PluginUtil;
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
        BackSystemCache backSystemCache = new BackSystemCache();

        BukkitFrame bukkitFrame = new BukkitFrame(this);
        bukkitFrame.registerCommands(
                new AlertCommand(),
                new BackCommand(backSystemCache),
                new ClearChatCommand(),
                new ClearCommand(),
                new CraftCommand(),
                new EnchantCommand(),
                new TpaCommand()
        );

        PluginUtil.registerListener(this,
                new BackSystemListener(backSystemCache)
        );

        MessageUtil.load(new ConfigFile(this, "messages.yml").getFileConfiguration());
    }

    @Override
    public void onDisable() {
        pooledConnection.close();
    }
}
