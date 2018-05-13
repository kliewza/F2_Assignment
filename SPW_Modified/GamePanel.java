//package F2_Assignment.SPW_Modified;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	ArrayList<EnemyRain> rainy = new ArrayList<EnemyRain>();
	ArrayList<EnemyBubble> bubbles = new ArrayList<EnemyBubble>();

	public GamePanel() {
		bi = new BufferedImage(800, 650, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 800, 650);
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("PLAYER01: %08d", reporter.getScore01()), 50, 20);
		big.drawString(String.format("PLAYER02: %08d", reporter.getScore02()), 610, 20);
		
		for(Sprite s : sprites){
			s.draw(big);
		}

		for(EnemyRain r : rainy){
			r.draw(big);
		}

		for(EnemyBubble b : bubbles){
			b.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
