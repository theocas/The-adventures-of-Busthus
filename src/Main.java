import java.awt.*;	//Masse imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Main  implements KeyListener{ 	//S� du kan bruke tastaturet
	static Level1 l1;	//l1==Level1(en classe)
	public static String version = "The adventures of Busthus version: Alpha 1";
	public static Character Busthus;
	public static Character Maeng;
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	public static boolean debug = false;
	public static String dstring = "[DEBUG]\n";
	public static Image wheart;
	public static Image hheart;

	
	public static void main(String[] args){	//Her starter programmet
		dstring += "[Main] starting game\n";
		hheart = new ImageIcon(Main.class.getResource("images/halfheart.png")).getImage();
		wheart = new ImageIcon(Main.class.getResource("images/wholeheart.png")).getImage();
		Image[] up = {new ImageIcon(Main.class.getResource("images/machgu1.png")).getImage(),	//
				new ImageIcon(Main.class.getResource("images/machgu2.png")).getImage(),			//Dette lagerer bildene for Busthus' sprite opp
				new ImageIcon(Main.class.getResource("images/machgu1.png")).getImage(),			//
				new ImageIcon(Main.class.getResource("images/machgu3.png")).getImage()};			//
		
		Image[] down = {new ImageIcon(Main.class.getResource("images/machgd1.png")).getImage(),	//
				new ImageIcon(Main.class.getResource("images/machgd2.png")).getImage(),			//Dette lagerer bildene for Busthus' sprite ned
				new ImageIcon(Main.class.getResource("images/machgd1.png")).getImage(),			//
				new ImageIcon(Main.class.getResource("images/machgd3.png")).getImage()};			//
		
		Image[] left = {new ImageIcon(Main.class.getResource("images/machgl1.png")).getImage(),	//
				new ImageIcon(Main.class.getResource("images/machgl2.png")).getImage(),			//Dette lagerer bildene for Busthus' sprite til venstre
				new ImageIcon(Main.class.getResource("images/machgl1.png")).getImage(),			//
				new ImageIcon(Main.class.getResource("images/machgl3.png")).getImage()};			//
		
		Image[] right = {new ImageIcon(Main.class.getResource("images/machgr1.png")).getImage(),	//
				new ImageIcon(Main.class.getResource("images/machgr2.png")).getImage(),			//Dette lagerer bildene for Busthus' sprite til h�yre
				new ImageIcon(Main.class.getResource("images/machgr1.png")).getImage(),			//
				new ImageIcon(Main.class.getResource("images/machgr3.png")).getImage()};			//
		Busthus = new Character("Busthus",right,left,up,down,200,200,0.08f,0.0f,-0.08f,0.0f,0.0f,-0.08f,0.0f,0.08f,"Left", 6);//Dette lagrer alle spritene til Busthus i et eget objekt
		dstring += "[Main] Loaded Character(Busthus)\n";
		
		Image[] maengmove = {new ImageIcon(Main.class.getResource("images/maeng1.png")).getImage(),new ImageIcon(Main.class.getResource("images/maeng2.png")).getImage()};
		Maeng = new Character("Maeng",maengmove,maengmove,maengmove,maengmove,500,500,0.05f,0.0f,-0.05f,0.0f,0.0f,-0.05f,0.0f,0.05f,"Left", 6);
		dstring += "[Main] Loaded Character(Maeng)\n";
		
		l1 = new Level1();			//Leser klassen Level1
		l1.run();			//kj�rer run() i l1
		dstring += "[Main] game should be started\n";
	}

	
	//keypressed
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();	//Finner ut hvilken knapp som ble trykket
		dstring += "[Main] a button has been pushed\n";
		
		//Dette er ikke s� vanskelig. den finner ut hvilken knapp det er og gj�r ting deretter
		if(keyCode == KeyEvent.VK_ESCAPE){
			dstring += "[Main] it was escape\n";
			dstring += "[Main] stopping game\n";
			l1.stop();	//Stopper level1
			dstring += "[Main] game should be stopped\n";
		}else if(keyCode == KeyEvent.VK_D){
			Busthus.setImage("right");//sier at det er right/h�yre som er den gjeldende veien Busthus g�r
			Busthus.setMoving(true);//Sier at Busthus er i bevegelse
			right = true;
			left = false;
			up = false;
			down = false;
		}else if(keyCode == KeyEvent.VK_A){
			Busthus.setImage("left");
			Busthus.setMoving(true);
			right = false;
			left = true;
			up = false;
			down = false;
		}else if(keyCode == KeyEvent.VK_W){
			Busthus.setImage("up");
			Busthus.setMoving(true);
			right = false;
			left = false;
			up = true;
			down = false;
		}else if(keyCode == KeyEvent.VK_S){
			Busthus.setImage("down");
			Busthus.setMoving(true);
			right = false;
			left = false;
			up = false;
			down = true;
		}
		e.consume();
	}
	
	//keyRelased
	public void keyReleased(KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_D){
			if(right){
				Busthus.setMoving(false);//Sier at Busthus ikke er i becvegelse
			}
			right = false;
		}else if(keyCode == KeyEvent.VK_A){
			if(left){
				Busthus.setMoving(false);
			}
			left = false;
		}else if(keyCode== KeyEvent.VK_W){
			if(up){
				Busthus.setMoving(false);
			}
			up = false;
		}else if(keyCode== KeyEvent.VK_S){
			if(down){
				Busthus.setMoving(false);
			}
			down = false;
		}
		e.consume();
	}
	
	//last method from interface
	public void keyTyped(KeyEvent e){
		e.consume();
	}
	
}
