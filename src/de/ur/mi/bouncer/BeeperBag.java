package de.ur.mi.bouncer;
import java.util.List;
import java.util.Stack;

public class BeeperBag {

	private Stack<Beeper> beepers;

	public BeeperBag() {
		beepers = new Stack<Beeper>();
	}
	
	public Beeper retrieveBeeper() {
		return beepers.pop();
	}
	
	public void fill(List<Beeper> beepers) {
		for (Beeper b: beepers) {
			addBeeper(b);
		}
	}
	
	public void addBeeper(Beeper beeper) {
		if (beeper == null) {
			return;
		}
		beepers.push(beeper);
	}
	
	public boolean hasBeepers() {
		return !beepers.isEmpty();
	}

	public boolean isEmpty() {
		return beepers.isEmpty();
	}
}
