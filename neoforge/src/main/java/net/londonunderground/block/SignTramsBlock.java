package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SignTramsBlock extends AbstractRoundelSignBlock {

	public SignTramsBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<RoundelSignBlockEntity>> blockEntityType) {
		super(properties, blockEntityType);
	}
}
