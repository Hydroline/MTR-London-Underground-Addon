package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.Component;
import org.mtr.block.BlockSignalBase;
import org.mtr.block.BlockSignalLightBase;
import org.mtr.block.IBlock;
import java.util.List;

public class TunnelSignalBlock extends BlockSignalLightBase {

	private final Supplier<BlockEntityType<TunnelSignalBlockEntity>> blockEntityType;

	public TunnelSignalBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<TunnelSignalBlockEntity>> blockEntityType) {
		super(properties, 2, 14);
		this.blockEntityType = blockEntityType;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState()
				.setValue(WallRoundelBlock.FACING, context.getHorizontalDirection().getClockWise())
				.setValue(BlockSignalBase.IS_45, BlockSignalBase.EnumBooleanInverted.FALSE)
				.setValue(BlockSignalBase.IS_22_5, BlockSignalBase.EnumBooleanInverted.FALSE);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 16, 16, state.getValue(WallRoundelBlock.FACING));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TunnelSignalBlockEntity(blockEntityType.get(), pos, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, net.minecraft.world.item.Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.literal("DEPRECATED!!!").withColor(0xFF5555));
	}

	public static class TunnelSignalBlockEntity extends BlockSignalBase.BlockEntityBase {

		public TunnelSignalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
			super(type, false, pos, state);
		}
	}
}
