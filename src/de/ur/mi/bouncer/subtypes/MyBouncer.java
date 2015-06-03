package de.ur.mi.bouncer.subtypes;
import de.ur.mi.bouncer.Bouncer;

public class MyBouncer extends Bouncer {

	public void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}

	public void turnAround() {
		turnLeft();
		turnLeft();
	}
	
	public void moveEast() {
		turnEast();
		move();
	}
	
	public void turnEast() {
		while (!isFacingEast()) {
			turnLeft();
		}
	}
	
	
}
