package gameMaker;

import java.util.ArrayList;

//static class
public class KeyEventHandler {	
	
	private static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
	private static ArrayList<Integer> keysDown = new ArrayList<Integer>();
	private static ArrayList<Integer> keysReleased = new ArrayList<Integer>();
	
	/**
	 * This will be called as part of the background processing.
	 * @param e
	 */
	public static void receiveKeyEvent(ModifiedKeyEvent mke) {
		switch (mke.type){
		case KEY_PRESSED:
			keysPressed.add(mke.keyCode);
			break;
		case KEY_RELEASED:
			keysReleased.add(mke.keyCode);
			keysDown.remove(mke.keyCode);
			break;
		}
	}
	
	/**
	 * Acts on all of the key events that have been fired in the last step.
	 * This will be called as part of the background processing.
	 */
	public static void performEvents(ArrayList<HasKeyEvent> objects){
		for (Integer code : keysPressed)
			for (HasKeyEvent obj : objects)
				obj.keyPressed(code);
		for (Integer code : keysDown)
			for (HasKeyEvent obj : objects)
				obj.keyDown(code);
		for (Integer code : keysReleased)
			for (HasKeyEvent obj : objects)
				obj.keyReleased(code);
		
		for (Integer code : keysPressed)//transfer keyPressed events to keyDown
			keysDown.add(code);
		keysPressed.clear();
		
		keysReleased.clear();
	}
	
}
