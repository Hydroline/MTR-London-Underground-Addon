package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mtr.block.IBlock;

public abstract class AbstractRoundelSignBlock extends Block implements EntityBlock {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty COLOR = IntegerProperty.create("color", 0, 2);

	private final Supplier<BlockEntityType<RoundelSignBlockEntity>> blockEntityType;

	protected AbstractRoundelSignBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<RoundelSignBlockEntity>> blockEntityType) {
		super(properties);
		this.blockEntityType = blockEntityType;
		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(COLOR, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, COLOR);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		return IBlock.checkHoldingBrush(level, player, () -> level.setBlock(pos, state.cycle(COLOR), Block.UPDATE_ALL));
	}

	@Override
	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		final Direction facing = state.getValue(FACING);
		return Shapes.or(
				IBlock.getVoxelShapeByDirection(-1.5, 8.66667, 7.575, 17.5, 27.5, 8.425, facing),
				IBlock.getVoxelShapeByDirection(-1.65, 0, 7.125, -0.85, 27.5, 8.875, facing),
				IBlock.getVoxelShapeByDirection(16.85, 0, 7.125, 17.65, 27.5, 8.875, facing)
		);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RoundelSignBlockEntity(blockEntityType.get(), pos, state);
	}

	public static class RoundelSignBlockEntity extends BlockEntity {

		public RoundelSignBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
			super(type, pos, state);
		}
	}
}
