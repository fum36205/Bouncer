package de.ur.mi.bouncer.events;
import de.ur.mi.bouncer.Beeper;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.world.Color;
import de.ur.mi.bouncer.world.Field;

public class NullEventBus implements EventBus {

	public void bouncerTurnedLeft() {
	}

	public void fieldColorWasClearedByBouncer(Field field) {
	}

	public void fieldWasEnteredByBouncer(Field field) {
	}

	public void fieldWasLeftByBouncer(Field field) {
	}

	public void fieldWasPaintedWithColorByBouncer(Field field, Color color) {
	}

	public void bouncerTriedToMoveInObstacle(Field field, Direction direction) {
	}

	@Override
	public void bouncerWasPlacedAtField(Field field) {
	}

	@Override
	public void eventBusWasChanged() {
	}

	@Override
	public void bouncerPickedUpBeeper(Beeper beeper) {
	}

	@Override
	public void bouncerPutBeeperAtField(Beeper beeper, Field field) {
	}

	@Override
	public void addOnWordChangedListener(OnWorldChangedListener listener) {
	}

	@Override
	public void addBouncerEventsListener(BouncerEventsListener listener) {
		
	}

	@Override
	public void bouncerMoved(Field currentField, Field nextField) {
		// TODO Auto-generated method stub
		
	}

}
