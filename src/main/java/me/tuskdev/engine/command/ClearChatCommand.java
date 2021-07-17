package me.tuskdev.engine.command;

import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.tuskdev.engine.util.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

public class ClearChatCommand {

    @Command(
            name = "clearchat",
            aliases = "cc",
            permission = "engine.command.clearchat"
    )
    public void handleCommand(BukkitContext bukkitContext) {
        String message = StringUtils.repeat(" §c \n §c ", 100);
        Bukkit.broadcastMessage(message);
        bukkitContext.sendMessage(MessageUtil.getMessage("clearchat-command-message"));
    }

}
