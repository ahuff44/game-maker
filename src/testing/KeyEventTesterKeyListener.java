package testing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;


public class KeyEventTesterKeyListener extends JComponent implements KeyListener {
	
	private ArrayList<Integer> currentlyPressed;
	
	public KeyEventTesterKeyListener(){
		currentlyPressed = new ArrayList<Integer>();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		Integer code = arg0.getKeyCode();
		if (currentlyPressed.contains(code))
			return;
		currentlyPressed.add(code);
		System.out.println(code + " pressed for reals");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		Integer code = arg0.getKeyCode();
		currentlyPressed.remove(code);
		System.out.println(code + " released for reals");
	}

	@Override public void keyTyped(KeyEvent arg0) { }

}
