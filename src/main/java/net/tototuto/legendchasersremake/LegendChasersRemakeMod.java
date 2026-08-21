package net.tototuto.legendchasersremake;

import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModItems;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModTabs;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.TickTask;

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

@Mod(LegendChasersRemakeMod.MODID)
public class LegendChasersRemakeMod {
	public static final Logger LOGGER = LogManager.getLogger(LegendChasersRemakeMod.class);
	public static final String MODID = "legend_chasers_remake";

	public LegendChasersRemakeMod(FMLJavaModLoadingContext context) {
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = context.getModEventBus();

		// Enregistrement des éléments
		LegendChasersRemakeModItems.REGISTRY.register(bus);
		LegendChasersRemakeModTabs.REGISTRY.register(bus);
		LegendChasersRemakeModBlocks.BLOCKS.register(bus);
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

	private static final Queue<IntObjectPair<Runnable>> workToBeScheduled = new ConcurrentLinkedQueue<>();
	private static final PriorityQueue<TickTask> workQueue = new PriorityQueue<>(Comparator.comparingInt(TickTask::getTick));

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
				workQueue.add(new TickTask(currentTick + work.leftInt(), work.right()));
			}
			while (!workQueue.isEmpty() && currentTick >= workQueue.peek().getTick()) {
				workQueue.poll().run();
			}
		}
	}
}