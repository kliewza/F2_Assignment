//package F2_Assignment.SPW_Modified;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Start {
	public Start(){

	}

	public void start(){
		JFrame frame = new JFrame("Space War: Modified");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 650);

		frame.getContentPane().setLayout(new BorderLayout());;    
        SpaceShip01 v1 = new SpaceShip01(375, 550, 20, 20);
		SpaceShip02 v2 =new SpaceShip02(375, 550, 20, 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v1, v2, frame);

        frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
        engine.start();
	}
}

