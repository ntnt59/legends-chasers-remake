package net.tototuto.legendchasersremake.event;

import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = LegendChasersRemakeMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Définis ici le rendu Cutout pour tes feuilles
            ItemBlockRenderTypes.setRenderLayer(LegendChasersRemakeModBlocks.PROTIUM_LEAVES.get(), RenderType.cutoutMipped());
        });
    }
}