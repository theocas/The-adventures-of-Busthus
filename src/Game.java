import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game extends Core implements KeyListener{

	public String curr = "level1";
	static Main m;
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	
	public Game(){
		super();
		m = new Main();
	}

	//set full screen
	public void init(){							//Denne blir kjørt fra core
		super.init();							//kjører init i core
		Window w = s.getFullScreenWindow();		//
		w.setFocusTraversalKeysEnabled(false);	//Noen skjermgreier
		w.addKeyListener(this);					//
	}
	
	public void update(long timePassed, long currtime){
		if(curr == "level1"){
			Main.l1.update(timePassed, currtime);
		}else if(curr == "menu"){
			Main.menu.update(timePassed, currtime);
		}else if(curr == "gameover"){
			Main.gameo.update(timePassed, currtime);
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(curr == "level1"){
			Main.l1.draw(g);
		}else if(curr == "menu"){
			Main.menu.draw(g);
		}else if(curr == "gameover"){
			Main.gameo.draw(g);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();	//Finner ut hvilken knapp som ble trykket
		if(keyCode == KeyEvent.VK_ESCAPE){
			if(curr == "level1"){
				curr = "menu";
			}else if(curr == "menu"){
				curr = "level1";
			}
			
		}else if(keyCode == KeyEvent.VK_D){
			if(curr == "level1"){
				Main.Busthus.setImage("right");
				Main.Busthus.setMoving(true);
				right = true;
				left = false;
				up = false;
				down = false;
			}

		}else if(keyCode == KeyEvent.VK_A){
			if(curr == "level1"){
				Main.Busthus.setImage("left");
				Main.Busthus.setMoving(true);
				right = false;
				left = true;
				up = false;
				down = false;
			}

		}else if(keyCode == KeyEvent.VK_W){
			if(curr == "level1"){
				Main.Busthus.setImage("up");
				Main.Busthus.setMoving(true);
				right = false;
				left = false;
				up = true;
				down = false;
			}else if(curr == "gameover"){
				Main.gameo.selChange();
			}else if(curr == "menu"){
				Main.menu.selUp();
			}

		}else if(keyCode == KeyEvent.VK_S){
			if(curr == "level1"){
				Main.Busthus.setImage("down");
				Main.Busthus.setMoving(true);
				right = false;
				left = false;
				up = false;
				down = true;
			}else if(curr == "gameover"){
				Main.gameo.selChange();
			}else if(curr == "menu"){
				Main.menu.selDown();
			}
			
		}else if(keyCode == KeyEvent.VK_Q){
			stop();
		}else if(keyCode == KeyEvent.VK_ENTER){
			if(curr == "gameover"){
				if(Main.gameo.getSelection() == 1){
					Main.Busthus.lives = Main.Busthus.defLives;
					Main.Busthus.dead = false;
					Main.Busthus.setX((int) Main.Busthus.defX);
					Main.Busthus.setY((int) Main.Busthus.defY);
					left = false;
					right = false;
					up = false;
					down = false;
					Main.Busthus.setMoving(false);
					Main.Maeng.setX((int) Main.Maeng.defX);
					Main.Maeng.setY((int) Main.Maeng.defY);
					curr = "level1";
				}else{
					stop();
				}
			}else if(curr == "menu"){
				if(Main.menu.getSelection() == 2){
					curr = "level1";
				}else{
					stop();
				}
			}
		}else if(keyCode == KeyEvent.VK_SPACE){
			Main.Busthus.toggleAttack();
		}
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();	//Finner ut hvilken knapp som ble trykket
		if(keyCode == KeyEvent.VK_D){
			if(curr == "level1"){
				if(right){
					Main.Busthus.setMoving(false);//Sier at Busthus ikke er i becvegelse
				}
				right = false;
			}
			
		}else if(keyCode == KeyEvent.VK_A){
			if(curr == "level1"){
				if(left){
					Main.Busthus.setMoving(false);
				}
				left = false;
			}
			
		}else if(keyCode== KeyEvent.VK_W){
			if(curr == "level1"){
				if(up){
					Main.Busthus.setMoving(false);
				}
				up = false;
			}
			
		}else if(keyCode== KeyEvent.VK_S){
			if(curr == "level1"){
				if(down){
					Main.Busthus.setMoving(false);
				}
				down = false;
			}
			
		}else if(keyCode == KeyEvent.VK_SPACE){
			Main.Busthus.toggleAttack();
		}
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

}
