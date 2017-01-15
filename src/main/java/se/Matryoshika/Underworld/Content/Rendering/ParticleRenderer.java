package se.Matryoshika.Underworld.Content.Rendering;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleRenderer {
	
	public static int fireflyFXCount = 0;
	public static int fireflyDICount = 0;

	// Called from LightningHandler.onRenderWorldLast since that was
	// already registered. /shrug
	public static void dispatch() {
		Tessellator tessellator = Tessellator.getInstance();

		Profiler profiler = Minecraft.getMinecraft().mcProfiler;

		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		//GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
		GlStateManager.disableLighting();

		profiler.startSection("underworld:firefly");
		FireflyFX.dispatchQueuedRenders(tessellator);
		profiler.endSection();

		//GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		//GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_SRC_ALPHA);
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
		GL11.glPopAttrib();
	}
	
	@SubscribeEvent
	public static void onRenderWorldLast(RenderWorldLastEvent event){
		ParticleRenderer.dispatch();
	}

}
