package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mtr.block.BlockPIDSHorizontalBase;
import org.mtr.block.IBlock;

public class NorthernPIDSBlock extends BlockPIDSHorizontalBase {

	private static final int MAX_ARRIVALS = 3;
	private final Supplier<BlockEntityType<NorthernPIDSBlockEntity>> blockEntityType;

	public NorthernPIDSBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<NorthernPIDSBlockEntity>> blockEntityType) {
		super(properties, MAX_ARRIVALS);
		this.blockEntityType = blockEntityType;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		final VoxelShape shape1 = IBlock.getVoxelShapeByDirection(6, 0, 0, 10, 9, 16, state.getValue(BlockStateProperties.HORIZONTAL_FACING));
		final VoxelShape shape2 = IBlock.getVoxelShapeByDirection(7.5, 9, 12.5, 8.5, 16, 13.5, state.getValue(BlockStateProperties.HORIZONTAL_FACING));
		return Shapes.or(shape1, shape2);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NorthernPIDSBlockEntity(blockEntityType.get(), pos, state);
	}

	public static class NorthernPIDSBlockEntity extends BlockEntityHorizontalBase {

		public NorthernPIDSBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
			super(MAX_ARRIVALS, type, pos, state);
		}

		@Override
		public boolean showArrivalNumber() {
			return true;
		}
	}
}
