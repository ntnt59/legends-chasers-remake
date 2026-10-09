package net.tototuto.legendchasersremake.event;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent.BlockToolModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

@Mod.EventBusSubscriber(modid = LegendChasersRemakeMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AxeStrippingHandler {

    @SubscribeEvent
    public static void onToolModification(BlockToolModificationEvent event) {
        // Vérifie qu'il s'agit d'une action d'écorçage (axe strip)
        if (event.getToolAction() == ToolActions.AXE_STRIP) {
            BlockState originalState = event.getState();

            // Vérifie si le bloc cliqué est votre bûche non écorchée
            if (originalState.is(LegendChasersRemakeModBlocks.PROTIUM_LOG.get())) {
                BlockState strippedState = LegendChasersRemakeModBlocks.STRIPPED_PROTIUM_LOG.get().defaultBlockState()
                        .setValue(RotatedPillarBlock.AXIS, originalState.getValue(RotatedPillarBlock.AXIS));

                // Applique le nouvel état écorché (gère automatiquement la durabilité et le son côté vanilla)
                event.setFinalState(strippedState);
            }
        }
    }
}