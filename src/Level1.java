import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.ImageIcon;


public class Level1 extends Core implements Runnable{ //gjør så den bygger på classen Core. og gjør så den kan kan kjøres  med run()
	Main m; //for å slippe å skrive main.
	Image bg = new ImageIcon(Main.class.getResource("images/bg.png")).getImage();//bakgrunden til spillet
	
	public Level1(){
		m = new Main();
	}
	
	public void run() {
		super.run();//kjører run i Core
	}

	
	//set full screen
	public void init(){							//Denne blir kjørt fra core
		super.init();							//kjører init i core
		Window w = s.getFullScreenWindow();		//
		w.setFocusTraversalKeysEnabled(false);	//Noen skjermgreier
		w.addKeyListener(m);					//
	}
	
	//update the animation
	public void update(long timePassed, long currtime){
		
		Rectangle bu = new Rectangle(Main.Busthus.getX(),Main.Busthus.getY(),Main.Busthus.getWidth(),Main.Busthus.getHeight());
		Rectangle ma = new Rectangle(Main.Maeng.getX(), Main.Maeng.getY(),Main.Maeng.getWidth(),Main.Maeng.getHeight());
		
		if(ma.intersects(bu)){
			Main.Busthus.loseHealt(1, currtime);
		}
		
		if(Main.Busthus.getX() + Main.Busthus.getWidth()+1 >= s.getWidth() && m.right){		//
			Main.Busthus.setMoving(false);													//
			m.right = false;																//
		}																					//
		if(Main.Busthus.getX()-1 <= 0 && m.left){											//
			Main.Busthus.setMoving(false);													//Dette skal egentlig gjøre så man ikke kan gå utenfor skjermen
			m.left = false;																	//
		}																					//
		if(Main.Busthus.getY()-1 <= 0 && m.up){												//
			Main.Busthus.setMoving(false);													//
			m.up = false;																	//
		}																					//
		if(Main.Busthus.getY() + Main.Busthus.getHeight()+1 >= s.getHeight() && m.down){	//
			Main.Busthus.setMoving(false);													//
			m.down = false;																	//
		}																					//
		if(Main.Busthus.getMoving()){														//
			Main.Busthus.update(timePassed);												//
		}																					//
		
		String dir;
		dir = follow(Main.Maeng,Main.Busthus.getX(),Main.Busthus.getY());
		if(dir != null){
			Main.Maeng.setImage(dir);
			Main.Maeng.update(timePassed);
		}
		
		
	}
	
	public void draw(Graphics2D g) {
		g.clearRect(0, 0, 1000, 1000);																					//
		g.drawImage(bg,0,0,null);																						//Dette gjør først hele skjermen "blank" så tegner den bakrunden, så tegner den Busthus
		g.drawImage(Main.Maeng.getImage(), Math.round(Main.Maeng.getX()), Math.round(Main.Maeng.getY()), null);			//
		g.drawImage(Main.Busthus.getImage(), Math.round(Main.Busthus.getX()), Math.round(Main.Busthus.getY()), null);	//
		if(Main.Busthus.getHealt() == 6){
			g.drawImage(Main.wheart, 10, 30, null);
			g.drawImage(Main.wheart, 26, 30, null);
			g.drawImage(Main.wheart, 42, 30, null);
		}else if(Main.Busthus.getHealt() == 5){
			g.drawImage(Main.wheart, 10, 30, null);
			g.drawImage(Main.wheart, 26, 30, null);
			g.drawImage(Main.hheart, 42, 30, null);
		}else if(Main.Busthus.getHealt() == 4){
			g.drawImage(Main.wheart, 10, 30, null);
			g.drawImage(Main.wheart, 26, 30, null);
		}else if(Main.Busthus.getHealt() == 3){
			g.drawImage(Main.wheart, 10, 30, null);
			g.drawImage(Main.hheart, 26, 30, null);
		}else if(Main.Busthus.getHealt() == 2){
			g.drawImage(Main.wheart, 10, 30, null);
		}else if(Main.Busthus.getHealt() == 1){
			g.drawImage(Main.hheart, 10, 30, null);
		}else if(Main.Busthus.getHealt() == 0){
			g.drawString("GAME OVER!", s.getWidth()/2, s.getHeight()/2);
		}
		g.drawString(Main.version + "  Screen: " + s.getWidth() + " x " + s.getHeight()+"    Lives:"  + Main.Busthus.getHealt(), 10, 20);						//														//
		g.dispose();																									//
		
	}
	
	/*
	 * To get the direction to go by the coordinates to go to
	 */
	public String follow(Character c, int FX,int FY){
		int X;
		int Y;
		if(FX>c.getX()){
			X = FX - c.getX();
		}else{
			X = c.getX() - FX;
		}
		if(FY>c.getY()){
			Y = FY - c.getY();
		}else{
			Y = c.getY() - FY;
		}
		String direction = null;
		
		if(X>Y){
			if(FX>c.getX()){
				direction = "Right";
			}else if(FX<c.getX()){
				direction = "Left";
			}
		}else if(Y>X){
			if(FY>c.getY()){
				direction = "Down";
			}else if(FY<c.getY()){
				direction = "Up";
			}
		}
		return direction;
	}
	

	
	
	
}