package de.ur.mi.bouncer.world;

import de.ur.mi.ProgrammingError;
import de.ur.mi.bouncer.BeeperBag;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.error.FieldIndexOutOfBoundsException;
import de.ur.mi.bouncer.events.EventBus;
import de.ur.mi.bouncer.events.NullEventBus;

public class TwoDimensionalWorld {
	private static final int DEFAULT_GRID_SIZE = 15;
	private final Field[][] fields;
	private Field bouncerStartField;

	public static TwoDimensionalWorld emptyWorld() {
		return new TwoDimensionalWorld(new NullEventBus());
	}

	public static TwoDimensionalWorld emptyWorldWithSize(int size) {
		return new TwoDimensionalWorld(new NullEventBus(), size);
	}

	public void placeObstacleAt(int x, int y) {
		Field field = fieldAt(x, y);
		field.setContent(FieldContent.OBSTACLE);
	}

	public void paintFieldAt(int x, int y, FieldColor fieldColor) {
		Field field = fieldAt(x, y);
		field.paintWith(fieldColor);
	}

	public void setBouncerStartPosition(int x, int y) {
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

	public FieldColor colorAt(int x, int y) {
		return fieldAt(x, y).color();
	}

	public Collision collisionAt(int x, int y) {
		return fieldAt(x, y).collision();
	}

	public Field fieldAtRowAndColumn(int rowIdx, int colIdx)
			throws FieldIndexOutOfBoundsException {
		if (rowIdx > fields.length - 1 || rowIdx < 0) {
			throw new FieldIndexOutOfBoundsException("den Zeilenindex \"rowIdx\"",
					fields.length - 1, rowIdx);
		}
		if (colIdx > fields[rowIdx].length - 1 || colIdx < 0) {
			throw new FieldIndexOutOfBoundsException("den Spaltenindex \"colIdx\"",
					fields[rowIdx].length - 1, colIdx);
		}
		return fields[colIdx][rowIdx];
	}

	public Field fieldAtXandY(int x, int y)
			throws FieldIndexOutOfBoundsException {
		if (y > fields.length - 1 || y < 0) {
			throw new FieldIndexOutOfBoundsException("die y-Koordinate", fields.length - 1, y);
		}
		if (x > fields[y].length - 1 || x < 0) {
			throw new FieldIndexOutOfBoundsException("die x-Koordinate", fields[y].length - 1,
					x);
		}
		return fields[y][x];
	}

	private Field fieldAt(int i, int j) {
		if (i > fields.length - 1 || j > fields[i].length - 1) {
			throw new ProgrammingError(
					"Tried to access field index out of bounds (i = " + i
							+ ", j = " + j + ")");
		}
		return fields[i][j];
	}

	private TwoDimensionalWorld(EventBus eventBus) {
		this(eventBus, DEFAULT_GRID_SIZE);
	}

	private TwoDimensionalWorld(EventBus eventBus, int gridSize) {
		fields = new Field[gridSize][gridSize];
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
				fields[x][y] = new Field(FieldContent.FREE, FieldColor.WHITE,
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
