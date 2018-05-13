//package F2_Assignment.SPW_Modified;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class EnemyRain extends SpriteRectangle{
	public static final int Y_TO_FADE = 800;
	public static final int Y_TO_DIE = 650;
	
	private int step = 12;
	private boolean alive = true;
	
	public EnemyRain(int x, int y) {
		super(x, y, 5, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public void speedup()
	{
		step += 2;
	}

	public boolean isAlive(){
		return alive;
	}
}