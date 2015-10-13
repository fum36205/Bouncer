package de.ur.mi.bouncer.events;

import de.ur.mi.bouncer.Beeper;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.world.FieldColor;
import de.ur.mi.bouncer.world.Field;

public interface EventBus {

	void fieldWasPaintedWithColorByBouncer(Field field, FieldColor fieldColor);

	void fieldColorWasClearedByBouncer(Field field);

	void bouncerTurnedLeft();

	void bouncerTriedToMoveInObstacle(Field fromField, Direction inDirection);

	void bouncerWasPlacedAtField(Field field);

	void bouncerPickedUpBeeper(Beeper beeper);

	void bouncerPutBeeperAtField(Beeper beeper, Field field);

	void eventBusWasChanged();

	void addOnWordChangedListener(OnWorldChangedListener listener);

	void addBouncerEventsListener(BouncerEventsListener listener);

	void bouncerMoved(Field currentField, Field nextField);
}
