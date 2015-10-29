package de.ur.mi.bouncer.world;

public enum FieldColor {
	RED, GREEN, WHITE, BLUE, PINK, ORANGE;
	
	public String toString() {
		switch(this.ordinal()) {
		case 0: return "FieldColor.RED";
		case 1: return "FieldColor.GREEN";
		case 2: return "FieldColor.WHITE";
		case 3: return "FieldColor.BLUE";
		case 4: return "FieldColor.PINK";
		case 5: return "FieldColor.ORANGE";
		default: return "";
		}
	};
}
