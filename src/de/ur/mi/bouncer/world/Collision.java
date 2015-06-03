package de.ur.mi.bouncer.world;
import de.ur.mi.bouncer.Direction;


public class Collision {
	private int duration;
	public final Direction direction;
	
	public Collision(Direction inDirection) {
		this.duration = 1;
		this.direction = inDirection;
	}
	
	public void decrementDuration() {
		duration -= 1;
	}
	
	public boolean isAlive() {
		return duration > 0;
	}
}
