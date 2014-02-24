package gameMaker.utilities.intuitiveKeyListener;

import gameMaker.objects.eventInterfaces.HasIntuitiveKeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * I didn't like the output of the built-in java.awt.event.KeyListener, so I built this package around it so that it makes more sense to me.
 * In this setup, pressing the 'F' key will generate the following sequence of events:
 * 	-keyPressed- 'F'
 *  -keyDown- 'F'
 *  -keyDown- 'F'
 *  ...
 *  -keyDown- 'F'
 *  -keyDown- 'F'
 *  -keyReleased- 'F'
 *  
 * Note that with this listener, it is possible for no keyReleased event to be fired. For instance, if the window loses focus this may occur.
 * 
 * @author Andrew
 */
public class IntuitiveKeyListener implements KeyListener {

	private ArrayList<Integer> currentlyPressed;
	private HasIntuitiveKeyListener owner;
	
	public IntuitiveKeyListener(HasIntuitiveKeyListener owner){
		this.owner = owner;
		currentlyPressed = new ArrayList<Integer>();
	}	
	
	public void keyPressed(KeyEvent event) {
		Integer code = event.getKeyCode();
		if (!currentlyPressed.contains(code)){
			currentlyPressed.add(code);
			owner.keyPressed(event);//TODO think of a better name for this, and this class
		}
		owner.keyDown(event);//TODO think of a better name for this, and this class
	}

	public void keyReleased(KeyEvent event) {
		Integer code = event.getKeyCode();
		currentlyPressed.remove(code);
		owner.keyReleased(event);//TODO think of a better name for this, and this class
	}

	public void keyTyped(KeyEvent event) { }
	
}
