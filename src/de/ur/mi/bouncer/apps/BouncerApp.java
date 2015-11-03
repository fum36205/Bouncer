package de.ur.mi.bouncer.apps;
import de.ur.mi.bouncer.BasicBouncerCommands;
import de.ur.mi.bouncer.Bouncer;


@SuppressWarnings("serial")
public abstract class BouncerApp extends GenericBouncerApp<Bouncer> {

	@Override
	public Bouncer createBouncer() {
		return new Bouncer();
	}
	
	
}
