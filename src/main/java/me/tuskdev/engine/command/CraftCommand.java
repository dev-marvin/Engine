package me.tuskdev.engine.command;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class CraftCommand {

    @Command(
            name = "craft",
            permission = "engine.command.craft",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<Player> context) {
        Player player = context.getSender();
        player.openWorkbench(player.getLocation(), true);
    }

}
