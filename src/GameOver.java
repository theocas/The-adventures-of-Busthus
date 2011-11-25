import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class GameOver {

	Game g;
	Image gameover;
	
	public GameOver(Game g){
		this.g = g;
		gameover = new ImageIcon(Main.class.getResource("images/gameover.png")).getImage();
	}
	
	public void update(long timePassed, long currtime){
		
	}
	
	public void draw(Graphics2D g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.g.s.getWidth(), this.g.s.getHeight());
		g.setColor(c);
		g.drawImage(gameover, 0,0, null);
		g.dispose();
	}
	
}
