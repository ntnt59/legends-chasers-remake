package net.tototuto.legendchasersremake.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

import java.util.HashSet;
import java.util.Set;

public class GiantCanopyTreeFeature extends Feature<TreeConfiguration> {

    public GiantCanopyTreeFeature(Codec<TreeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        TreeConfiguration config = context.config();

        // 1. Vérification du sol (ex: Herbe, Terre, Sand, etc.)
        BlockState stateBelow = level.getBlockState(origin.below());
        if (!stateBelow.is(LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get())) {
            return false;
        }

        // 2. Calcul de la hauteur de l'arbre (Min 6, Max 11 blocs)
        int treeHeight = Mth.nextInt(random, 6, 11);

        // Vérification de la hauteur limite du monde
        if (origin.getY() + treeHeight + 3 >= level.getMaxBuildHeight()) {
            return false;
        }

        Set<BlockPos> trunkPositions = new HashSet<>();
        Set<BlockPos> leafPositions = new HashSet<>();

        // 3. Génération du Tronc (Inspiré de HugeFungus + TreeFeature)
        BlockState logState = config.trunkProvider.getState(random, origin);
        for (int y = 0; y < treeHeight; ++y) {
            BlockPos trunkPos = origin.above(y);
            if (isReplaceable(level, trunkPos)) {
                level.setBlock(trunkPos, logState, 19);
                trunkPositions.add(trunkPos);
            }
        }

        // 4. Génération du Chapeau/Feuillage (Inspiré de HugeFungusFeature)
        BlockPos topPos = origin.above(treeHeight);
        BlockState leafState = config.foliageProvider.getState(random, topPos);

        // S'assurer que les feuilles sont persistantes pour ne pas despawn
        if (leafState.hasProperty(LeavesBlock.PERSISTENT)) {
            leafState = leafState.setValue(LeavesBlock.PERSISTENT, true);
        }

        int hatHeight = Math.min(random.nextInt(1 + treeHeight / 3) + 3, treeHeight);
        int startY = treeHeight - hatHeight;

        for (int k = startY; k <= treeHeight; ++k) {
            int radius = (k < treeHeight - random.nextInt(2)) ? 2 : 1;
            if (treeHeight > 8 && k < startY + 2) {
                radius = 3; // Élargissement de la base de la canopy pour les grands arbres
            }

            for (int dx = -radius; dx <= radius; ++dx) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    boolean isCorner = (Math.abs(dx) == radius && Math.abs(dz) == radius);

                    // On arrondit les angles du dôme
                    if (isCorner && random.nextFloat() < 0.4F) {
                        continue;
                    }

                    BlockPos leafPos = origin.offset(dx, k, dz);
                    if (isReplaceable(level, leafPos) && !trunkPositions.contains(leafPos)) {
                        level.setBlock(leafPos, leafState, 19);
                        leafPositions.add(leafPos);
                    }
                }
            }
        }

        // 5. Mise à jour de la propriété DISTANCE des feuilles (TreeFeature)
        updateLeafDistances(level, trunkPositions, leafPositions);

        return true;
    }

    private static boolean isReplaceable(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, state ->
                state.isAir() || state.is(BlockTags.REPLACEABLE_BY_TREES) || state.is(BlockTags.LEAVES)
        );
    }

    private void updateLeafDistances(WorldGenLevel level, Set<BlockPos> trunks, Set<BlockPos> leaves) {
        for (BlockPos leafPos : leaves) {
            int minDistance = 7;
            for (BlockPos trunkPos : trunks) {
                int dist = Math.abs(leafPos.getX() - trunkPos.getX())
                        + Math.abs(leafPos.getY() - trunkPos.getY())
                        + Math.abs(leafPos.getZ() - trunkPos.getZ());
                minDistance = Math.min(minDistance, dist);
            }

            BlockState state = level.getBlockState(leafPos);
            if (state.hasProperty(BlockStateProperties.DISTANCE)) {
                level.setBlock(leafPos, state.setValue(BlockStateProperties.DISTANCE, Math.min(minDistance, 7)), 19);
            }
        }
    }
}