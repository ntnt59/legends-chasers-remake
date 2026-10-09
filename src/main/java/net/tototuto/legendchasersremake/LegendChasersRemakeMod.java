package net.tototuto.legendchasersremake;

import net.tototuto.legendchasersremake.init.LCRFeatures;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModItems;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModTabs;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import it.unimi.dsi.fastutil.ints.IntObjectImmutablePair;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(LegendChasersRemakeMod.MODID)
public class LegendChasersRemakeMod {
	public static final Logger LOGGER = LogManager.getLogger(LegendChasersRemakeMod.class);
	public static final String MODID = "legend_chasers_remake";

	public LegendChasersRemakeMod(FMLJavaModLoadingContext context) {
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = context.getModEventBus();

		// 1. D'abord les blocs (car les items et onglets en ont besoin)
		LegendChasersRemakeModBlocks.BLOCKS.register(bus);

		// 2. Ensuite les items
		LegendChasersRemakeModItems.REGISTRY.register(bus);

		// 3. Ensuite les onglets créatifs
		LegendChasersRemakeModTabs.REGISTRY.register(bus);

		// 4. Enfin les features
		LCRFeatures.FEATURES.register(bus);

		bus.addListener(this::commonSetup);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			net.minecraft.world.level.block.FlowerPotBlock flowerPot =
					(net.minecraft.world.level.block.FlowerPotBlock) net.minecraft.world.level.block.Blocks.FLOWER_POT;

			flowerPot.addPlant(ResourceLocation.fromNamespaceAndPath(MODID, "paraphyte"), LegendChasersRemakeModBlocks.POTTED_PARAPHYTE);
			flowerPot.addPlant(ResourceLocation.fromNamespaceAndPath(MODID, "florophyte"), LegendChasersRemakeModBlocks.POTTED_FLOROPHYTE);
			flowerPot.addPlant(ResourceLocation.fromNamespaceAndPath(MODID, "bubblephyte"), LegendChasersRemakeModBlocks.POTTED_BUBBLEPHYTE);
		});
	}

	// --- Réseaux & Tâches ---
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
			ResourceLocation.fromNamespaceAndPath(MODID, MODID),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	// Structure simple pour stocker une tâche planifiée avec son tick d'exécution
	public record ScheduledTask(int executionTick, Runnable action) {}

	private static final Queue<IntObjectPair<Runnable>> workToBeScheduled = new ConcurrentLinkedQueue<>();
	private static final PriorityQueue<ScheduledTask> workQueue = new PriorityQueue<>(Comparator.comparingInt(ScheduledTask::executionTick));

	public static void queueServerWork(int delay, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workToBeScheduled.add(new IntObjectImmutablePair<>(delay, action));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			int currentTick = event.getServer().getTickCount();
			IntObjectPair<Runnable> work;
			while ((work = workToBeScheduled.poll()) != null) {
				workQueue.add(new ScheduledTask(currentTick + work.leftInt(), work.right()));
			}
			while (!workQueue.isEmpty() && currentTick >= workQueue.peek().executionTick()) {
				workQueue.poll().action().run();
			}
		}
	}
}