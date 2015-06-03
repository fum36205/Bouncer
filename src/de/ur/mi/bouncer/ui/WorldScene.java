package de.ur.mi.bouncer.ui;
import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.apps.AppConfiguration;
import de.ur.mi.bouncer.world.Collision;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;

public class WorldScene {
	private TwoDimensionalWorld world;
	private final Bouncer bouncer;
	private final AppConfiguration appConfig;
	private final int squareSize;
	private final int windowSize;

	public WorldScene(TwoDimensionalWorld world, Bouncer bouncer,
			int windowSize, AppConfiguration appConfig) {
		this.world = world;
		this.bouncer = bouncer;
		this.windowSize = windowSize;
		this.squareSize = windowSize / world.size();
		this.appConfig = appConfig;
	}

	public void setWorld(TwoDimensionalWorld world) {
		this.world = world;
	}

	public void draw(GraphicsContext graphics) {
		drawBackground(graphics);
		drawGrid(graphics);
		drawWorld(graphics);
	}

	private void drawBackground(GraphicsContext graphics) {
		graphics.background(appConfig.backgroundColor());
	}

	private void drawGrid(GraphicsContext graphics) {
		graphics.noFill();
		for (int i = 1; i < world.size(); i++) {
			graphics.stroke(appConfig.gridColorFront());
			graphics.strokeWeight(appConfig.borderWeight());
			graphics.line(i * squareSize, 0, i * squareSize, windowSize);
			graphics.line(0, i * squareSize, windowSize, i * squareSize);
		}
	}

	private void drawWorld(GraphicsContext graphics) {
		for (int x = 0; x < world.size(); x++) {
			for (int y = 0; y < world.size(); y++) {
				if (world.hasObstacleAt(x, y)) {
					drawObstacle(graphics, x, y);
					continue;
				}
				drawColoredField(graphics, x, y);
				if (world.hasBouncerAt(x, y)) {
					drawBouncer(graphics, x, y);
				}
				if (world.hasCollisionAt(x, y)) {
					drawCollision(graphics, x, y);
				}
			}
		}
	}

	private void drawCollision(GraphicsContext graphics, int x, int y) {
		graphics.fill(appConfig.collisionColor());
		graphics.stroke(0xFF000000);
		Collision collision = world.collisionAt(x, y);
		int width = squareSize / 2;
		int height = squareSize / 2;
		int xPos = x * squareSize;
		int yPos = y * squareSize;
		float start = 0;
		float stop = 0;
		if (collision.direction == Direction.NORTH) {
			xPos += squareSize / 2;
			start = (float)0;
			stop = (float)(Math.PI);
		} else if (collision.direction == Direction.EAST) {
			xPos += squareSize;
			yPos += squareSize / 2;
			start = (float) (Math.PI / 2);
			stop = (float) (Math.PI + Math.PI /2);
		} else if (collision.direction == Direction.WEST) {
			yPos += squareSize / 2;
			start = (float)(-1*Math.PI/2);
			stop = (float)(Math.PI/2);
		} else {
			xPos += squareSize / 2;
			yPos += squareSize;
			start = (float)(Math.PI);
			stop = (float)(2*Math.PI);
		}
		graphics.arc(xPos, yPos, width, height, start, stop);
	}

	private void drawBouncer(GraphicsContext graphics, int x, int y) {
		float open = 0;
		float close = 0;
		switch (bouncer.currentOrientation()) {
		case NORTH:
			open = -70;
			close = 250;
			break;
		case EAST:
			open = 20;
			close = 340;
			break;
		case SOUTH:
			open = -250;
			close = 70;
			break;
		case WEST:
			open = -160;
			close = 160;
			break;
		}
		open = (float) Math.toRadians(open);
		close = (float) Math.toRadians(close);
		graphics.noStroke();
		graphics.fill(appConfig.bouncerColor());
		int xPos = x * squareSize + squareSize / 2;
		int yPos = y * squareSize + squareSize / 2;
		int radius = (int) (squareSize * appConfig.bouncerScaleFactor());
		graphics.arc(xPos, yPos, radius, radius, open, close);
	}

	private void drawColoredField(GraphicsContext graphics, int x, int y) {
		graphics.noStroke();
		switch (world.colorAt(x, y)) {
		case RED:
			graphics.fill(appConfig.redColor());
			break;
		case GREEN:
			graphics.fill(appConfig.greenColor());
			break;
		case BLUE:
			graphics.fill(appConfig.blueColor());
			break;
		case WHITE:
			return;
		}
		graphics.rectModeCorner();
		graphics.rect(x * squareSize + appConfig.borderWeight(), y * squareSize
				+ appConfig.borderWeight(),
				squareSize - appConfig.borderWeight(),
				squareSize - appConfig.borderWeight());
	}

	private void drawObstacle(GraphicsContext graphics, int x, int y) {
		graphics.noStroke();
		graphics.fill(appConfig.obstacleColor());
		graphics.rectModeCorner();
		graphics.rect(x * squareSize, y * squareSize, squareSize, squareSize);
	}
}
