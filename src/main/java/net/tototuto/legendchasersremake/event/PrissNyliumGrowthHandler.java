package net.tototuto.legendchasersremake.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

@Mod.EventBusSubscriber(modid = LegendChasersRemakeMod.MODID)
public class PrissNyliumGrowthHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos clickedPos = event.getPos();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack heldItem = player.getItemInHand(hand);

        // 1. VÉRIFICATION DE BASE : Item = Bone Meal & Bloc cliqué = Pierre Priss
        if (heldItem.is(Items.BONE_MEAL) && level.getBlockState(clickedPos).is(LegendChasersRemakeModBlocks.PRISS_STONE.get())) {

            BlockPos abovePos = clickedPos.above();
            BlockState aboveState = level.getBlockState(abovePos);

            boolean hasObstacleAbove = !aboveState.isAir() && !aboveState.canBeReplaced();

            if (!hasObstacleAbove) {
                if (hasAdjacentNylium(level, clickedPos)) {

                    // Animation de la main côté client & serveur
                    player.swing(hand);

                    // La modification logique du monde se fait UNIQUEMENT côté serveur
                    if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {

                        // A) Transformer la Pierre en Nylium Priss
                        serverLevel.setBlockAndUpdate(
                                clickedPos,
                                LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get().defaultBlockState()
                        );

                        // B) Déclencher les effets visuels et sonores de la Poudre d'os
                        playBonemealEffects(serverLevel, clickedPos);

                        // C) Consommer la poudre d'os (sauf si le joueur est en créatif)
                        if (!player.getAbilities().instabuild) {
                            heldItem.shrink(1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Scanne les 6 blocs autour de la position ciblée
     */
    private static boolean hasAdjacentNylium(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = level.getBlockState(neighborPos);

            if (neighborState.is(LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get())) {
                return true; // Un Nylium voisin a été trouvé !
            }
        }
        return false;
    }

    /**
     * Envoie le son et les particules de Bone Meal Vanilla
     */
    private static void playBonemealEffects(ServerLevel level, BlockPos pos) {
        // Le code événement 2005 correspond aux particules + son de la poudre d'os dans Vanilla
        level.levelEvent(2005, pos, 0);
    }
}