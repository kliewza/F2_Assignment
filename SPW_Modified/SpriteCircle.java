//package F2_Assignment.SPW_Modified;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;


public abstract class SpriteCircle {
	int x;
	int y;
	int width;
	int height;
	
	public SpriteCircle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	abstract public void draw(Graphics2D g);

	public Double getCircle() {
		return new Ellipse2D.Double(x, y, width, height);
	}
}
