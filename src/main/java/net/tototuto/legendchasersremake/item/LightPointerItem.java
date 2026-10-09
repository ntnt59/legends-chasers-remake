package net.tototuto.legendchasersremake.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.UUID;
import java.util.Random;

@Mod.EventBusSubscriber
public class LightPointerItem extends Item {

    // Structure pour suivre les joueurs en train de tirer leur rafale
    private static final HashMap<UUID, BurstSession> ACTIVE_BURSTS = new HashMap<>();
    private static final Random RANDOM = new Random();

    public LightPointerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Si le joueur a déjà un cooldown ou est déjà en train de tirer, on ignore
        if (player.getCooldowns().isOnCooldown(this) || ACTIVE_BURSTS.containsKey(player.getUUID())) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide()) {
            // Lancer la session de rafale : 16 points sur 60 ticks (3 secondes)
            // 60 ticks / 16 points ≈ 1 point tous les ~3.75 ticks
            ACTIVE_BURSTS.put(player.getUUID(), new BurstSession(16, 60));

            // Son de début de scan
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.5F, 1.5F);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    // Gestionnaire de Ticks global pour faire progresser les rafales de tous les joueurs
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide()) {
            Player player = event.player;
            UUID uuid = player.getUUID();

            if (ACTIVE_BURSTS.containsKey(uuid)) {
                BurstSession session = ACTIVE_BURSTS.get(uuid);
                session.ticksElapsed++;

                // Calculer à quel tick on doit poser le prochain point
                // Intervalle = Durée totale (60 ticks) / Nombre de points (16) = ~3.75 ticks
                int targetTickForNextPoint = (int) ((session.pointsFired + 1) * (session.totalTicks / (double) session.totalPoints));

                if (session.ticksElapsed >= targetTickForNextPoint && session.pointsFired < session.totalPoints) {
                    fireRandomPoint(player);
                    session.pointsFired++;
                }

                // Fin de la rafale (après 3 secondes / 60 ticks)
                if (session.ticksElapsed >= session.totalTicks) {
                    ACTIVE_BURSTS.remove(uuid);
                    // Appliquer le cooldown de 3 secondes (60 ticks) après la pose de TOUS les points
                    // Note: on cherche l'item dans les mains du joueur pour lui mettre le cooldown
                    ItemStack mainHand = player.getMainHandItem();
                    ItemStack offHand = player.getOffhandItem();
                    Item itemToCooldown = null;
                    if (mainHand.getItem() instanceof LightPointerItem) itemToCooldown = mainHand.getItem();
                    else if (offHand.getItem() instanceof LightPointerItem) itemToCooldown = offHand.getItem();

                    if (itemToCooldown != null) {
                        player.getCooldowns().addCooldown(itemToCooldown, 60);
                    }
                }
            }
        }
    }

    // Méthode pour tirer un point unique avec une dispersion aléatoire dans le champ de vision
    private static void fireRandomPoint(Player player) {
        Level level = player.level();

        // Récupérer le regard de base du joueur
        Vec3 lookVec = player.getLookAngle();

        // Ajouter une dispersion aléatoire (Spread) pour répartir les points dans le champ de vision
        // Ajustez le facteur (ex: 0.35F) pour élargir ou rétrécir la zone couverte
        double spreadX = (RANDOM.nextDouble() - 0.5D) * 0.5D;
        double spreadY = (RANDOM.nextDouble() - 0.5D) * 0.4D;
        double spreadZ = (RANDOM.nextDouble() - 0.5D) * 0.5D;

        Vec3 spreadLook = new Vec3(lookVec.x + spreadX, lookVec.y + spreadY, lookVec.z + spreadZ).normalize();

        // Calculer la position de départ (yeux du joueur) et la fin du raycast (portée de 20 blocs)
        Vec3 eyePos = player.getEyePosition();
        Vec3 endPos = eyePos.add(spreadLook.scale(20.0D));

        // Effectuer le Raycast
        HitResult rayTraceResult = level.clip(new net.minecraft.world.level.ClipContext(
                eyePos, endPos,
                net.minecraft.world.level.ClipContext.Block.COLLIDER,
                net.minecraft.world.level.ClipContext.Fluid.NONE,
                player
        ));

        if (rayTraceResult.getType() == HitResult.Type.BLOCK) {
            Vec3 hitPos = rayTraceResult.getLocation();

            // Envoyer la particule (Visible par tous les clients autour)
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.END_ROD,
                        hitPos.x, hitPos.y, hitPos.z,
                        1, 0.0D, 0.0D, 0.0D, 0.0D);
            }

            // Petit son discret à chaque point posé
            level.playSound(null, hitPos.x, hitPos.y, hitPos.z,
                    SoundEvents.STONE_FALL, SoundSource.PLAYERS, 0.2F, 2.0F);
        }
    }

    // Classe interne pour suivre l'état d'une rafale en cours pour un joueur
    private static class BurstSession {
        int totalPoints;
        int totalTicks;
        int ticksElapsed = 0;
        int pointsFired = 0;

        public BurstSession(int totalPoints, int totalTicks) {
            this.totalPoints = totalPoints;
            this.totalTicks = totalTicks;
        }
    }
}