package net.tototuto.legendchasersremake.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.worldgen.dimension.LegendsChasersRemakeDimensions;
import org.joml.Matrix4f;

import java.util.Random;

@Mod.EventBusSubscriber(modid = LegendChasersRemakeMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientLCREvents {

    private static final ResourceLocation CUSTOM_SUN = ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "textures/environment/prissaz_sun.png");
    private static final ResourceLocation CUSTOM_MOON = ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "textures/environment/prissaz_moon.png");

    private static VertexBuffer skyDiscBuffer;
    private static VertexBuffer voidDiscBuffer;
    private static VertexBuffer starBuffer;

    private static void fillDisc(BufferBuilder builder, float y) {
        float radius = Math.signum(y) * 512.0F;
        builder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
        builder.vertex(0.0F, y, 0.0F).endVertex();
        for (int i = -180; i <= 180; i += 45) {
            double rad = Math.toRadians(i);
            builder.vertex(radius * (float) Math.cos(rad), y, radius * (float) Math.sin(rad)).endVertex();
        }
    }

    private static VertexBuffer buildDiscBuffer(float y) {
        BufferBuilder builder = Tesselator.getInstance().getBuilder();
        fillDisc(builder, y);
        VertexBuffer vb = new VertexBuffer(VertexBuffer.Usage.STATIC);
        vb.bind();
        vb.upload(builder.end());
        VertexBuffer.unbind();
        return vb;
    }

    private static VertexBuffer buildStarBuffer() {
        BufferBuilder builder = Tesselator.getInstance().getBuilder();
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        Random random = new Random(4321L);
        int placed = 0;
        while (placed < 1200) {
            double nx = random.nextFloat() * 2.0 - 1.0;
            double ny = random.nextFloat() * 2.0 - 1.0;
            double nz = random.nextFloat() * 2.0 - 1.0;
            double size = 0.15 + random.nextFloat() * 0.1;
            double lenSq = nx * nx + ny * ny + nz * nz;
            if (lenSq >= 1.0D || lenSq <= 0.01D) continue;
            double invLen = 1.0D / Math.sqrt(lenSq);
            nx *= invLen; ny *= invLen; nz *= invLen;
            double px = nx * 100.0D, py = ny * 100.0D, pz = nz * 100.0D;

            double theta = Math.atan2(nx, nz);
            double sinTheta = Math.sin(theta), cosTheta = Math.cos(theta);
            double phi = Math.atan2(Math.sqrt(nx * nx + nz * nz), ny);
            double sinPhi = Math.sin(phi), cosPhi = Math.cos(phi);
            double roll = random.nextDouble() * Math.PI * 2.0D;
            double sinRoll = Math.sin(roll), cosRoll = Math.cos(roll);

            for (int corner = 0; corner < 4; corner++) {
                double cx = ((corner & 2) - 1) * size;
                double cz = ((corner + 1 & 2) - 1) * size;
                double rx = cx * cosRoll - cz * sinRoll;
                double rz = cz * cosRoll + cx * sinRoll;
                double tiltY = rx * sinPhi;
                double tiltZ = -rx * cosPhi;
                double finalX = tiltZ * sinTheta - rz * cosTheta;
                double finalZ = rz * sinTheta + tiltZ * cosTheta;
                builder.vertex((float) (px + finalX), (float) (py + tiltY), (float) (pz + finalZ)).endVertex();
            }
            placed++;
        }
        VertexBuffer vb = new VertexBuffer(VertexBuffer.Usage.STATIC);
        vb.bind();
        vb.upload(builder.end());
        VertexBuffer.unbind();
        return vb;
    }

    @SubscribeEvent
    public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        DimensionSpecialEffects prissazEffects = new DimensionSpecialEffects(192.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false) {

            @Override
            public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float sunHeight) {
                if (sunHeight < 0.2F) {
                    float factor = Math.max(0.0F, (sunHeight + 0.2F) / 0.2F);
                    Vec3 nightColor = new Vec3(0.01D, 0.01D, 0.02D);
                    Vec3 dayColor = fogColor.multiply(0.8D, 0.9D, 1.2D);
                    return nightColor.scale(1.0F - factor).add(dayColor.scale(factor));
                }
                return fogColor.multiply(0.8D, 0.9D, 1.2D);
            }

            @Override
            public boolean isFoggyAt(int x, int y) {
                return false;
            }

            @Override
            public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
                if (skyDiscBuffer == null) skyDiscBuffer = buildDiscBuffer(16.0F);
                if (voidDiscBuffer == null) voidDiscBuffer = buildDiscBuffer(-16.0F);
                if (starBuffer == null) starBuffer = buildStarBuffer();

                setupFog.run();
                BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
                float starBrightness = level.getStarBrightness(partialTick);

                // --- 1. DISQUE DE CIEL ---
                RenderSystem.depthMask(false);
                RenderSystem.setShader(GameRenderer::getPositionShader);
                Vec3 skyColor = level.getSkyColor(camera.getPosition(), partialTick);
                RenderSystem.setShaderColor((float) skyColor.x, (float) skyColor.y, (float) skyColor.z, 1.0F);

                skyDiscBuffer.bind();
                skyDiscBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
                VertexBuffer.unbind();

                // --- 2. DISQUE DE VIDE ---
                RenderSystem.setShaderColor(0.02F, 0.02F, 0.03F, 1.0F);
                voidDiscBuffer.bind();
                voidDiscBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
                VertexBuffer.unbind();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                // --- 3. SOLEIL / LUNE / ÉTOILES ---
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(
                        GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
                        GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO
                );

                poseStack.pushPose();
                float rainModifier = 1.0F - level.getRainLevel(partialTick);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, rainModifier);
                poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));
                Matrix4f celestialMatrix = poseStack.last().pose();

                // --- SOLEIL ---
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, CUSTOM_SUN);
                float sunSize = 30.0F;
                bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                bufferbuilder.vertex(celestialMatrix, -sunSize, 100.0F, -sunSize).uv(0.0F, 0.0F).endVertex();
                bufferbuilder.vertex(celestialMatrix, sunSize, 100.0F, -sunSize).uv(1.0F, 0.0F).endVertex();
                bufferbuilder.vertex(celestialMatrix, sunSize, 100.0F, sunSize).uv(1.0F, 1.0F).endVertex();
                bufferbuilder.vertex(celestialMatrix, -sunSize, 100.0F, sunSize).uv(0.0F, 1.0F).endVertex();
                BufferUploader.drawWithShader(bufferbuilder.end());

                // --- LUNE ---
                RenderSystem.setShaderTexture(0, CUSTOM_MOON);
                float moonSize = 20.0F;
                bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                bufferbuilder.vertex(celestialMatrix, -moonSize, -100.0F, moonSize).uv(1.0F, 1.0F).endVertex();
                bufferbuilder.vertex(celestialMatrix, moonSize, -100.0F, moonSize).uv(0.0F, 1.0F).endVertex();
                bufferbuilder.vertex(celestialMatrix, moonSize, -100.0F, -moonSize).uv(0.0F, 0.0F).endVertex();
                bufferbuilder.vertex(celestialMatrix, -moonSize, -100.0F, -moonSize).uv(1.0F, 0.0F).endVertex();
                BufferUploader.drawWithShader(bufferbuilder.end());

                poseStack.popPose();

                // --- 4. ÉTOILES ---
                if (starBrightness > 0.0F) {
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                    poseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));
                    Matrix4f starMatrix = poseStack.last().pose();

                    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
                    RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
                    RenderSystem.setShader(GameRenderer::getPositionShader);
                    starBuffer.bind();
                    starBuffer.drawWithShader(starMatrix, projectionMatrix, GameRenderer.getPositionShader());
                    VertexBuffer.unbind();
                    poseStack.popPose();
                }

                // --- 5. RESET COMPLET DE L'ÉTAT ---
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.depthMask(true);

                return true; // Empêche le ciel vanilla de s'afficher par-dessus, mais laisse le niveau gérer la logique de météo
            }
        };

        event.register(LegendsChasersRemakeDimensions.PRISSAZ_EFFECTS, prissazEffects);
    }
}