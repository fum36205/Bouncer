package de.ur.mi.bouncer.events;

import auctionsniper.util.Announcer;
import de.ur.mi.bouncer.Beeper;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.world.FieldColor;
import de.ur.mi.bouncer.stacktrace.StackTraceFilter;
import de.ur.mi.bouncer.world.Field;

public class DefaultEventBus implements EventBus {
	private final StackTraceFilter stackTraceFilter;
	private Announcer<OnWorldChangedListener> onWorldChangedListeners;
	private Announcer<BouncerEventsListener> bouncerEventsListeners;

	public DefaultEventBus(StackTraceFilter stackTraceFilter) {
		this.stackTraceFilter = stackTraceFilter;
		onWorldChangedListeners = Announcer.to(OnWorldChangedListener.class);
		bouncerEventsListeners = Announcer.to(BouncerEventsListener.class);
	}

	@Override
	public void addOnWordChangedListener(OnWorldChangedListener listener) {
		onWorldChangedListeners.addListener(listener);
	}

	@Override
	public void fieldWasPaintedWithColorByBouncer(Field field, FieldColor fieldColor) {
		onWorldChangedListeners.announce().onWorldStateChanged();
		bouncerEventsListeners.announce().onBouncerPaintedField(field, fieldColor);
	}

	@Override
	public void fieldColorWasClearedByBouncer(Field field) {
		onWorldChangedListeners.announce().onWorldStateChanged();
	}

	@Override
	public void bouncerTurnedLeft() {
		onWorldChangedListeners.announce().onWorldStateChanged();
		bouncerEventsListeners.announce().onBouncerTurnedLeft();
		printStudentMethodCall();
	}

	@Override
	public void bouncerTriedToMoveInObstacle(Field fromField,
			Direction inDirection) {
		onWorldChangedListeners.announce().onWorldStateChanged();
		bouncerEventsListeners.announce().onBouncerTriedToMoveInObstacle(
				fromField, inDirection);
	}

	@Override
	public void bouncerWasPlacedAtField(Field field) {
		onWorldChangedListeners.announce().onWorldStateChanged();
		bouncerEventsListeners.announce().onBouncerWasPlacedAtField(field);
	}

	@Override
	public void bouncerPickedUpBeeper(Beeper beeper) {
		onWorldChangedListeners.announce().onWorldStateChanged();
	}

	@Override
	public void bouncerPutBeeperAtField(Beeper beeper, Field field) {
		onWorldChangedListeners.announce().onWorldStateChanged();
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
		onWorldChangedListeners.announce().onWorldStateChanged();
		bouncerEventsListeners.announce().onBouncerMoved(from, to);
		printStudentMethodCall();
	}

	private void printStudentMethodCall() {
		StackTraceElement[] studentStackTrace = studentMethodCall();
		System.out.println(studentStackTrace[0]);
		for (int i = 1; i < studentStackTrace.length; i++) {
			System.out.println("\tat " + studentStackTrace[i]);
		}
	}

	private StackTraceElement[] studentMethodCall() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		return stackTraceFilter.select(elements);
	}
}
