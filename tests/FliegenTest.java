import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.apps.WorldBouncerApp;
import de.ur.mi.bouncer.apps.tasks.Fliegen;
import de.ur.mi.bouncer.events.BouncerEventsListener;
import de.ur.mi.bouncer.events.DefaultEventBus;
import de.ur.mi.bouncer.stacktrace.StackTraceFilter;
import de.ur.mi.bouncer.world.FieldColor;
import de.ur.mi.bouncer.world.Field;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;
import de.ur.mi.bouncer.world.loader.WorldLoader;

public class FliegenTest implements BouncerEventsListener {
	private WorldBouncerApp app;
	private boolean bouncerDidMoveAtLeastOnce;

	@Before
	public void setupApp() {
		WorldLoader wl = new WorldLoader() {
			@Override
			public TwoDimensionalWorld loadLocalMap(String mapName) {
				return TwoDimensionalWorld.emptyWorld();
			}
		};
		app = new Fliegen();
		app.setWorldLoader(wl);
	}

	@Test
	public void test() {
		DefaultEventBus eventBus = new DefaultEventBus(
				StackTraceFilter.forClasses(Fliegen.class));
		eventBus.addBouncerEventsListener(this);
		app.setupBouncer(eventBus);
		app.bounce();
		assertTrue(bouncerDidMoveAtLeastOnce);
	}

	@Override
	public void onBouncerMoved(Field startField, Field targetField) {
		if (targetField.isNeighbourTo(startField)) {
			bouncerDidMoveAtLeastOnce = true;
		}
	}

	@Override
	public void onBouncerTurnedLeft() {
	}

	@Override
	public void onBouncerPaintedField(Field field, FieldColor color) {
	}

	@Override
	public void onBouncerTriedToMoveInObstacle(Field fromField,
			Direction inDirection) {
	}

	@Override
	public void onBouncerWasPlacedAtField(Field toField) {
	}
}
