package net.tototuto.legendchasersremake.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.portal.*;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class LegendsChasersRemakeTeleporter implements ITeleporter {
    private final BlockPos pos;
    private final boolean insideDimension;

    public LegendsChasersRemakeTeleporter(BlockPos pos, boolean insideDimension) {
        this.pos = pos;
        this.insideDimension = insideDimension;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {

        Entity teleportedEntity = repositionEntity.apply(false);

        // Position de destination : juste au-dessus du bloc cliqué
        BlockPos targetPos = this.pos.above();

        // Sécurité : s'assure qu'il n'y a pas de bloc solide où le joueur apparaît
        if (destWorld.getBlockState(targetPos).isSolid()) {
            destWorld.setBlockAndUpdate(targetPos, Blocks.AIR.defaultBlockState());
            destWorld.setBlockAndUpdate(targetPos.above(), Blocks.AIR.defaultBlockState());
        }

        teleportedEntity.teleportTo(
                targetPos.getX() + 0.5,
                targetPos.getY(),
                targetPos.getZ() + 0.5
        );

        return teleportedEntity;
    }
}