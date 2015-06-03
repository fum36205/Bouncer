import static org.junit.Assert.*;

import org.junit.Test;

import de.ur.mi.bouncer.apps.GenericBouncerApp;
import de.ur.mi.bouncer.apps.tasks.Fliegen;


public class StackTraceTest {

	@Test
	public void test() {
		assertTrue(GenericBouncerApp.class.isAssignableFrom(Fliegen.class));
	}

}
