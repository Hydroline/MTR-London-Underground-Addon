package net.londonunderground.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Locale;
import net.londonunderground.init.MainNeoForge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.mtr.MTRClient;
import org.mtr.core.data.Station;
import org.mtr.data.IGui;
import org.mtr.render.BlockEntityRendererExtension;

public class RenderRoundel<T extends BlockEntity> extends BlockEntityRendererExtension<T> implements IGui {

	private final float maxWidth;
	private final float maxScale;
	private final float xOffset;
	private final float yOffset;
	private final float zOffset;
	private final float xTilt;
	private final int textColor;
	private final boolean doubleSided;
	private final String fontName;

	public RenderRoundel(float maxWidth, float maxScale, float xOffset, float yOffset, float zOffset, float xTilt, int textColor, boolean doubleSided, String fontName) {
		this.maxWidth = maxWidth;
		this.maxScale = maxScale;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
		this.xTilt = xTilt;
		this.textColor = textColor;
		this.doubleSided = doubleSided;
		this.fontName = fontName;
	}

	@Override
	public void render(T entity, PoseStack poseStack, MultiBufferSource bufferSource, ClientLevel level, LocalPlayer player, float tickDelta, int packedLight, int packedOverlay) {
		final BlockPos pos = entity.getBlockPos();
		final BlockState state = entity.getBlockState();
		final Direction facing = state.hasProperty(BlockStateProperties.HORIZONTAL_FACING) ? state.getValue(BlockStateProperties.HORIZONTAL_FACING) : Direction.NORTH;
		final Station station = MTRClient.findStation(pos);
		final String stationName = IGui.textOrUntitled(IGui.formatStationName(station == null ? "" : station.getName())).toUpperCase(Locale.ROOT);
		final MutableComponent text = withConfiguredFont(Component.literal(stationName));
		final Font font = Minecraft.getInstance().font;
		final int textWidth = font.width(text);
		if (textWidth <= 0) {
			return;
		}

		final float scale = Math.min(maxWidth / textWidth, maxScale);
		final int renderCount = doubleSided ? 2 : 1;

		for (int side = 0; side < renderCount; side++) {
			poseStack.pushPose();
			poseStack.translate(0, 0.5D, 0);
			poseStack.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
			poseStack.mulPose(Axis.ZP.rotationDegrees(180));
			if (side == 1) {
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
			}
			poseStack.mulPose(Axis.XP.rotationDegrees(xTilt));
			poseStack.translate(-xOffset, -yOffset, -zOffset - SMALL_OFFSET * 2);
			poseStack.scale(scale, scale, scale);
			font.drawInBatch(text, -textWidth / 2F, -3.5F, textColor, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.SEE_THROUGH, 0, LightTexture.FULL_BRIGHT);
			poseStack.popPose();
		}
	}

	private MutableComponent withConfiguredFont(MutableComponent text) {
		if ("johnston".equals(fontName)) {
			return org.mtr.client.IDrawing.withMTRFont(text);
		}
		return text.withStyle(style -> style.withFont(ResourceLocation.fromNamespaceAndPath(MainNeoForge.MOD_ID, fontName)));
	}
}
