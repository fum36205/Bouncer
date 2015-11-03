package de.ur.mi.bouncer.apps.tasks;

import de.ur.mi.bouncer.apps.WorldBouncerApp;
import de.ur.mi.bouncer.subtypes.WorldBouncer;
import de.ur.mi.bouncer.world.FieldColor;

@SuppressWarnings("serial")
public class Fliegen extends WorldBouncerApp {

	@Override
	public void bounce() {
		loadLocalMap("RedLine");
		while (bouncer.isOnFieldWithColor(FieldColor.RED))
			bouncer.move();
		bouncer.paintField(FieldColor.GREEN);
		bouncer.turnLeft();
		bouncer.move();
		bouncer.setStopsOnError(true);
		for (int rowIdx = 0; rowIdx < bouncer.sizeOfWorld(); rowIdx++) {
			for (int colIdx = 0; colIdx < bouncer.sizeOfWorld(); colIdx++) {
				bouncer.jumpToRowAndColumn(rowIdx, colIdx);
				if (colIdx % 2 == 0) {
					if (rowIdx % 2 == 0) {
						bouncer.paintField(FieldColor.RED);
					} else {
						bouncer.paintField(FieldColor.BLUE);
					}
				} else {
					if (rowIdx % 2 == 0) {
						bouncer.paintField(FieldColor.GREEN);
					} else {
						bouncer.paintField(FieldColor.RED);
					}
				}
			}
		}
	}

}
