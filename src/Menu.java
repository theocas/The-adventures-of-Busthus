import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Menu{
	
	Game g;
	Image menu;
	Image selector;
	private int selection = 1;
	
	public Menu(Game g){
		this.g = g;
		menu = new ImageIcon(Main.class.getResource("images/menu.png")).getImage();
		selector = new ImageIcon(Main.class.getResource("images/cursor.png")).getImage();
	}
	
	public void update(long timePassed, long currtime){
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(menu,0,0,null);
		if(selection == 1){
			g.drawImage(selector, 205, 225, null);
		}else if(selection == 2){
			g.drawImage(selector,205, 315, null);
		}else{
			g.drawImage(selector,205, 400, null);
		}
	}
	
	public void selDown(){
		if(selection == 1){
			selection = 2;
		}else if(selection == 2){
			selection = 3;
		}else{
			selection = 1;
		}
	}
	
	public void selUp(){
		if(selection == 1){
			selection = 3;
		}else if(selection == 2){
			selection = 1;
		}else{
			selection = 2;
		}
	}
	
	public int getSelection(){
		return selection;
	}
	
}
