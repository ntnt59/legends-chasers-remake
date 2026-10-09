package net.tototuto.legendchasersremake.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

public class LCRCoralTreeFeature extends Feature<NoneFeatureConfiguration> {
    public LCRCoralTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource random = context.random();
        LevelAccessor level = context.level();
        BlockPos pos = context.origin(); // On repart directement du sol marin trouvé par le placement

        BlockState coralState = LegendChasersRemakeModBlocks.ABYSSAL_CORAL_BLOCK.get().defaultBlockState();

        // Vérification optionnelle : s'assurer qu'on est bien sur un bloc solide et sous l'eau
        if (!level.getBlockState(pos).isSolid() || !level.getBlockState(pos.above()).is(Blocks.WATER)) {
            return false;
        }

        BlockPos.MutableBlockPos mutablePos = pos.mutable();
        int height = random.nextInt(3) + 1;

        for (int j = 0; j < height; ++j) {
            // On pose le bloc (le premier se posera sur le sol, les suivants monteront dans l'eau)
            if (!this.placeCustomCoralBlock(level, mutablePos, coralState)) {
                return true;
            }
            mutablePos.move(Direction.UP);
        }

        BlockPos topPos = mutablePos.immutable();
        int branchesCount = random.nextInt(3) + 2;
        java.util.List<Direction> directions = Plane.HORIZONTAL.shuffledCopy(random);

        for (Direction direction : directions.subList(0, branchesCount)) {
            mutablePos.set(topPos);
            mutablePos.move(direction);
            int branchLength = random.nextInt(5) + 2;

            for (int j1 = 0; j1 < branchLength && this.placeCustomCoralBlock(level, mutablePos, coralState); ++j1) {
                mutablePos.move(Direction.UP);
                if (random.nextFloat() < 0.25F) {
                    mutablePos.move(direction);
                }
            }
        }
        return true;
    }

    private boolean placeCustomCoralBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockState current = level.getBlockState(pos);
        // On autorise à poser si c'est de l'eau ou si c'est un bloc solide (pour la base au sol)
        if (current.is(Blocks.WATER) || current.isSolid()) {
            level.setBlock(pos, state, 3);
            return true;
        }
        return false;
    }
}