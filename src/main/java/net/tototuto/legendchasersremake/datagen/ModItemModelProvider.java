package net.tototuto.legendchasersremake.datagen;

import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LegendChasersRemakeMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // --- Exemples d'utilisation ---

        // 1. Pour un item 2D classique (ex: un lingot, une pomme, etc.)
        simpleItem(LegendChasersRemakeModItems.AQUAMARINE);

        // 2. Pour un outil ou une arme tenue comme un bâton (épée, pioche, etc.)
        /*handheldItem(ModItems.MON_EPEE);*/

        // 3. Pour un item qui utilise le modèle 3D d'un bloc existant (ex: un bloc posable sous forme d'item)
        // buttonItem(ModItems.MON_BOUTON, ModBlocks.MON_BOUTON);
    }

    // Helper pour générer un item 2D standard (utilise le parent "item/generated")
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "item/" + item.getId().getPath()));
    }

    // Helper pour générer un outil 2D (utilise le parent "item/handheld")
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath("minecraft", "item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "item/" + item.getId().getPath()));
    }
}