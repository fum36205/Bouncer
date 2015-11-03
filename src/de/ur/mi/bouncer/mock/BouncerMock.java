package de.ur.mi.bouncer.mock;

import java.util.Stack;

import de.ur.mi.bouncer.BasicBouncerCommands;
import de.ur.mi.bouncer.world.FieldColor;

public class BouncerMock implements BasicBouncerCommands {
	public final MethodInvocationLog invocationLog;
	public Stack<Boolean> returnValues = new Stack<Boolean>();

	public BouncerMock(MethodInvocationLog invocationLog) {
		this.invocationLog = invocationLog;
	}

	public void turnLeft() {
		invocationLog.addInvocation("bouncer", "turnLeft");
	}

	public void move() {
		invocationLog.addInvocation("bouncer", "move");
	}

	public void paintField(FieldColor color) {
		invocationLog.addInvocation("bouncer", "paintField",
				MethodParameter.create("FieldColor", color));
	}

	public void setReturnValues(Stack<Boolean> returnValues) {
		this.returnValues = returnValues;
	}

	public boolean isOnFieldWithColor(FieldColor color) {
		invocationLog.addInvocation("bouncer", "isOnFieldWithColor",
				MethodParameter.create("FieldColor", color));
		return nextReturnValue();
	}

	public boolean canMoveForward() {
		invocationLog.addInvocation("bouncer", "canMoveForward");
		return nextReturnValue();
	}

	public boolean canNotMoveForward() {
		invocationLog.addInvocation("bouncer", "canNotMoveForward");
		return nextReturnValue();
	}

	public boolean canMoveLeft() {
		invocationLog.addInvocation("bouncer", "canMoveLeft");
		return nextReturnValue();
	}

	public boolean canNotMoveLeft() {
		invocationLog.addInvocation("bouncer", "canNotMoveLeft");
		return nextReturnValue();
	}

	public boolean canMoveRight() {
		invocationLog.addInvocation("bouncer", "canMoveRight");
		return nextReturnValue();
	}

	public boolean canNotMoveRight() {
		invocationLog.addInvocation("bouncer", "canNotMoveRight");
		return nextReturnValue();
	}

	private Boolean nextReturnValue() {
		if (returnValues.isEmpty()) {
			return false;
		}
		return returnValues.pop();
	}

	@Override
	public void clearFieldColor() {
		invocationLog.addInvocation("bouncer", "clearFieldColor");
	}

	@Override
	public boolean isFacingWest() {
		invocationLog.addInvocation("bouncer", "isFacingWest");
		return nextReturnValue();
	}

	@Override
	public boolean isFacingEast() {
		invocationLog.addInvocation("bouncer", "isFacingEast");
		return nextReturnValue();
	}

	@Override
	public boolean isFacingNorth() {
		invocationLog.addInvocation("bouncer", "isFacingNorth");
		return nextReturnValue();
	}

	@Override
	public boolean isFacingSouth() {
		invocationLog.addInvocation("bouncer", "isFacingSouth");
		return nextReturnValue();
	}

}
