package de.ur.mi.bouncer.apps.tasks;
import de.ur.mi.bouncer.apps.MyBouncerApp;
import de.ur.mi.bouncer.subtypes.MyBouncer;

@SuppressWarnings("serial")
public class Hindernis extends MyBouncerApp {
	@Override
	public MyBouncer createBouncer() {
		return new MyBouncer();
	}

	@Override
	public void bounce() {
		loadLocalMap("Obstacles");
		bouncer.setStopsOnError(true);
		bouncer.moveEast();
		bouncer.moveEast();
		bouncer.turnLeft();
		
	}

}
