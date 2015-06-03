package de.ur.mi.bouncer.world;
import de.ur.mi.ProgrammingError;
import de.ur.mi.bouncer.BeeperBag;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.events.EventBus;
import de.ur.mi.bouncer.events.NullEventBus;


public class TwoDimensionalWorld {
	private static final int GRID_SIZE = 15;
	private final Field[][] fields;
	private Field bouncerStartField;

	public static TwoDimensionalWorld emptyWorld() {
		return new TwoDimensionalWorld(new NullEventBus());
	}

	public void placeObstacleAt(int x, int y) {
		Field field = fieldAt(x, y);
		field.setContent(FieldContent.OBSTACLE);
	}

	public void paintFieldAt(int x, int y, Color color) {
		Field field = fieldAt(x, y);
		field.paintWith(color);
	}

	public void placeBouncerAt(int x, int y) {
		bouncerStartField = fieldAt(x, y);
		bouncerStartField.setContent(FieldContent.BOUNCER);
	}

	public boolean hasCollisionAt(int x, int y) {
		return fieldAt(x, y).hasCollision();
	}

	public Field bouncerStartField() {
		return bouncerStartField;
	}

	public int size() {
		return fields.length;
	}

	public boolean hasObstacleAt(int x, int y) {
		return fieldAt(x, y).isBlocked();
	}

	public boolean hasBouncerAt(int x, int y) {
		return fieldAt(x, y).hasBouncer();
	}

	public Color colorAt(int x, int y) {
		return fieldAt(x, y).color();
	}
	
	public Collision collisionAt(int x, int y) {
		return fieldAt(x, y).collision();
	}

	public Field fieldAt(int x, int y) {
		if (x > GRID_SIZE - 1 || y > GRID_SIZE - 1) {
			throw new ProgrammingError(
					"Cannot access field outside of the grid (x = " + x
							+ ", y = " + y);
		}
		return fields[x][y];
	}

	private TwoDimensionalWorld(EventBus eventBus) {
		fields = new Field[GRID_SIZE][GRID_SIZE];
		createFields();
		connectFields();
		bouncerStartField = defaultBouncerStartField();
	}

	private Field defaultBouncerStartField() {
		return fields[0][0];
	}

	private void createFields() {
		for (int x = 0; x < fields.length; x++) {
			for (int y = 0; y < fields[x].length; y++) {
				fields[x][y] = new Field(FieldContent.FREE, Color.WHITE,
						new BeeperBag(), new Surroundings());
			}
		}
	}

	private void connectFields() {
		for (int x = 0; x < fields.length; x++) {
			for (int y = 0; y < fields[x].length; y++) {
				Field currentField = fields[x][y];
				if (x > 0) {
					Field westernNeighbour = fields[x - 1][y];
					currentField.setNeighbourInDirection(westernNeighbour,
							Direction.WEST);
				}
				if (x < fields.length - 1) {
					Field easternNeighbour = fields[x + 1][y];
					currentField.setNeighbourInDirection(easternNeighbour,
							Direction.EAST);
				}
				if (y > 0) {
					Field northernNeighbour = fields[x][y - 1];
					currentField.setNeighbourInDirection(northernNeighbour,
							Direction.NORTH);
				}
				if (y < fields[x].length - 1) {
					Field southernNeighbour = fields[x][y + 1];
					currentField.setNeighbourInDirection(southernNeighbour,
							Direction.SOUTH);
				}
			}
		}
	}

	public void purgeCollisions() {
		for (int x = 0; x < fields.length; x++) {
			for (int y = 0; y < fields[x].length; y++) {
				fieldAt(x, y).purgeCollision();
			}
		}
	}
}
