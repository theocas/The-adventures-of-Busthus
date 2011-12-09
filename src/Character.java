import java.awt.Image;




public class Character {
	
	
	public double defX;
	public double defY;
	public int defLives;
	String name;					//
	String currentimage;			//
	Image ir;						//
	Image il;						//
	Image iu;						//
	Image id;						//
	Animation ar;					//
	Animation al;					//
	Animation au;					//
	Animation ad;					//
	Sprite sr;						//
	Sprite sl;						//Dette sier seg vel selv?
	Sprite su;						//
	Sprite sd;						//
	double x;						//
	double y;						//
	float vxr;						//
	float vyr;						//
	float vxl;						//
	float vyl;						//
	float vxu;						//
	float vyu;						//
	float vxd;						//
	float vyd;						//
	Image[] attacks;				//
	Boolean moving = false;			//
	int lives;						//
	boolean losingHealt = false;	//
	boolean dead = false;			//
	long deadtime = 0;
	boolean attacking = false;
	
	
	public Character(String name, Image[] ar, Image[] al, Image[] au, Image[] ad, Image[] attack, double x, double y, float vxr, float vyr, float vxl, float vyl, float vxu, float vyu, float vxd, float vyd, String currentimage, int lives){
		
		this.name = name;					//
		this.currentimage = currentimage;	//
		this.x = x;							//
		defX = x;
		this.y = y;							//
		defY = y;
		this.vxr = vxr;						//
		this.vyr = vyr;						//
		this.vxl = vxl;						//
		this.vyl = vyl;						//
		this.vxu = vxu;						//Dette setter variablene fra i stad en verdi
		this.vyu = vyu;						//
		this.vxd = vxd;						//
		this.vyd = vyd;						//
		this.ir = ar[0];					//
		this.il = al[0];					//
		this.iu = au[0];					//
		this.id = ad[0];					//
		this.lives = lives;					//
		defLives = lives;
		this.attacks = attack;
		
		this.ar = new Animation();
		for(int counter=0; counter<ar.length; counter++){
			this.ar.addScene(ar[counter], 250);
		}
		
		this.al = new Animation();
		for(int count=0; count<al.length; count++){
			this.al.addScene(al[count], 250);
		}
		
		this.au = new Animation();
		for(int counter=0; counter<au.length; counter++){
			this.au.addScene(au[counter], 250);
		}
		
		this.ad = new Animation();
		for(int counter=0; counter<ad.length; counter++){
			this.ad.addScene(ad[counter], 250);
		}
		
		sr = new Sprite(this.ar);
		sr.setVelocityX(this.vxr);
		sr.setVelocityY(this.vyr);
		
		sl = new Sprite(this.al);
		sl.setVelocityX(this.vxl);
		sl.setVelocityY(this.vyl);
		
		su = new Sprite(this.au);
		su.setVelocityX(this.vxu);
		su.setVelocityY(this.vyu);
		
		sd = new Sprite(this.ad);
		sd.setVelocityX(this.vxd);
		sd.setVelocityY(this.vyd);
		
		sr.setY((float)y);
		sl.setY((float)y);
		su.setY((float)y);
		sd.setY((float)y);
		sr.setX((float)x);
		sl.setX((float)x);
		su.setX((float)x);
		sd.setX((float)x);
		
	}
	
	public Image getImage(){
		if(currentimage == "Left"){
			if(attacking){
				return attacks[0];
			}
			return il;
		}else if(currentimage == "Up"){
			if(attacking){
				return attacks[2];
			}
			return iu;
		}else if(currentimage == "Down"){
			if(attacking){
				return attacks[3];
			}
			return id;
		}
		if(attacking){
			return attacks[1];
		}
		return ir;
	}
	
	public void setImage(String image){
		if(image == currentimage){
			return;
		}else if(image.equalsIgnoreCase("left")){
			currentimage = "Left";
		}else if(image.equalsIgnoreCase("right")){
			currentimage = "Right";
		}else if(image.equalsIgnoreCase("up")){
			currentimage = "Up";
		}else if(image.equalsIgnoreCase("down")){
			currentimage = "Down";
		}
	}
	
	public void setX(int x){
		this.x = x;
		sr.setX(x);
		sl.setX(x);
		su.setX(x);
		sd.setX(x);
	}
	public void setY(int y){
		this.y = y;
		sr.setY(y);
		sl.setY(y);
		su.setY(y);
		sd.setY(y);
	}
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	
	public void setVelocityX(float vx, String Sprite){
		if(Sprite.equalsIgnoreCase("right")){
			this.vxr = vx;
			sr.setVelocityX(vx);
		}else if(Sprite.equalsIgnoreCase("left")){
			this.vxl = vx;
			sl.setVelocityX(vx);
		}else if(Sprite.equalsIgnoreCase("up")){
			this.vxu = vx;
			su.setVelocityX(vx);
		}else if(Sprite.equalsIgnoreCase("down")){
			this.vxd = vx;
			sd.setVelocityX(vx);
		}
	}
	public void setVelocityY(float vy, String Sprite){
		if(Sprite.equalsIgnoreCase("right")){
			this.vyr = vy;
			sr.setVelocityY(vy);
		}else if(Sprite.equalsIgnoreCase("left")){
			this.vyl = vy;
			sl.setVelocityY(vy);
		}else if(Sprite.equalsIgnoreCase("up")){
			this.vyu = vy;
			su.setVelocityY(vy);
		}else if(Sprite.equalsIgnoreCase("down")){
			this.vyd = vy;
			sd.setVelocityY(vy);
		}
	}
	
	public int getWidth(){
		return ir.getWidth(null);
	}
	public int getHeight(){
		return ir.getHeight(null);
	}
	
	public void update(long timePassed){
		if(this.dead){
			return;
		}
		if(currentimage == "Left"){
			sl.update(timePassed);
			this.x = sl.getX();
			this.y = sl.getY();
			il = sl.getImage();
		}else if(currentimage == "Right"){
			sr.update(timePassed);
			this.x = sr.getX();
			this.y = sr.getY();
			ir = sr.getImage();
		}else if(currentimage == "Up"){
			su.update(timePassed);
			this.x = su.getX();
			this.y = su.getY();
			iu = su.getImage();
		}else if(currentimage == "Down"){
			sd.update(timePassed);
			this.x = sd.getX();
			this.y = sd.getY();
			id = sd.getImage();
		}
		sl.setX((float)this.x);
		sr.setX((float)this.x);
		su.setX((float)this.x);
		sd.setX((float)this.x);
		
		sl.setY((float)this.y);
		sr.setY((float)this.y);
		su.setY((float)this.y);
		sd.setY((float)this.y);
	}
	
	public void setMoving(Boolean b){
		moving = b;
	}
	public boolean getMoving(){
		return moving;
	}
	
	public void loseHealt(int lose, long time){
		if(time-this.deadtime>2000){
			this.lives = this.lives - lose;
			if(this.lives <= 0){
				this.dead = true;
			}
			this.deadtime = time;
		}
	}
	public void gainHealt(int gain){
		this.lives += gain;
	}
	public int getHealt(){
		return this.lives;
	}
	
	public void updateHealt(double x){
		
	}
	
	public void toggleAttackOn(){
		if(attacking != true){
			attacking = true;
		}
	}
	
	public void toggleAttackOff(){
		if(attacking != false){
			attacking = false;
		}
	}
	
}
