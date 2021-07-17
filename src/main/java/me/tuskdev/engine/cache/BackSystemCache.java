package me.tuskdev.engine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import me.tuskdev.engine.util.Coordinates;

import java.time.Duration;
import java.util.UUID;

public class BackSystemCache {

    private final Cache<UUID, Coordinates> CACHE = Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(5)).build();

    public void insert(UUID uuid, Coordinates coordinates) {
        CACHE.put(uuid, coordinates);
    }

    public Coordinates select(UUID uuid) {
        return CACHE.getIfPresent(uuid);
    }

    public void delete(UUID uuid) {
        CACHE.invalidate(uuid);
    }

}
