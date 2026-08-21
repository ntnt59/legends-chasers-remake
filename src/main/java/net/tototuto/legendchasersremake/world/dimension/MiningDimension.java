package net.tototuto.legendchasersremake.world.dimension;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.DimensionSpecialEffects;

@Mod.EventBusSubscriber
public class MiningDimension {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class DesertSpecialEffectsHandler {
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
            DimensionSpecialEffects customEffect = new DimensionSpecialEffects(192f, false, DimensionSpecialEffects.SkyType.NONE, false, false) {
                @Override
                public Vec3 getBrightnessDependentFogColor(Vec3 color, float sunHeight) {
                    return new Vec3(0.1, 0.12, 0.15);
                }

                @Override
                public boolean isFoggyAt(int x, int y) {
                    return true;
                }
            };

            // Updated to use the modern ResourceLocation factory method
            event.register(ResourceLocation.fromNamespaceAndPath("legend_chasers_remake", "mining_dimension"), customEffect);
        }
    }
}