package de.ur.mi.bouncer.events;

import auctionsniper.util.Announcer;
import de.ur.mi.bouncer.Beeper;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.world.Color;
import de.ur.mi.bouncer.world.Field;

public class DefaultEventBus implements EventBus {
	private Announcer<OnWorldChangedListener> onWorldChangedListeners;
	private Announcer<BouncerEventsListener> bouncerEventsListeners;
	
	public DefaultEventBus() {
		onWorldChangedListeners = Announcer.to(OnWorldChangedListener.class);
		bouncerEventsListeners = Announcer.to(BouncerEventsListener.class);
	}
	
	@Override
	public void addOnWordChangedListener(OnWorldChangedListener listener) {
		onWorldChangedListeners.addListener(listener);
	}

	@Override
	public void fieldWasPaintedWithColorByBouncer(Field field, Color color) {
		onWorldChangedListeners.announce().onWorldChanged();
		bouncerEventsListeners.announce().onBouncerPaintedField(field, color);
	}

	@Override
	public void fieldColorWasClearedByBouncer(Field field) {
		onWorldChangedListeners.announce().onWorldChanged();
	}

	@Override
	public void bouncerTurnedLeft() {
		onWorldChangedListeners.announce().onWorldChanged();
		bouncerEventsListeners.announce().onBouncerTurnedLeft();
	}

	@Override
	public void bouncerTriedToMoveInObstacle(Field fromField, Direction inDirection) {
		onWorldChangedListeners.announce().onWorldChanged();
		bouncerEventsListeners.announce().onBouncerTriedToMoveInObstacle(fromField, inDirection);
	}

	@Override
	public void bouncerWasPlacedAtField(Field field) {
		onWorldChangedListeners.announce().onWorldChanged();
		bouncerEventsListeners.announce().onBouncerWasPlacedAtField(field);
	}

	@Override
	public void bouncerPickedUpBeeper(Beeper beeper) {
		onWorldChangedListeners.announce().onWorldChanged();
	}

	@Override
	public void bouncerPutBeeperAtField(Beeper beeper, Field field) {
		onWorldChangedListeners.announce().onWorldChanged();
	}

	@Override
	public void eventBusWasChanged() {

	}

	@Override
	public void addBouncerEventsListener(BouncerEventsListener listener) {
		bouncerEventsListeners.addListener(listener);
	}

	@Override
	public void bouncerMoved(Field from, Field to) {
		onWorldChangedListeners.announce().onWorldChanged();
		bouncerEventsListeners.announce().onBouncerMoved(from, to);
	}

	
}
