package gameMaker.objects.eventInterfaces;

import gameMaker.utilities.intuitiveKeyListener.IntuitiveKeyListener;

import java.awt.event.KeyEvent;

/**
 * @see IntuitiveKeyListener
 * @author Andrew
 */
public interface HasIntuitiveKeyListener {

	/**
	 * This event is fired when a key is first pressed.
	 * @see testing.KeyEventTester.java
	 * @param event
	 */
	public void keyPressed(KeyEvent event);
	
	/**
	 * This event is fired repeatedly while a key is being held down.
	 * @see testing.KeyEventTester.java
	 * @param event
	 */
	public void keyDown(KeyEvent event);
	
	/**
	 * This event is fired when a key is released.
	 * @see testing.KeyEventTester.java
	 * @param event
	 */
	public void keyReleased(KeyEvent event);
	
}
