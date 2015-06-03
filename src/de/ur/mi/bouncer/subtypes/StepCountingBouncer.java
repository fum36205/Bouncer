package de.ur.mi.bouncer.subtypes;
import de.ur.mi.bouncer.Bouncer;


public class StepCountingBouncer extends Bouncer {

	// potenzielle Erweiterung
	public void move(int steps) {
		for (int i = 0; i < steps; i++) {
			move();
		}
	}
}
