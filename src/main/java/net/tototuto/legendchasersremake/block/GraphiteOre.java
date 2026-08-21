package net.tototuto.legendchasersremake.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GraphiteOre extends Block {
    public GraphiteOre(Properties properties) {
        super(properties);
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader level, RandomSource random, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        if (silkTouchLevel > 0) {
            return 0;
        } else {
            return random.nextIntBetweenInclusive(0, 2);
        }
    }
}
