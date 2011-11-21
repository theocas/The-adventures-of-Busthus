import java.awt.*;

public abstract class Core {

	
	private static DisplayMode modes[] = {
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0),
	};
	public boolean running;
	ScreenManager s;
	
	public void stop(){
		running = false;
	}
	
	//call init and gameLoop
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			s.restoreScreen();
		}
	}
	
	//set fullscreen
	public void init(){
		s = new ScreenManager();
		DisplayMode dm = s.findFistCompatibleMode(modes);
		s.setFullScreen(dm);
		
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Lucida Console",Font.PLAIN,10));
		w.setBackground(Color.GREEN);
		w.setForeground(Color.BLACK);
		running = true;
	}
	
	//main gameLoop
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		long cumTime = startTime;
		long currtime = System.currentTimeMillis();
		
		while(running){
			long timePassed = System.currentTimeMillis() - cumTime;
			currtime = System.currentTimeMillis();
			cumTime +=timePassed;
			update(timePassed, currtime);
			
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	
	//update the animation
	public void update(long timePassed, long currtime){}
	
	//draws to the screen
	public abstract void draw(Graphics2D g);
	
}
