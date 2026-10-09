package net.tototuto.legendchasersremake.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tototuto.legendchasersremake.worldgen.dimension.LegendsChasersRemakeDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralBlock.class)
public abstract class CoralBlockMixin {

    // Empêche le corail de mourir lors du tick s'il est dans votre dimension
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void cancelTickInDimension(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (level.dimension().equals(LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY)) {
            ci.cancel(); // Annule la vérification de mort
        }
    }

    // Empêche la planification de la mort lors de la mise à jour des blocs voisins
    @Inject(method = "updateShape", at = @At("HEAD"), cancellable = true)
    private void cancelUpdateShapeInDimension(BlockState state, net.minecraft.core.Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (level instanceof ServerLevel serverLevel && serverLevel.dimension().equals(LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY)) {
            // Laisse le comportement de base du bloc sans programmer sa mort
            cir.setReturnValue(state);
        }
    }
}