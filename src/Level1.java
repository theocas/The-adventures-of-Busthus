import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;



public class Level1 extends Level{

	public Level1(Game g) {
		super(g);
		bg = new ImageIcon(Main.class.getResource("images/bg.png")).getImage();
	}

	@Override
	public void houseCollideCheck() {
		// TODO Auto-generated method stub
		
	}
}