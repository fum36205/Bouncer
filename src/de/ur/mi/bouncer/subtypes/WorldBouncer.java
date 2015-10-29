package de.ur.mi.bouncer.subtypes;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.error.FieldIndexOutOfBoundsException;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;

public class WorldBouncer extends Bouncer {
	private TwoDimensionalWorld world;

	// public void jumpToFieldAt(int x, int y) {
	// try {
	// placeAt(world.fieldAtXandY(x, y));
	// } catch (FieldIndexOutOfBoundsException e) {
	// throwErrorIfWanted(e.getMessage());
	// }
	// }

	public void jumpTo(int rowIdx, int colIdx) {
		try {
			placeAt(world.fieldAtRowAndColumn(rowIdx, colIdx));
		} catch (FieldIndexOutOfBoundsException e) {
			throwErrorIfWanted(e.getMessage());
		}
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
