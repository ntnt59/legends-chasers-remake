package net.tototuto.legendchasersremake.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tototuto.legendchasersremake.worldgen.dimension.LegendsChasersRemakeDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseCoralPlantTypeBlock.class)
public abstract class BaseCoralPlantTypeBlockMixin {

    @Inject(method = "tryScheduleDieTick", at = @At("HEAD"), cancellable = true)
    private void cancelDieTickInDimension(BlockState state, LevelAccessor level, BlockPos pos, CallbackInfo ci) {
        if (level instanceof net.minecraft.world.level.Level world && world.dimension().equals(LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY)) {
            ci.cancel();
        }
    }
}