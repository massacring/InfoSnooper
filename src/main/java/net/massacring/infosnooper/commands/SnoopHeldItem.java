package net.massacring.infosnooper.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class SnoopHeldItem {
    public SnoopHeldItem(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("snoop").then(Commands.literal("heldItem").executes((command) -> {
            return heldItem(command.getSource());
        })));
    }

    private int heldItem(CommandSourceStack source) throws CommandSyntaxException {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            source.sendFailure(Component.literal("Somehow ran a client command on something that's not a player."));
            return 0;
        }

        ItemStack heldItem = player.getMainHandItem();

        source.sendSuccess(() -> {
            CompoundTag nbt = heldItem.getTag();

            return heldItem.getDisplayName().copy()
                    .append("\n")
                    .append(getDisplay(nbt));
        }, true);

        return 1;
    }

    private String getDisplay(CompoundTag nbt) {
        if (nbt == null) return "";

        StringBuilder display = new StringBuilder();
        for (String tag : nbt.getAllKeys()) {
            display.append(tag).append(": ").append(nbt.get(tag))
                    .append("\n");
        }

        return display.toString();
    }
}
