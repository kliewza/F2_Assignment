//package F2_Assignment.SPW_Modified;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	
	private SpaceShip01 v1;
	private SpaceShip02 v2;
	private ArrayList<EnemyRain> rain = new ArrayList<EnemyRain>();	
	private ArrayList<EnemyBubble> bubble = new ArrayList<EnemyBubble>();
	
	JFrame frame;
	private Timer timer01;
	private int timer02;
	private long score01 = 0;
	private long score02 = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip01 v1, SpaceShip02 v2, JFrame frame) {
		this.gp = gp;
		this.v1 = v1;
		this.v2 = v2;
		this.frame = frame;	
		
		gp.sprites.add(v1);
		gp.sprites.add(v2);
		
		timer01 = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
				timer02 += 1;
			}
		});
		timer01.setRepeats(true);
		
	}
	
	public void start(){
		timer01.start();
	}
	
	private void generateRain(){
		EnemyRain er = new EnemyRain((int)(Math.random()*800), 50);
		
		if(timer02 % 2 == 0)
		{
			difficulty = difficulty + 0.005;
			er.speedup();
		}
		
		gp.rainy.add(er);
		rain.add(er);
	}

	private void generateBubble(){
		EnemyBubble eb = new EnemyBubble((int)(Math.random()*800), 20);

		if(timer02 % 5 == 0)
		{
			difficulty = difficulty + 0.002;
			eb.speedup();
		}

		gp.bubbles.add(eb);
		bubble.add(eb);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateRain();
			generateBubble();
		}
		
		Iterator<EnemyRain> er_iter = rain.iterator();
		while(er_iter.hasNext()){
			EnemyRain er = er_iter.next();
			er.proceed();
			if(!er.isAlive()){
				er_iter.remove();
				gp.rainy.remove(er);
			if(v1.isAlive()){
				score01 += 100;
			}
			if(v2.isAlive()){
				score02 += 100;
			}
			}
		}

		Iterator<EnemyBubble> eb_iter = bubble.iterator();
		while(eb_iter.hasNext()){
			EnemyBubble eb = eb_iter.next();
			eb.proceed();
			
			if(!eb.isAlive()){
				eb_iter.remove();
				gp.bubbles.remove(eb);
			if(v1.isAlive()){
				score01 += 50;
			}
			if(v2.isAlive()){
				score02 += 50;
			}
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr1 = v1.getRectangle();
		Rectangle2D.Double vr2 = v2.getRectangle();
		Rectangle2D.Double r;
		Ellipse2D.Double b;

		for(EnemyRain er : rain){
			r = er.getRectangle();
			if(r.intersects(vr1)){
				die();
				v1.die();
				gp.sprites.remove(v1);
				return;
			}
			if(r.intersects(vr2)){
				die();
				v2.die();
				gp.sprites.remove(v2);
				return;
			}
		}
		for(EnemyBubble eb : bubble){
			b = eb.getCircle();
			if(b.intersects(vr1)){
				die();
				v1.die();
				gp.sprites.remove(v1);
				return;
			}
			if(b.intersects(vr2)){
				die();
				v2.die();
				gp.sprites.remove(v2);
				return;
			}
		}
	}
	
	public void die(){
		long scoreEnd01 = getScore01();
		long scoreEnd02 = getScore02();
		
		if(!((v1.isAlive())||(v2.isAlive())))
		{
			
			if(scoreEnd01 > scoreEnd02)
			{
				JOptionPane.showMessageDialog(null, "Player1 WON!\nYour Score: " + scoreEnd01, "Result", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(scoreEnd01 == scoreEnd02)
			{
				JOptionPane.showMessageDialog(null, "DRAW!\nYour Score: "+ scoreEnd01, "Result", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Player2 WON!\nYour Score: "+ scoreEnd02, "Result", JOptionPane.INFORMATION_MESSAGE);
			}

			frame.dispose();
			timer01.stop();	
		}	
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v1.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v1.move(1);
			break;
		case KeyEvent.VK_A:
			v2.move(-1);
			break;
		case KeyEvent.VK_D:
			v2.move(1);
			break;
		}
	}

	public long getScore01(){
		return score01;
	}

	public long getScore02(){
		return score02;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
