package de.ur.mi.bouncer.world;
import de.ur.mi.bouncer.Beeper;
import de.ur.mi.bouncer.BeeperBag;
import de.ur.mi.bouncer.Direction;

public class Field {
	private final Surroundings surroundings;
	private FieldContent content;
	private BeeperBag beeperBag;
	private FieldColor fieldColor;
	private Collision collision;
	
	public Field(FieldContent content, FieldColor fieldColor, BeeperBag beeperBag,
			Surroundings surroundings) {
		this.content = content;
		this.fieldColor = fieldColor;
		this.beeperBag = beeperBag;
		this.surroundings = surroundings;
	}
	
	public void setNeighbourInDirection(Field field, Direction direction) {
		surroundings.setNeighbourInDirection(field, direction);
	}

	public boolean isNeighbourInDirectionClear(Direction direction) {
		Field fieldInDirection = surroundings.neighbourInDirection(direction);
		if (fieldInDirection == null) {
			return false;
		}
		return fieldInDirection.isClear();
	}

	public Field tryToLeaveInDirection(Direction direction) {
		if (!canBeLeftInDirection(direction)) {
			this.collision = new Collision(direction);
			return this;
		}
		Field nextField = surroundings.neighbourInDirection(direction);
		return nextField;
	}

	private boolean canBeLeftInDirection(Direction direction) {
		Field neighbour = surroundings.neighbourInDirection(direction);
		return (neighbour != null && neighbour.isClear());
	}
	
	public void leftByBouncer() {
		content = FieldContent.FREE;
	}

	public void enteredByBouncer() {
		content = FieldContent.BOUNCER;
	}

	public void putBeeper(Beeper beeper) {
		beeperBag.addBeeper(beeper);
	}

	public boolean hasBeeper() {
		return !beeperBag.isEmpty();
	}

	public Beeper pickUpBeeper() {
		if (beeperBag.isEmpty()) {
			return null;
		}
		return beeperBag.retrieveBeeper();
	}

	public void paintWith(FieldColor fieldColor) {
		this.fieldColor = fieldColor;
	}

	public boolean isPaintedWith(FieldColor fieldColor) {
		return this.fieldColor.equals(fieldColor);
	}

	public boolean isClear() {
		return content != FieldContent.OBSTACLE;
	}

	public boolean isBlocked() {
		return !isClear();
	}

	public void clearColor() {
		this.fieldColor = FieldColor.WHITE;
	}
	
	public boolean hasCollision() {
		return this.collision != null;
	}
	
	public Collision collision() {
		return this.collision;
	}
	
	public void purgeCollision() {
		this.collision = null;
	}
	
	void setContent(FieldContent content) {
		this.content = content;
	}

	public FieldColor color() {
		return this.fieldColor;
	}

	public boolean hasBouncer() {
		return content == FieldContent.BOUNCER;
	}
	
	public boolean isNeighbourTo(Field field) {
		return surroundings.haveField(field);
	}

}
