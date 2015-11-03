package de.ur.mi.bouncer.apps.tasks;

import de.ur.mi.bouncer.apps.WorldBouncerApp;

@SuppressWarnings("serial")
public class Diagonale extends WorldBouncerApp {

	@Override
	public void bounce() {
		bouncer.setStopsOnError(true);
		loadLocalMap("Empty_Size_12");
		bouncer.jumpToRowAndColumn(1, 0);
		// bouncer.jumpTo(0, 1);
		// bouncer.paintField(Color.ORANGE);
		bouncer.jumpToRowAndColumn(2, 0);
		// bouncer.jumpTo(0, 2);
		// bouncer.paintField(Color.ORANGE);
	}

}
