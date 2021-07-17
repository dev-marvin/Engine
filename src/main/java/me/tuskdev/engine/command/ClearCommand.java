package me.tuskdev.engine.command;

import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.tuskdev.engine.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand {

    @Command(
            name = "clear",
            permission = "engine.command.clear"
    )
    public void handleCommand(BukkitContext bukkitContext) {
        CommandSender sender = bukkitContext.getSender();
        String[] args = bukkitContext.getArgs();

        if (args.length > 0 && sender.hasPermission("engine.admin") || !(sender instanceof Player)) {
            // NOTE: Sender is console, execute this verification.
            if (args.length <= 0) {
                sender.sendMessage(MessageUtil.getMessage("clear-command-usage"));
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(MessageUtil.getMessage("invalid-player").replace("{player}", args[0]));
                return;
            }

            target.getInventory().clear();
            sender.sendMessage(MessageUtil.getMessage("clear-command-player-message").replace("{player}", target.getName()));
            return;
        }

        Player player = (Player) sender;
        player.getInventory().clear();
        player.sendMessage(MessageUtil.getMessage("clear-command-message"));
    }

}
