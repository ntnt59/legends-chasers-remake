package net.tototuto.legendchasersremake.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tototuto.legendchasersremake.worldgen.dimension.LegendsChasersRemakeDimensions;
import net.tototuto.legendchasersremake.worldgen.portal.LegendsChasersRemakeTeleporter;

public class BlueMysteriousBricks extends Block {
    public BlueMysteriousBricks(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            handlePortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }

    private void handlePortal(Entity player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverlevel) {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY ?
                    Level.OVERWORLD : LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                if (resourcekey == LegendsChasersRemakeDimensions.PRISSAZ_LEVEL_KEY) {
                    player.changeDimension(portalDimension, new LegendsChasersRemakeTeleporter(pPos, true));
                } else {
                    player.changeDimension(portalDimension, new LegendsChasersRemakeTeleporter(pPos, false));
                }
            }
        }
    }
}
