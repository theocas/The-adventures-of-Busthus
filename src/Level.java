import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;



public abstract class Level{
	Game g;
	Image bg;
	Rectangle info = new Rectangle(0,0,300,50);
	
	public Level(Game g){
		this.g = g;
	}

	//update the animation
	public void update(long timePassed, long currtime){
		
		Rectangle bu = new Rectangle(Main.Busthus.getX(),Main.Busthus.getY(),Main.Busthus.getWidth(),Main.Busthus.getHeight());
		Rectangle ma = new Rectangle(Main.Maeng.getX(), Main.Maeng.getY(),Main.Maeng.getWidth(),Main.Maeng.getHeight());
		
		if(ma.intersects(bu) && !Main.Maeng.dead && currtime-Main.Busthus.deadtime>2000 && !Main.debug){
				if(Main.Busthus.attacking && g.left && Main.Maeng.getX() < Main.Busthus.getX()){
					Main.Maeng.loseHealt(2, currtime);
					Main.Maeng.setX(Main.Maeng.getX() - 30);
				}else if(Main.Busthus.attacking && g.right && Main.Maeng.getX() > Main.Busthus.getX()){
					Main.Maeng.loseHealt(2, currtime);
					Main.Maeng.setX(Main.Maeng.getX() + 30);
				}else if(Main.Busthus.attacking && g.up && Main.Maeng.getY() < Main.Busthus.getY()){
					Main.Maeng.loseHealt(2, currtime);
					Main.Maeng.setY(Main.Maeng.getY() - 30);
				}else if(Main.Busthus.getMoving()){
					
					if(g.down && Main.Maeng.getY() > Main.Busthus.getY()){
						Main.Maeng.loseHealt(2, currtime);
						Main.Maeng.setY(Main.Maeng.getY() + 30);
					}else if(g.down){
						Main.Busthus.setY(Main.Busthus.getY()-25);
					}else if(g.up){
						Main.Busthus.setY(Main.Busthus.getY()+25);
					}else if(g.left){
						Main.Busthus.setX(Main.Busthus.getX()+25);
					}else{
						Main.Busthus.setX(Main.Busthus.getX()-25);
					}
					
				}else{
					
					int EX = Main.Maeng.getX();
					int EY = Main.Maeng.getY();
					int CX = Main.Busthus.getX();
					int CY = Main.Busthus.getY();
					int DX;
					int DY;
					
					if(EX>CX){
						DX = EX - CX;
					}else{
						DX = CX - EX;
					}
					if(EY>CY){
						DY = EY - CY;
					}else{
						DY = CY - EY;
					}
					
					 if(DX>DY){
						 
						 if(EX>CX){
							 Main.Busthus.setX(Main.Busthus.getX()-25);
						 }else{
							 Main.Busthus.setX(Main.Busthus.getX()+25);
						 }
						 
					 }else{
						 
						 if(EY>CY){
							 Main.Busthus.setY(Main.Busthus.getY()-25);
						 }else{
							 Main.Busthus.setY(Main.Busthus.getY()+25);
						 }
						 
					 }
					
				}
				

				Main.Busthus.loseHealt(1, currtime);
			
		}
		
		if(bu.intersects(info)){
			
			if(Main.Busthus.getMoving()){
				
				if(g.up && Main.Busthus.getX()<info.getWidth()-2){
					g.up = false;
					Main.Busthus.setMoving(false);
				}else if(g.left && Main.Busthus.getY()<info.getHeight()-2){
					g.left = false;
					Main.Busthus.setMoving(false);
				}
				
			}
			
		}
		
		if(Main.Busthus.getX() + Main.Busthus.getWidth()+1 >= g.s.getWidth() && g.right){		//
			Main.Busthus.setMoving(false);													//
			g.right = false;																//
		}																					//
		if(Main.Busthus.getX()-1 <= 0 && g.left){											//
			Main.Busthus.setMoving(false);													//Dette skal egentlig gjøre så man ikke kan gå utenfor skjermen
			g.left = false;																	//
		}																					//
		if(Main.Busthus.getY()-1 <= 0 && g.up){												//
			Main.Busthus.setMoving(false);													//
			g.up = false;																	//
		}																					//
		if(Main.Busthus.getY() + Main.Busthus.getHeight()+1 >= g.s.getHeight() && g.down){	//
			Main.Busthus.setMoving(false);													//
			g.down = false;																	//
		}																					//
		if(Main.Busthus.getMoving()){														//
			Main.Busthus.update(timePassed);												//
		}																					//
		System.out.println("getting follow");
		String dir = follow(Main.Maeng, Main.Busthus.getX(), Main.Busthus.getY());
		System.out.println("follow got");
		if(dir != null){
			System.out.println("Follow was not null");
			Main.Maeng.setImage(dir);
			Main.Maeng.update(timePassed);
		}
		
		
	}
	/*
	 * Ths code firs draw the peson and the emeny, then the hearts(the long code) then the debug info.
	 */
	public void draw(Graphics2D g) {
		g.clearRect(0, 0, 1000, 1000);																					//
		g.drawImage(bg,0,0,null);																						//Dette gjør først hele skjermen "blank" så tegner den bakrunden, så tegner den Busthus
		if(!Main.Maeng.dead && !Main.debug){
			g.drawImage(Main.Maeng.getImage(), Math.round(Main.Maeng.getX()), Math.round(Main.Maeng.getY()), null);			//
		}
		g.drawImage(Main.Busthus.getImage(), Math.round(Main.Busthus.getX()), Math.round(Main.Busthus.getY()), null);	//
		g.draw(info);
		Color c = g.getColor();
		g.setColor(new Color(96, 96, 96));
		g.fill(info);
		g.setColor(c);
		drawHeart(g);
		g.drawString(Main.version, 10, 20);						//														//
		g.dispose();																									//

		
	}
	
	public void drawHeart(Graphics2D g){
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
			g.drawImage(Main.eheart, 42, 30, null);
		}else if(Main.Busthus.getHealt() == 3){
			g.drawImage(Main.wheart, 10, 30, null);
			g.drawImage(Main.hheart, 26, 30, null);
			g.drawImage(Main.eheart, 42, 30, null);
		}else if(Main.Busthus.getHealt() == 2){
			g.drawImage(Main.wheart, 10, 30, null);
			g.drawImage(Main.eheart, 26, 30, null);
			g.drawImage(Main.eheart, 42, 30, null);
		}else if(Main.Busthus.getHealt() == 1){
			g.drawImage(Main.hheart, 10, 30, null);
			g.drawImage(Main.eheart, 26, 30, null);
			g.drawImage(Main.eheart, 42, 30, null);
		}else if(Main.Busthus.getHealt() == 0){
			g.drawString("GAME OVER!", this.g.s.getWidth()/2, this.g.s.getHeight()/2);
			g.drawImage(Main.eheart, 10, 30, null);
			g.drawImage(Main.eheart, 26, 30, null);
			g.drawImage(Main.eheart, 42, 30, null);
		}
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
		
		System.out.println("FX = "+FX);
		System.out.println("FY = "+FY);
		
		if(Y==X){
			if(FX>c.getX()){
				return "Right";
			}else if(FX<c.getX()){
				return "Left";
			}else{
				return "Right";
			}
		}else if(X>Y){
			if(FX>c.getX()){
				return "Right";
			}else if(FX<c.getX()){
				return "Left";
			}
		}else if(Y>X){
			if(FY>c.getY()){
				return "Down";
			}else if(FY<c.getY()){
				return "Up";
			}
		}
		return "Up";
	}
	
	public abstract void houseCollideCheck();
	
}