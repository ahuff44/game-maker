package testing;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

//this needs a runner class to run and add this to it.
public class MouseEventTester extends JPanel{
	
	public MouseEventTester(){
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Clicked");
				Point p = arg0.getPoint();
				System.out.println("  Pos: " + p);
				System.out.println();
			}

			@Override public void mouseEntered(MouseEvent arg0) { System.out.println(" Entered"); }
			@Override public void mouseExited(MouseEvent arg0) { System.out.println(" Exited"); }
			@Override public void mousePressed(MouseEvent arg0) { System.out.println("Pressed"); }
			@Override public void mouseReleased(MouseEvent arg0) { System.out.println("Released"); }
			
		});
		
	}
}
