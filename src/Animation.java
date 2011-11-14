import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private ArrayList<OneScene> scenes;
	private int sceneindex;
	private long movieTime;
	private long totalTime;
	
	//CONSTRUCTOR
	public Animation(){
		scenes = new ArrayList<OneScene>();
		totalTime=0;
		start();
	}
	
	//addSene to ArrayList and set time for each scene
	public synchronized void addScene(Image i,long t){
		totalTime+=t;
		scenes.add(new OneScene(i, totalTime));
	}
	
	//start animation from begining
	public synchronized void start(){
		movieTime = 0;
		sceneindex = 0;
	}
	
	//change scenes
	public synchronized void update(long timePassed){
		if(scenes.size()>1){
			movieTime += timePassed;
			if(movieTime>=totalTime){
				movieTime = 0;
				sceneindex = 0;
			}
			while(movieTime > getScene(sceneindex).endTime){
				sceneindex++;
			}
		}
	}
	
	//get animations current scene
	public synchronized Image getImage(){
		if(scenes.size()==0){
			return null;
		}else{
			return getScene(sceneindex).pic;
		}
	}
	
	//get scene
	private OneScene getScene(int x){
		return (OneScene)scenes.get(x);
	}
	
////////////////////////////////PRIVATE INNER CLASS///////////////////////
	private class OneScene{
		Image pic;
		long endTime;
		
		//CONSTRUCTOR
		public OneScene(Image pic, long endTime){
			this.pic = pic;
			this.endTime=endTime;
		}
	}
	
}