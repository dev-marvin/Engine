package me.tuskdev.engine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TpaSystemCache {

    // NOTE: The key is the UUID of the player you invited.
    private final Cache<UUID, UUID> CACHE = Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build();

    public void insert(UUID sender, UUID invited) {
        CACHE.put(sender, invited);
    }

    public UUID select(UUID uuid) {
        return CACHE.getIfPresent(uuid);
    }

    public void delete(UUID uuid) {
        CACHE.invalidate(uuid);
    }

}
