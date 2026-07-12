package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SignUndergroundBlock extends AbstractRoundelSignBlock {

	public SignUndergroundBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<RoundelSignBlockEntity>> blockEntityType) {
		super(properties, blockEntityType);
	}
}
