import static org.junit.Assert.*;

import org.junit.Test;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;


public class WorldTests {

	@Test
	public void placesBouncerAtField() throws Exception {
		Bouncer b = new Bouncer();
		TwoDimensionalWorld world = TwoDimensionalWorld.emptyWorld();
		world.setBouncerStartPosition(0, 0);
		b.placeAt(world.bouncerStartField());
		assertTrue(world.hasBouncerAt(0, 0));
	}
}
