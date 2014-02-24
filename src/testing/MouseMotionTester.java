package testing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MouseMotionTester extends JFrame implements MouseMotionListener {

	public MouseMotionTester(){
		addMouseMotionListener(this);
		setScreenSettings();
	}
	
	/*
	 * Copy and paste this generic method into another class to make a basic JFrame window
	 */
	public void setScreenSettings() {
		final int WIDTH = 640;
		final int HEIGHT = 480;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screen.width - WIDTH) / 2,
				  (screen.height - HEIGHT) / 2,
				  WIDTH + 4 + 4,
				  HEIGHT + 4 + 4 + 20);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Dragged: " + e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("Moved: " + e.getPoint());
	}
	
	public static void main(String[] args){
		(new MouseMotionTester()).setVisible(true);
	}

}
