package me.tuskdev.engine.listener;

import me.tuskdev.engine.cache.BackSystemCache;
import me.tuskdev.engine.util.Coordinates;
import me.tuskdev.engine.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BackSystemListener implements Listener {

    private final BackSystemCache backSystemCache;

    public BackSystemListener(BackSystemCache backSystemCache) {
        this.backSystemCache = backSystemCache;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!player.hasPermission("system.back")) return;

        backSystemCache.insert(player.getUniqueId(), Coordinates.of(player.getLocation()));

        player.sendMessage(MessageUtil.getMessage("back-listener-message"));
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("system.back")) return;

        backSystemCache.insert(player.getUniqueId(), Coordinates.of(player.getLocation()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("system.back")) return;

        backSystemCache.delete(player.getUniqueId());
    }

}
