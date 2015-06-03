package de.ur.mi.bouncer.subtypes;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;

public class WorldBouncer extends Bouncer {
	private TwoDimensionalWorld world;
	
	public void jumpToFieldAt(int x, int y) {
		placeAt(world.fieldAt(x, y));
	}
	
	public void jumpToRowAndColumn(int rowIdx, int colIdx) {
		placeAt(world.fieldAt(colIdx, rowIdx));
	}
	
	@Override
	public void placeInWorld(TwoDimensionalWorld world) {
		super.placeInWorld(world);
		this.world = world;
	}
	
	public int sizeOfWorld() {
		return world.size();
	}
}
