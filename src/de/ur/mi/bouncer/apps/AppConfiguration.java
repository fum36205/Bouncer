package de.ur.mi.bouncer.apps;
public class AppConfiguration {

	public int defaultWindowSize() {
		return 600;
	}

	public int gridSize() {
		return 15;
	}

	public int defaultSquareSize() {
		return defaultWindowSize() / gridSize();
	}

	public int frameRate() {
		return 7;
	}

	public int smoothLevel() {
		return 8;
	}

	public int borderWeight() {
		return 2;
	}

	public int backgroundColor() {
		return 0x000000FF;
	}

	public int gridColorFront() {
		return 0x0000001E;
	}

	public int obstacleColor() {
		return 0x0000001E;
	}

	public int redColor() {
		return 0x32DC0000;
	}

	public int greenColor() {
		return 0x3200DC00;
	}

	public int blueColor() {
		return 0x320000DC;
	}

	public int windowSizeFor(int displayHeight) {
		return (int) (displayHeight * 0.8);
	}
	
	public double bouncerScaleFactor() {
		return 0.8;
	}
	
	public int bouncerColor() {
		return -4608;
	}

	public int collisionColor() {
		return 0xFFFF9933;
	}
}
