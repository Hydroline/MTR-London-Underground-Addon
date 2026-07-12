package net.londonunderground.render;

import org.mtr.block.BlockPIDSBase;
import org.mtr.render.RenderPIDS;

public class RenderNorthernLinePIDS extends RenderPIDS<BlockPIDSBase.BlockEntityBase> {

	public RenderNorthernLinePIDS() {
		super(1.5F, 7.5F, 6F, 6.5F, 29, true, 1F);
	}

	@Override
	public String getArrivalString(long arrival, boolean isRealtime, boolean isCjk) {
		return arrival < 60 && !isCjk ? "Due" : super.getArrivalString(arrival, isRealtime, isCjk);
	}
}
