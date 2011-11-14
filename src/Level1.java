import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;

import javax.swing.ImageIcon;


public class Level1 extends Core implements Runnable{ //gj�r s� den bygger p� classen Core. og gj�r s� den kan kan kj�res  med run()
	Main m; //for � slippe � skrive main.
	Image bg = new ImageIcon(Main.class.getResource("images/bg.png")).getImage();//bakgrunden til spillet
	
	public Level1(){
		m = new Main();
	}
	
	public void run() {
		super.run();//kj�rer run i Core
	}

	
	//set full screen
	public void init(){							//Denne blir kj�rt fra core
		super.init();							//kj�rer init i core
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
			Main.Busthus.setMoving(false);													//Dette skal egentlig gj�re s� man ikke kan g� utenfor skjermen
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
		g.drawImage(bg,0,0,null);																						//Dette gj�r f�rst hele skjermen "blank" s� tegner den bakrunden, s� tegner den Busthus
		g.drawImage(Main.Busthus.getImage(), Math.round(Main.Busthus.getX()), Math.round(Main.Busthus.getY()), null);	//
		g.drawString(Main.version, 10, 20);																				//
		g.dispose();																									//
		
	}
	
}