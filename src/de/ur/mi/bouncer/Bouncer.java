package de.ur.mi.bouncer;

import java.util.List;

import de.ur.mi.bouncer.error.BouncerError;
import de.ur.mi.bouncer.events.EventBus;
import de.ur.mi.bouncer.events.NullEventBus;
import de.ur.mi.bouncer.stacktrace.StackTraceFilter;
import de.ur.mi.bouncer.world.Field;
import de.ur.mi.bouncer.world.FieldColor;
import de.ur.mi.bouncer.world.NullField;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;

public class Bouncer implements BasicBouncerCommands {
	private EventBus eventBus;
	private final BeeperBag inventory;
	private Field currentField;
	private Direction currentOrientation;
	private StackTraceFilter stackTraceFilter;
	private boolean shouldThrowError = false;

	public Bouncer() {
		eventBus = new NullEventBus();
		currentField = new NullField();
		inventory = new BeeperBag();
		currentOrientation = Direction.EAST;
	}

	public final void setEventBus(EventBus eventBus) {
		this.eventBus.eventBusWasChanged();
		this.eventBus = eventBus;
	}

	public final void setStackTraceFilter(StackTraceFilter stackTraceFilter) {
		this.stackTraceFilter = stackTraceFilter;
	}

	public void setStopsOnError(boolean stopsOnError) {
		this.shouldThrowError = stopsOnError;
	}

	public final void fillInventory(List<Beeper> beepers) {
		inventory.fill(beepers);
	}

	public void placeInWorld(TwoDimensionalWorld world) {
		this.placeAt(world.bouncerStartField());
	}

	public final void placeAt(Field field) {
		move(currentField, field);
		eventBus.bouncerWasPlacedAtField(currentField);
	}

	@Override
	public final void move() {
		Field nextField = currentField
				.tryToLeaveInDirection(currentOrientation);
		if (nextField == currentField) {
			throwErrorIfWanted("Bouncer konnte diesen Schritt nicht gehen.");
			eventBus.bouncerTriedToMoveInObstacle(currentField,
					currentOrientation);
			return;
		}
		move(currentField, nextField);
	}

	private void move(Field from, Field to) {
		from.leftByBouncer();
		currentField = to;
		to.enteredByBouncer();
		eventBus.bouncerMoved(from, to);
	}

	private final boolean canPickUpBeeper() {
		return currentField.hasBeeper();
	}

	public final void putBeeper() {
		if (inventory.isEmpty()) {
			throwErrorIfWanted("Bouncer hat versucht einen Beeper zu platzieren, obwohl er selbst keinen hat.");
			return;
		}
		Beeper beeper = inventory.retrieveBeeper();
		currentField.putBeeper(beeper);
		eventBus.bouncerPutBeeperAtField(beeper, currentField);
	}

	public final void pickBeeper() {
		if (!canPickUpBeeper()) {
			throwErrorIfWanted("Bouncer hat versucht einen Beeper aufzuheben, wo keiner war.");
			return;
		}
		Beeper beeper = currentField.pickUpBeeper();
		eventBus.bouncerPickedUpBeeper(beeper);
		inventory.addBeeper(beeper);
	}

	@Override
	public final void turnLeft() {
		currentOrientation = currentOrientation.afterLeftTurn();
		eventBus.bouncerTurnedLeft();
	}

	@Override
	public final void paintField(FieldColor fieldColor) {
		currentField.paintWith(fieldColor);
		eventBus.fieldWasPaintedWithColorByBouncer(currentField, fieldColor);
	}

	@Override
	public final boolean isOnFieldWithColor(FieldColor fieldColor) {
		return currentField.isPaintedWith(fieldColor);
	}

	@Override
	public final void clearFieldColor() {
		currentField.clearColor();
		eventBus.fieldColorWasClearedByBouncer(currentField);
	}

	@Override
	public final boolean canMoveForward() {
		return currentField.isNeighbourInDirectionClear(currentOrientation);
	}

	@Override
	public final boolean canMoveLeft() {
		return currentField.isNeighbourInDirectionClear(currentOrientation
				.afterLeftTurn());
	}

	@Override
	public final boolean canMoveRight() {
		return currentField.isNeighbourInDirectionClear(currentOrientation
				.afterRightTurn());
	}

	@Override
	public final boolean canNotMoveForward() {
		return !canMoveForward();
	}

	@Override
	public final boolean canNotMoveLeft() {
		return !canMoveLeft();
	}

	@Override
	public final boolean canNotMoveRight() {
		return !canMoveRight();
	}

	@Override
	public final boolean isFacingWest() {
		return currentOrientation == Direction.WEST;
	}

	@Override
	public final boolean isFacingEast() {
		return currentOrientation == Direction.EAST;
	}

	@Override
	public final boolean isFacingNorth() {
		return currentOrientation == Direction.NORTH;
	}

	@Override
	public final boolean isFacingSouth() {
		return currentOrientation == Direction.SOUTH;
	}

	public Direction currentOrientation() {
		return this.currentOrientation;
	}

	protected void throwErrorIfWanted(String errorMessage) {
		if (shouldThrowError) {
			throw new BouncerError(errorMessage, stackTraceFilter);
		}
	}

}
