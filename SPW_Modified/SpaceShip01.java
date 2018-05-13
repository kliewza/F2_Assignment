//package F2_Assignment.SPW_Modified;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip01 extends Sprite{

	int step = 8;
	private boolean alive = true;
	
	public SpaceShip01(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}


	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 800 - width)
			x = 800 - width;
	}

	public boolean isAlive(){
		return alive;
	}

	public void die()
	{
		alive = false;
	}

}
