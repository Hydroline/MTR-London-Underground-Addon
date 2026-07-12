package net.londonunderground.block;

import java.util.function.Supplier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MordenSignDlrBlock extends AbstractMordenSignBlock {

	public MordenSignDlrBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<MordenSignBlockEntity>> blockEntityType) {
		super(properties, blockEntityType);
	}
}
