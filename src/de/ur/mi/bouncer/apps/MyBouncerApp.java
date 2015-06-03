package de.ur.mi.bouncer.apps;
import de.ur.mi.bouncer.subtypes.MyBouncer;

@SuppressWarnings("serial")
public abstract class MyBouncerApp extends GenericBouncerApp<MyBouncer> {

	public abstract MyBouncer createBouncer();
	
}
