package de.ur.mi.bouncer.mock;

import de.ur.mi.bouncer.world.FieldColor;

public class BouncerMethodInvocationFactory {

	public MethodInvocation move() {
		return MethodInvocation.create("bouncer", "move");
	}

	public MethodInvocation turnLeft() {
		return MethodInvocation.create("bouncer", "turnLeft");
	}

	public MethodInvocation canMoveForward() {
		return MethodInvocation.create("bouncer", "canMoveForward");
	}

	public MethodInvocation canNotMoveForward() {
		return MethodInvocation.create("bouncer", "canNotMoveForward");
	}

	public MethodInvocation canMoveLeft() {
		return MethodInvocation.create("bouncer", "canMoveLeft");
	}

	public MethodInvocation canNotMoveLeft() {
		return MethodInvocation.create("bouncer", "canNotMoveLeft");
	}

	public MethodInvocation canMoveRight() {
		return MethodInvocation.create("bouncer", "canMoveRight");
	}

	public MethodInvocation canNotMoveRight() {
		return MethodInvocation.create("bouncer", "canNotMoveRight");
	}

	public MethodInvocation paintField(FieldColor color) {
		return MethodInvocation.create("bouncer", "paintField",
				MethodParameter.create("FieldColor", color));
	}

	public MethodInvocation isOnFieldWithColor(FieldColor color) {
		return MethodInvocation.create("bouncer", "isOnFieldWithColor",
				MethodParameter.create("FieldColor", color));
	}

	public MethodInvocation clearFieldColor() {
		return MethodInvocation.create("bouncer", "clearFieldColor");
	}

	public MethodInvocation isFacingWest() {
		return MethodInvocation.create("bouncer", "isFacingWest");
	}

	public MethodInvocation isFacingEast() {
		return MethodInvocation.create("bouncer", "isFacingEast");
	}

	public MethodInvocation isFacingNorth() {
		return MethodInvocation.create("bouncer", "isFacingNorth");
	}

	public MethodInvocation isFacingSouth() {
		return MethodInvocation.create("bouncer", "isFacingSouth");
	}
}
