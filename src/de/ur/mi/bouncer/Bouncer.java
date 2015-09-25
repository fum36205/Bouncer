package de.ur.mi.bouncer;

import java.util.List;

import de.ur.mi.bouncer.error.BouncerError;
import de.ur.mi.bouncer.events.EventBus;
import de.ur.mi.bouncer.events.NullEventBus;
import de.ur.mi.bouncer.world.Color;
import de.ur.mi.bouncer.world.Field;
import de.ur.mi.bouncer.world.NullField;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;

public class Bouncer {
	private EventBus eventBus;
	private final BeeperBag inventory;
	private Field currentField;
	private Direction currentOrientation;
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

	public void setStopsOnError(boolean throwsError) {
		this.shouldThrowError = throwsError;
	}

	public final void fillInventory(List<Beeper> beepers) {
		inventory.fill(beepers);
	}


	public void placeInWorld(TwoDimensionalWorld world) {
		this.placeAt(world.bouncerStartField());
	}

	public final void placeAt(Field field) {
		if (currentField == field) {
			throwErrorIfWanted("Bouncer steht schon auf diesem Feld");
			return;
		}
		move(currentField, field);
		eventBus.bouncerWasPlacedAtField(currentField);
	}

	public final void move() {
		Field nextField = currentField
				.tryToLeaveInDirection(currentOrientation);
		if (nextField == currentField) {
			throwErrorIfWanted("Bouncer konnte diesen Schritt nicht gehen.");
			eventBus.bouncerTriedToMoveInObstacle(currentField, currentOrientation);
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

	public final void turnLeft() {
		currentOrientation = currentOrientation.afterLeftTurn();
		eventBus.bouncerTurnedLeft();
	}

	public final void paintField(Color color) {
		currentField.paintWith(color);
		eventBus.fieldWasPaintedWithColorByBouncer(currentField, color);
	}

	public final boolean isOnFieldWithColor(Color color) {
		return currentField.isPaintedWith(color);
	}

	public final void clearFieldColor() {
		currentField.clearColor();
		eventBus.fieldColorWasClearedByBouncer(currentField);
	}

	public final boolean canMoveForward() {
		return currentField.isNeighbourInDirectionClear(currentOrientation);
	}

	public final boolean canMoveLeft() {
		return currentField.isNeighbourInDirectionClear(currentOrientation
				.afterLeftTurn());
	}

	public final boolean canMoveRight() {
		return currentField.isNeighbourInDirectionClear(currentOrientation
				.afterRightTurn());
	}

	public final boolean canNotMoveForward() {
		return !canMoveForward();
	}

	public final boolean canNotMoveLeft() {
		return !canMoveLeft();
	}

	public final boolean canNotMoveRight() {
		return !canMoveRight();
	}

	public final boolean isFacingWest() {
		return currentOrientation == Direction.WEST;
	}

	public final boolean isFacingEast() {
		return currentOrientation == Direction.EAST;
	}

	public final boolean isFacingNorth() {
		return currentOrientation == Direction.NORTH;
	}

	public final boolean isFacingSouth() {
		return currentOrientation == Direction.SOUTH;
	}

	public Direction currentOrientation() {
		return this.currentOrientation;
	}

	private void throwErrorIfWanted(String errorMessage) {
		if (shouldThrowError) {
			throw new BouncerError(errorMessage);
		}
	}
}
