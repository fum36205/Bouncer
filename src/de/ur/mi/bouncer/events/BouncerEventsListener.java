package de.ur.mi.bouncer.events;

import java.util.EventListener;

import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.world.FieldColor;
import de.ur.mi.bouncer.world.Field;

public interface BouncerEventsListener extends EventListener {

	void onBouncerMoved(Field startField, Field targetField);
	void onBouncerTurnedLeft();
	void onBouncerPaintedField(Field field, FieldColor color);
	void onBouncerTriedToMoveInObstacle(Field fromField, Direction inDirection);
	void onBouncerWasPlacedAtField(Field toField);
}
