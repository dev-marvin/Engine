package me.tuskdev.engine.command;

import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import me.tuskdev.engine.cache.TpaSystemCache;
import me.tuskdev.engine.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpaCommand {

    private final TpaSystemCache tpaSystemCache = new TpaSystemCache();

    @Command(
            name = "tpa",
            permission = "command.tpa",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(BukkitContext bukkitContext, String[] args) {
        Player player = (Player) bukkitContext.getSender();

        if (tpaSystemCache.select(player.getUniqueId()) != null) {
            player.sendMessage(MessageUtil.getMessage("tpa-command-contains"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(MessageUtil.getMessage("invalid-player").replace("{player}", args[0]));
            return;
        }

        if (player.getName().equals(target.getName())) {
            player.sendMessage(MessageUtil.getMessage("tpa-command-error"));
            return;
        }

        tpaSystemCache.insert(player.getUniqueId(), target.getUniqueId());
        player.sendMessage(MessageUtil.getMessage("tpa-command-message").replace("{player}", target.getName()));
        target.sendMessage(MessageUtil.getMessage("tpa-command-player").replace("{player}", player.getName()));
    }

    @Command(
            name = "tpaaccept",
            permission = "command.tpaaccept",
            target = CommandTarget.PLAYER
    )
    public void handleCommandTpaAccept(BukkitContext bukkitContext, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            bukkitContext.sendMessage(MessageUtil.getMessage("invalid-player").replace("{player}", args[0]));
            return;
        }

        Player player = (Player) bukkitContext.getSender();
        UUID targetInvite = tpaSystemCache.select(target.getUniqueId());
        if (targetInvite == null || !(targetInvite.equals(player.getUniqueId()))) {
            player.sendMessage(MessageUtil.getMessage("tpaaccept-command-error"));
            return;
        }

        player.sendMessage(MessageUtil.getMessage("tpaaccept-command-message").replace("{player}", target.getName()));
        target.sendMessage(MessageUtil.getMessage("tpaaccept-command-player").replace("{player}", player.getName()));

        target.teleport(player);
        tpaSystemCache.delete(target.getUniqueId());
    }

    @Command(
            name = "tpacancel",
            permission = "command.tpacancel",
            target = CommandTarget.PLAYER
    )
    public void handleCommandTpaCancel(BukkitContext bukkitContext, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            bukkitContext.sendMessage(MessageUtil.getMessage("invalid-player").replace("{player}", args[0]));
            return;
        }

        Player player = (Player) bukkitContext.getSender();
        UUID playerInvite = tpaSystemCache.select(player.getUniqueId());
        if (playerInvite == null || !(playerInvite.equals(target.getUniqueId()))) {
            player.sendMessage(MessageUtil.getMessage("tpacancel-command-error"));
            return;
        }

        player.sendMessage(MessageUtil.getMessage("tpacancel-command-message").replace("{player}", target.getName()));
        target.sendMessage(MessageUtil.getMessage("tpacancel-command-player").replace("{player}", player.getName()));

        tpaSystemCache.delete(player.getUniqueId());
    }

    @Command(
            name = "tpadeny",
            permission = "command.tpadeny",
            target = CommandTarget.PLAYER
    )
    public void handleCommandTpaDeny(BukkitContext bukkitContext, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            bukkitContext.sendMessage(MessageUtil.getMessage("invalid-player").replace("{player}", args[0]));
            return;
        }

        Player player = (Player) bukkitContext.getSender();
        UUID targetInvite = tpaSystemCache.select(target.getUniqueId());
        if (targetInvite == null || !(targetInvite.equals(player.getUniqueId()))) {
            player.sendMessage(MessageUtil.getMessage("tpadeny-command-error"));
            return;
        }

        player.sendMessage(MessageUtil.getMessage("tpadeny-command-message").replace("{player}", target.getName()));
        target.sendMessage(MessageUtil.getMessage("tpadeny-command-player").replace("{player}", player.getName()));

        tpaSystemCache.delete(target.getUniqueId());
    }

}
