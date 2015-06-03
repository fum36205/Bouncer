package de.ur.mi.bouncer.ui;
public interface GraphicsContext {
	void background(int color);
	void noFill();
	void noStroke();
	void stroke(int color);
	void strokeWeight(float weight);
	void line(float x1, float y1, float x2, float y2);
	void rect(float a, float b, float c, float d);
	void rectModeCorner();
	void fill(int color);
	void arc(float a, float b, float c, float d, float start, float stop);
	void ellipse(float x, float y, float width, float height);
}
