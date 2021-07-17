package me.tuskdev.engine.command;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import me.tuskdev.engine.cache.BackSystemCache;
import me.tuskdev.engine.util.Coordinates;
import me.tuskdev.engine.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BackCommand {

    private final BackSystemCache backSystemCache;

    public BackCommand(BackSystemCache backSystemCache) {
        this.backSystemCache = backSystemCache;
    }

    @Command(
            name = "back",
            aliases = { "return", "voltar", "retornar" },
            permission = "engine.command.back",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<Player> context) {
        Player player = context.getSender();
        UUID uuid = player.getUniqueId();

        Coordinates coordinates = backSystemCache.select(uuid);
        if (coordinates == null) {
            player.sendMessage(MessageUtil.getMessage("back-command-error"));
            return;
        }

        backSystemCache.delete(uuid);

        player.teleport(coordinates.build());
        player.sendMessage(MessageUtil.getMessage("back-command-message"));
    }

}
