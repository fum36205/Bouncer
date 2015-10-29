package de.ur.mi.bouncer.mock;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Stack;

import org.hamcrest.TypeSafeMatcher;

import de.ur.mi.bouncer.BasicBouncerCommands;
import de.ur.mi.bouncer.mock.matcher.IsInvocationWithParameters;
import de.ur.mi.bouncer.world.FieldColor;

public class BouncerExpectations implements BasicBouncerCommands {
	private final BouncerMethodInvocationFactory bouncer = new BouncerMethodInvocationFactory();
	private final ArrayList<MethodInvocation> expectedInvocations = new ArrayList<MethodInvocation>();
	private Stack<Boolean> returnValues = new Stack<Boolean>();

	public BouncerExpectations() {
	}

	public void setReturnValues(Stack<Boolean> returnValues) {
		this.returnValues = returnValues;
	}

	public void loadMap(String mapName) {
		expectedInvocations.add(MethodInvocation.create("loadMap",
				MethodParameter.create("String", mapName)));
	}

	public void move() {
		expectedInvocations.add(bouncer.move());
	}

	public void turnLeft() {
		expectedInvocations.add(bouncer.turnLeft());
	}

	public void paintField(FieldColor color) {
		expectedInvocations.add(bouncer.paintField(color));
	}

	public boolean isOnFieldWithColor(FieldColor color) {
		expectedInvocations.add(bouncer.isOnFieldWithColor(color));
		return nextReturnValue();
	}

	public boolean canMoveForward() {
		expectedInvocations.add(bouncer.canMoveForward());
		return nextReturnValue();
	}

	public boolean canNotMoveLeft() {
		expectedInvocations.add(bouncer.canNotMoveLeft());
		return nextReturnValue();
	}

	@Override
	public void clearFieldColor() {
		expectedInvocations.add(bouncer.clearFieldColor());
	}

	@Override
	public boolean canMoveLeft() {
		expectedInvocations.add(bouncer.canMoveLeft());
		return nextReturnValue();
	}

	@Override
	public boolean canMoveRight() {
		expectedInvocations.add(bouncer.canMoveRight());
		return nextReturnValue();
	}

	@Override
	public boolean isFacingWest() {
		expectedInvocations.add(bouncer.isFacingWest());
		return nextReturnValue();
	}

	@Override
	public boolean isFacingEast() {
		expectedInvocations.add(bouncer.isFacingEast());
		return nextReturnValue();
	}

	@Override
	public boolean isFacingNorth() {
		expectedInvocations.add(bouncer.isFacingNorth());
		return nextReturnValue();
	}

	@Override
	public boolean isFacingSouth() {
		expectedInvocations.add(bouncer.isFacingSouth());
		return nextReturnValue();
	}

	@Override
	public boolean canNotMoveForward() {
		expectedInvocations.add(bouncer.canNotMoveForward());
		return nextReturnValue();
	}

	@Override
	public boolean canNotMoveRight() {
		expectedInvocations.add(bouncer.canNotMoveRight());
		return nextReturnValue();
	}

	private Boolean nextReturnValue() {
		if (returnValues.isEmpty()) {
			return false;
		}
		return returnValues.pop();
	}

	public void assertExpectedCalls(MethodInvocationLog log) {
		if (log.totalCount() > expectedInvocations.size()) {
			String errorMessage = "Zu viele Methodenaufrufe:\n";
			for (int i = expectedInvocations.size(); i < log.totalCount(); i++) {
				errorMessage += (i + 1) + ". Methodenaufruf: "
						+ log.invocationAt(i).toString() + "\n";
			}
			fail(errorMessage);
		}
		for (int i = 0; i < log.totalCount(); i++) {
			assertThat((i + 1) + ". Methodenaufruf", log.invocationAt(i),
					isInvocationWithParameters(expectedInvocations.get(i)));
		}
		if (expectedInvocations.size() > log.totalCount()) {
			String errorMessage = "Fehlende Methodenaufrufe:\n";
			for (int i = log.totalCount(); i < expectedInvocations.size(); i++) {
				errorMessage += (i + 1) + ". Methodenaufruf: "
						+ expectedInvocations.get(i).toString() + "\n";
			}
			fail(errorMessage);
		}
	}

	private TypeSafeMatcher<MethodInvocation> isInvocationWithParameters(
			MethodInvocation invocation) {
		return new IsInvocationWithParameters(invocation);
	}

}
