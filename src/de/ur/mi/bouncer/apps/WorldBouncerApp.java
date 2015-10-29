package de.ur.mi.bouncer.apps;

import de.ur.mi.bouncer.subtypes.WorldBouncer;

public abstract class WorldBouncerApp extends GenericBouncerApp<WorldBouncer> {

	private static final long serialVersionUID = 5088290980887383122L;

	@Override
	public WorldBouncer createBouncer() {
		return new WorldBouncer();
	}


}
