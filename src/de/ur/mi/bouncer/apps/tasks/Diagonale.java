package de.ur.mi.bouncer.apps.tasks;

import de.ur.mi.bouncer.apps.WorldBouncerApp;
import de.ur.mi.bouncer.world.FieldColor;

@SuppressWarnings("serial")
public class Diagonale extends WorldBouncerApp {

	@Override
	public void bounce() {
		bouncer.setStopsOnError(true);
		loadLocalMap("Empty_Size_12");
		bouncer.jumpTo(1, 0);
		bouncer.paintField(FieldColor.PINK);
//		bouncer.jumpTo(0, 1);
//		bouncer.paintField(Color.ORANGE);
		bouncer.jumpTo(2, 0);
		bouncer.paintField(FieldColor.PINK);
//		bouncer.jumpTo(0, 2);
//		bouncer.paintField(Color.ORANGE);
	}

}
