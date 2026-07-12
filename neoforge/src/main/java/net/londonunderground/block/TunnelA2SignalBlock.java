package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mtr.block.BlockSignalBase;
import org.mtr.block.BlockSignalLightBase;
import org.mtr.block.IBlock;

public class TunnelA2SignalBlock extends BlockSignalLightBase {

	private final Supplier<BlockEntityType<TunnelA2SignalBlockEntity>> blockEntityType;

	public TunnelA2SignalBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<TunnelA2SignalBlockEntity>> blockEntityType) {
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
		return IBlock.getVoxelShapeByDirection(0, 0, 0, 12, 16, 16, state.getValue(WallRoundelBlock.FACING));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TunnelA2SignalBlockEntity(blockEntityType.get(), pos, state);
	}

	public static class TunnelA2SignalBlockEntity extends BlockSignalBase.BlockEntityBase {

		public TunnelA2SignalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
			super(type, false, pos, state);
		}
	}
}
