package net.tototuto.legendchasersremake.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tototuto.legendchasersremake.worldgen.dimension.LegendsChasersRemakeDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseCoralPlantTypeBlock.class)
public abstract class BaseCoralPlantTypeBlockMixin {

    @Inject(method = "scanForWater", at = @At("HEAD"), cancellable = true)
    private static void surviveWithoutWaterInDimension(BlockState state, BlockGetter blockGetter, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Le paramètre "blockGetter" remplace "level"
        if (blockGetter instanceof Level world) {
            if (world.dimension().equals(LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY)) {
                cir.setReturnValue(true);
            }
        }
    }
}