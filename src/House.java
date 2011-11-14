import java.awt.Image;


public class House {
	Image img;
	int[] x;
	int[] y;
	public House(int[] x, int[] y){
		this.x = x;
		this.y = y;
	}
	
	public boolean containsPoint(int x, int y){
		for(int count = 0;count<this.x.length;count++){
			if(x == this.x[count] && y == this.y[count]){
				return true;
			}
		}
		return false;
	}
	
	
	
}
