package de.ur.mi.bouncer.apps;

import de.ur.mi.bouncer.world.FieldColor;

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

	public int valueForColor(FieldColor color) {
		switch(color) {
		case BLUE: return 0x320000DC;
		case GREEN: return 0x3200DC00;
		case RED: return 0x32DC0000;
		case PINK: return 0xFFED48CC;
		case ORANGE: return 0xFFFF952B;
		default: return 0xFFFFFFFF;
		}
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
