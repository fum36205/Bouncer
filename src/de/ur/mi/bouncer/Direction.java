package de.ur.mi.bouncer;
public enum Direction {

	NORTH, EAST, SOUTH, WEST;

	public Direction afterLeftTurn() {
		return values()[(this.ordinal() - 1 + values().length)
				% values().length];
	}

	public Direction afterRightTurn() {
		return values()[(this.ordinal() + 1 + values().length)
				% values().length];
	}
}
