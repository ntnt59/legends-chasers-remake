package net.tototuto.legendchasersremake.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tototuto.legendchasersremake.worldgen.dimension.LegendsChasersRemakeDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralBlock.class)
public abstract class CoralBlockMixin {

    @Inject(method = "scanForWater", at = @At("HEAD"), cancellable = true)
    private void surviveWithoutWaterInDimension(BlockGetter blockGetter, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (blockGetter instanceof Level world) {
            if (world.dimension().equals(LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY)) {
                cir.setReturnValue(true);
            }
        }
    }
}