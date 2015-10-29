package de.ur.mi.bouncer;

import de.ur.mi.bouncer.world.FieldColor;

public interface BasicBouncerCommands {

	public abstract void move();

	public abstract void turnLeft();

	public abstract void paintField(FieldColor color);

	public abstract boolean isOnFieldWithColor(FieldColor color);

	public abstract void clearFieldColor();

	public abstract boolean canMoveForward();
	
	public abstract boolean canNotMoveForward();

	public abstract boolean canMoveLeft();
	
	public abstract boolean canNotMoveLeft();

	public abstract boolean canMoveRight();
	
	public abstract boolean canNotMoveRight();

	public abstract boolean isFacingWest();

	public abstract boolean isFacingEast();

	public abstract boolean isFacingNorth();

	public abstract boolean isFacingSouth();

}