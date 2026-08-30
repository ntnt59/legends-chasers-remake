package net.tototuto.legendchasersremake.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tototuto.legendchasersremake.init.LCRBlockTags;

public class PrissazGrass extends BushBlock{
    protected static final VoxelShape SHAPE = BushBlock.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);

    public PrissazGrass(Properties properties) {
        super(properties);

        }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        boolean isVanillaPlantable = super.mayPlaceOn(state, level, pos);
        boolean isLCRPlantable = state.is(LCRBlockTags.Blocks.IS_LCR_PLANTABLE);
        return isVanillaPlantable || isLCRPlantable;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
        return SHAPE;
    }
}
