package net.tototuto.legendchasersremake.client.renderer;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class PrissazDimensionEffects extends DimensionSpecialEffects {
    public PrissazDimensionEffects() {
        // float cloudLevel, boolean hasGround, SkyType skyType, boolean forceBrightLightmap, boolean constantAmbientLight
        super(Float.NaN, true, SkyType.NORMAL, false, false);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 clientFogColor, float daytime) {
        return clientFogColor;
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return false;
    }
}