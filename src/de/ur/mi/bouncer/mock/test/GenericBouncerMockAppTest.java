package de.ur.mi.bouncer.mock.test;

import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.Stack;

import org.junit.Rule;
import org.junit.rules.Timeout;

import de.ur.mi.bouncer.mock.BouncerExpectations;
import de.ur.mi.bouncer.mock.BouncerMockApp;
import de.ur.mi.bouncer.mock.MethodInvocationLog;

public abstract class GenericBouncerMockAppTest<T extends BouncerMockApp> {
	@Rule
	public Timeout globalTimeout = new Timeout(10000);
	private final MethodInvocationLog log = new MethodInvocationLog();
	private final T app = createApp(log);
	protected final BouncerExpectations bouncer = new BouncerExpectations();

	public abstract T createApp(MethodInvocationLog log);

	public void loadMap(String mapName) {
		bouncer.loadMap(mapName);
	}

	public void actualBounce() {
		app.bounce();
	}

	public void actualMethodNamed(String methodName) {
		try {
			Method turnRight = findMethod(methodName);
			Class<?> returnType = turnRight.getReturnType();
			if (!(returnType.equals(void.class))) {
				fail("Methodendeklaration nicht wie erwartet: private void "
						+ methodName + "()");
			}
			turnRight.setAccessible(true);
			turnRight.invoke(app);
		} catch (NoSuchMethodException e) {
			fail("Methode private void " + methodName
					+ "() wurde nicht deklariert.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Method findMethod(String name, Class<?>... parameters)
			throws NoSuchMethodException, SecurityException {
		return app.getClass().getDeclaredMethod(name, parameters);
	}

	public abstract void expectedBounce();

	public void assertExpectedMethodCalls() {
		bouncer.assertExpectedCalls(log);
	}

	public void setReturnValues(boolean... returnValues) {
		Stack<Boolean> valuesStack = new Stack<Boolean>();
		for (boolean v : returnValues) {
			valuesStack.push(v);
		}
		this.setReturnValues(valuesStack);
	}

	public void setReturnTrueCount(int count) {
		Stack<Boolean> valuesStack = new Stack<Boolean>();
		for (int i = 0; i < count; i++) {
			valuesStack.push(true);
		}
		this.setReturnValues(valuesStack);
	}

	@SuppressWarnings("unchecked")
	public void setReturnValues(Stack<Boolean> returnValues) {
		bouncer.setReturnValues((Stack<Boolean>) returnValues.clone());
		app.bouncer().setReturnValues((Stack<Boolean>) returnValues.clone());
	}
}
