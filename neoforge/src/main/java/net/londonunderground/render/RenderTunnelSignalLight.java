package net.londonunderground.render;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.mtr.block.BlockSignalBase;
import org.mtr.client.IDrawing;
import org.mtr.render.MainRenderer;
import org.mtr.render.QueuedRenderLayer;
import org.mtr.render.RenderSignalLight2Aspect;
import org.mtr.render.StoredMatrixTransformations;

public class RenderTunnelSignalLight<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalLight2Aspect<T> {

	private final int proceedColor;
	private final boolean redOnTop;

	public RenderTunnelSignalLight(boolean redOnTop, int proceedColor) {
		super(redOnTop, proceedColor);
		this.proceedColor = proceedColor;
		this.redOnTop = redOnTop;
	}

	@Override
	protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, ClientLevel level, float tickDelta, int occupiedAspect, int packedLight, boolean isBackSide) {
		final float y = (occupiedAspect > 0) == redOnTop ? 0.25F : 0.4375F;
		MainRenderer.scheduleRender(ResourceLocation.fromNamespaceAndPath("mtr", "textures/block/white.png"), false, QueuedRenderLayer.LIGHT, (poseStack, vertexConsumer, offset) -> {
			storedMatrixTransformations.transform(poseStack, offset);
			IDrawing.drawTexture(poseStack, vertexConsumer, -0.25F, y, -0.0625F, 0.3125F, y + 0.1875F, 0.3125F, Direction.UP, occupiedAspect > 0 ? 0xFFFF0000 : proceedColor, packedLight);
			poseStack.popPose();
		});
	}
}
