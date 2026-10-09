package net.tototuto.legendchasersremake.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GlowingCoralBlock extends Block {
    public static final IntegerProperty AMBIENT_LIGHT = IntegerProperty.create("ambient_light", 0, 15);

    public GlowingCoralBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AMBIENT_LIGHT, 15));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AMBIENT_LIGHT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        boolean isNight = level.isNight();
        int target = isNight ? 0 : 15;
        return this.defaultBlockState().setValue(AMBIENT_LIGHT, target);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            // Planifie un premier contrôle 100 ticks plus tard
            level.scheduleTick(pos, this, 100);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        updateAmbientLight(level, pos, state, random);
        // Revérifie régulièrement (toutes les 5 secondes)
        level.scheduleTick(pos, this, 100);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        if (!level.isClientSide) {
            updateAmbientLight((ServerLevel) level, pos, state, level.random);
        }
    }

    private void updateAmbientLight(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        boolean isNight = level.isNight();
        int targetStateValue;

        if (isNight) {
            // S'il fait nuit, on introduit de l'aléatoire :
            // Par exemple, 30% de chance à chaque tick de vérification de s'éteindre (passer à 0)
            if (random.nextFloat() < 0.30f) {
                targetStateValue = 0;
            } else {
                targetStateValue = state.getValue(AMBIENT_LIGHT); // Conserve l'état actuel en attendant
            }
        } else {
            // Le jour, on remet tout de suite à 15
            targetStateValue = 15;
        }

        if (state.getValue(AMBIENT_LIGHT) != targetStateValue) {
            level.setBlock(pos, state.setValue(AMBIENT_LIGHT, targetStateValue), 2);
        }
    }
}