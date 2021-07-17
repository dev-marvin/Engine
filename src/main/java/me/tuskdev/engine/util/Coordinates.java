package me.tuskdev.engine.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class Coordinates {

    private final String worldName;
    private final int blockX, blockY, blockZ;

    Coordinates(String worldName, int blockX, int blockY, int blockZ) {
        this.worldName = worldName;
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockZ = blockZ;
    }

    public String getWorldName() {
        return worldName;
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockZ() {
        return blockZ;
    }

    public Location build() {
        return new Location(Bukkit.getWorld(worldName), blockX, blockY, blockZ);
    }

    public static Coordinates of(Location location) {
        return new Coordinates(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static Coordinates of(Block block) {
        return of(block.getLocation());
    }

    public static Coordinates of(Entity entity) {
        return of(entity.getLocation());
    }
}
