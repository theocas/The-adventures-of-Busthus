import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Gui extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Game g;
	private JButton but;
	public boolean running = true;
	
	public Gui(Game g){
		super("tutle");
		this.g = g;
		setLayout(new FlowLayout());
		but = new JButton("Start The Adventures Of Busthus");
		add(but);
		
		HandlerClass handler = new HandlerClass();
		but.addActionListener(handler);
	
	}

	private class HandlerClass implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			running = false;
			setVisible(false);
			dispose();
		}
		
	}
	
}
