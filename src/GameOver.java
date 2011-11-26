import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class GameOver {

	Game g;
	Image gameover;
	Image selector;
	private int selection = 1;
	
	public GameOver(Game g){
		this.g = g;
		gameover = new ImageIcon(Main.class.getResource("images/gameover.png")).getImage();
		selector = new ImageIcon(Main.class.getResource("images/cursor.png")).getImage();
	}
	
	public void update(long timePassed, long currtime){
		
	}
	
	public void draw(Graphics2D g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.g.s.getWidth(), this.g.s.getHeight());
		g.setColor(c);
		g.drawImage(gameover, 0,0, null);
		if(selection == 1){
			g.drawImage(selector, 155, 390, null);
		}else{
			g.drawImage(selector,155,436,null);
		}
		g.dispose();
	}
	
	public void selChange(){
		if(selection == 1){
			selection = 2;
		}else{
			selection = 1;
		}
	}
	
	public int getSelection(){
		return selection;
	}
	
}
