package de.ur.mi.bouncer.world;

import de.ur.mi.bouncer.Beeper;
import de.ur.mi.bouncer.Direction;

public class NullField extends Field {

	public NullField() {
		super(null, null, null, null);
	}

	@Override
	public boolean isNeighbourInDirectionClear(Direction direction) {
		return false;
	}

	@Override
	public boolean isClear() {
		return true;
	}

	@Override
	public void leftByBouncer() {
	}

	@Override
	public void enteredByBouncer() {
	}

	@Override
	public void putBeeper(Beeper beeper) {
	}

	@Override
	public boolean hasBeeper() {
		return false;
	}

	@Override
	public Beeper pickUpBeeper() {
		return null;
	}

	@Override
	public void paintWith(FieldColor fieldColor) {
	}

	@Override
	public boolean isPaintedWith(FieldColor fieldColor) {
		return false;
	}

	@Override
	public void clearColor() {
	}
}
