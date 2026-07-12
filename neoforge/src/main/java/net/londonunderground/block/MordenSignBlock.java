package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MordenSignBlock extends AbstractMordenSignBlock {

	public MordenSignBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<MordenSignBlockEntity>> blockEntityType) {
		super(properties, blockEntityType);
	}
}
