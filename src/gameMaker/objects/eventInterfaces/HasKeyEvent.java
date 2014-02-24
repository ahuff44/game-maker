package gameMaker.objects.eventInterfaces;

import gameMaker.utilities.intuitiveKeyListener.IntuitiveKeyListener;

/**
 * @see IntuitiveKeyListener
 * @author Andrew
 */
public interface HasKeyEvent {
	
	public void keyPressed(Integer keyCode);
	public void keyDown(Integer keyCode);
	public void keyReleased(Integer keyCode);
	
}
