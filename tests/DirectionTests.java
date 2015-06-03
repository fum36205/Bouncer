import static org.junit.Assert.*;

import org.junit.Test;

import de.ur.mi.bouncer.Direction;

public class DirectionTests {

	@Test
	public void returnsWestAfterLeftTurnFromNorth() {
		assertEquals(Direction.WEST, Direction.NORTH.afterLeftTurn());
	}

	@Test
	public void returnsSouthAfterLeftTurnFromWest() throws Exception {
		assertEquals(Direction.SOUTH, Direction.WEST.afterLeftTurn());
	}

	@Test
	public void returnsEastAfterLeftTurnFromSouth() throws Exception {
		assertEquals(Direction.EAST, Direction.SOUTH.afterLeftTurn());
	}

	@Test
	public void returnsNorthAfterLeftTurnFromEast() throws Exception {
		assertEquals(Direction.NORTH, Direction.EAST.afterLeftTurn());
	}

	@Test
	public void returnsNorthAfterRightTurnFromWest() throws Exception {
		assertEquals(Direction.NORTH, Direction.WEST.afterRightTurn());
	}

	@Test
	public void returnsEastAfterRightTurnFromNorth() throws Exception {
		assertEquals(Direction.EAST, Direction.NORTH.afterRightTurn());
	}

	@Test
	public void returnsSouthAfterRightTurnFromEast() throws Exception {
		assertEquals(Direction.SOUTH, Direction.EAST.afterRightTurn());
	}
	
	@Test
	public void returnsWestAfterRightTurnFromSouth() throws Exception {
		assertEquals(Direction.WEST, Direction.SOUTH.afterRightTurn());
	}

}
