package me.tuskdev.engine.command;

import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.tuskdev.engine.util.MessageUtil;
import org.bukkit.Bukkit;

public class AlertCommand {

    @Command(
            name = "alert",
            aliases = { "alerta", "say", "broadcast", "me" },
            permission = "engine.command.alert"
    )
    public void handleCommand(BukkitContext bukkitContext) {
        if (bukkitContext.argsCount() <= 0) {
            bukkitContext.sendMessage(MessageUtil.getMessage("use-alert-command"));
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String argument : bukkitContext.getArgs()) stringBuilder.append(argument).append(" ");

        Bukkit.broadcastMessage(MessageUtil.getMessage("alert-command-message").replace("{message}", stringBuilder.toString()));
    }

}
