package de.ur.mi.bouncer.subtypes;
public class CompassBouncer extends StepCountingBouncer {
	// potenzielle Erweiterung
	public void moveEast() {
		turnEast();
		move();
	}
	
	public void moveWest() {
		turnWest();
		move();
	}
	
	public void moveSouth() {
		turnSouth();
		move();
	}
	
	public void moveNorth() {
		turnNorth();
		move();
	}

	public void turnEast() {
		while (!isFacingEast()) {
			turnLeft();
		}
	}

	public void turnWest() {
		while (!isFacingWest()) {
			turnLeft();
		}
	}

	public void turnNorth() {
		while (!isFacingNorth()) {
			turnLeft();
		}
	}
	
	public void turnSouth() {
		while (!isFacingSouth()) {
			turnLeft();
		}
	}
}
