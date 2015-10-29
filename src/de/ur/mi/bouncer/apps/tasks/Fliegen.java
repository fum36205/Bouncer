package de.ur.mi.bouncer.apps.tasks;

import de.ur.mi.bouncer.apps.WorldBouncerApp;
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
	}

}
