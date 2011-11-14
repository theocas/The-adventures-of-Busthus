import java.awt.Graphics2D;
import java.awt.Image;
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
	public void update(long timePassed){
		
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
		
		if(Main.h.containsPoint(Main.Busthus.getX(), Main.Busthus.getY())){
			Main.Busthus.setMoving(false);
		}
		
		if(Main.Busthus.getMoving()){														//
			Main.Busthus.update(timePassed);												//
		}																					//
		
		
	}
	
	public void draw(Graphics2D g) {
		g.clearRect(0, 0, 1000, 1000);																					//
		g.drawImage(bg,0,0,null);																						//Dette gjør først hele skjermen "blank" så tegner den bakrunden, så tegner den Busthus
		g.drawImage(Main.Busthus.getImage(), Math.round(Main.Busthus.getX()), Math.round(Main.Busthus.getY()), null);	//
		g.drawString(Main.version, 10, 20);																				//
		g.dispose();																									//
		
	}
	
}