package net.massacring.infosnooper.events;

import net.massacring.infosnooper.InfoSnooper;
import net.massacring.infosnooper.commands.SnoopHeldItem;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = InfoSnooper.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new SnoopHeldItem(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
