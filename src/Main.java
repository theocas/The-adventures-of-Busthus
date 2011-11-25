import java.awt.*;	//Masse imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main  implements KeyListener{ 	//Så du kan bruke tastaturet
	static Level1 l1;	//l1==Level1(en classe)
	static Menu menu;
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
	public static Image eheart;
	public static ScreenManager sm;
	static Game g;
	static GameOver gameo;

	
	public static void main(String[] args){	//Her starter programmet
		dstring += "[Main] starting game\n";
		sm = new ScreenManager();
		hheart = new ImageIcon(Main.class.getResource("images/halfheart.png")).getImage();
		wheart = new ImageIcon(Main.class.getResource("images/wholeheart.png")).getImage();
		eheart = new ImageIcon(Main.class.getResource("images/emptyheart.png")).getImage();
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
				new ImageIcon(Main.class.getResource("images/machgr2.png")).getImage(),			//Dette lagerer bildene for Busthus' sprite til høyre
				new ImageIcon(Main.class.getResource("images/machgr1.png")).getImage(),			//
				new ImageIcon(Main.class.getResource("images/machgr3.png")).getImage()};			//
		Busthus = new Busthus("Busthus",right,left,up,down,200,200,0.08f,0.0f,-0.08f,0.0f,0.0f,-0.08f,0.0f,0.08f,"Left", 6);//Dette lagrer alle spritene til Busthus i et eget objekt
		dstring += "[Main] Loaded Character(Busthus)\n";
		
		Image[] maengmove = {new ImageIcon(Main.class.getResource("images/maeng1.png")).getImage(),new ImageIcon(Main.class.getResource("images/maeng2.png")).getImage()};
		Maeng = new maeng("Maeng",maengmove,maengmove,maengmove,maengmove,500,500,0.05f,0.0f,-0.05f,0.0f,0.0f,-0.05f,0.0f,0.05f,"Left", 6);
		dstring += "[Main] Loaded Character(Maeng)\n";
		
		g = new Game();
		l1 = new Level1(g);			//Leser klassen Level1
		menu = new Menu(g);
		gameo = new GameOver(g);
		/*
		 *Launcher don't work propaly
		 *
		 *
		Gui go = new Gui(g);
		go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		go.setSize(500,500);
		go.setVisible(true);
		try{Thread.sleep(500);}catch(Exception ex){}
		while(go.running){}
		*/
		g.run();
		
		dstring += "[Main] game should be started\n";
	}

	
	//keypressed
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();	//Finner ut hvilken knapp som ble trykket
		e.consume();
	}
	
	//keyRelased
	public void keyReleased(KeyEvent e){
		int keyCode = e.getKeyCode();
		e.consume();
	}
	
	//last method from interface
	public void keyTyped(KeyEvent e){
		e.consume();
	}
	
}
