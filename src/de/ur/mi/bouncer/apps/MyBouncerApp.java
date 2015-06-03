package de.ur.mi.bouncer.apps;

import de.ur.mi.bouncer.stacktrace.StackTraceFilter;
import de.ur.mi.bouncer.subtypes.MyBouncer;

@SuppressWarnings("serial")
public abstract class MyBouncerApp extends GenericBouncerApp<MyBouncer> {

	@Override
	protected StackTraceFilter newStackTraceFilter() {
		return StackTraceFilter.forClasses(this.getClass(),
				MyBouncer.class);
	}

	public abstract MyBouncer createBouncer();

}
