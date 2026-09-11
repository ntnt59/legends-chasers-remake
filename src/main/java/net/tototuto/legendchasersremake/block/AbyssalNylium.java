package net.tototuto.legendchasersremake.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

public class AbyssalNylium extends Block {
    public AbyssalNylium(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos abovePos = pos.above();

        boolean hasObstacleAbove = level.getBlockState(abovePos).isSolidRender(level, abovePos);

        if (hasObstacleAbove) {
            level.setBlockAndUpdate(pos, LegendChasersRemakeModBlocks.PRISS_STONE.get().defaultBlockState());
        }
    }
}
