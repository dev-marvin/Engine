package me.tuskdev.engine.command;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import me.tuskdev.engine.util.MessageUtil;
import me.tuskdev.engine.util.NumberUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantCommand {

    @Command(
            name = "enchant",
            aliases = { "encantar" },
            permission = "engine.command.echant",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<Player> context) {
        if (context.argsCount() < 2) {
            context.sendMessage(MessageUtil.getMessage("enchant-command-usage"));
            return;
        }

        Enchantment enchantment = Enchantment.getByName(context.getArg(0));
        if (enchantment == null) {
            context.sendMessage(MessageUtil.getMessage("enchant-command-invalid").replace("{enchant}", context.getArg(0)));
            return;
        }

        Integer level = NumberUtil.tryParseInt(context.getArg(1));
        if (level == null) {
            context.sendMessage(MessageUtil.getMessage("invalid-number").replace("{number}", context.getArg(1)));
            return;
        }

        ItemStack itemStack = context.getSender().getItemInHand();
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            context.sendMessage(MessageUtil.getMessage("enchant-command-error"));
            return;
        }

        itemStack.addUnsafeEnchantment(enchantment, level);
        context.sendMessage(MessageUtil.getMessage("enchant-command-message").replace("{enchant}", enchantment.getName()).replace("{level}", level.toString()));
    }

}
