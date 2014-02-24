package gameMaker;

import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MouseEventHandler {
	
	private static ArrayList<MouseEvent> mouseEvents = new ArrayList<MouseEvent>();
	
	/**
	 * This will be called as part of the background processing.
	 * @param e
	 */
	public static void receiveMouseEvent(MouseEvent e) {
		mouseEvents.add(e);//This event will be acted on when the mouseEvent is called
	}

	/**
	 * Acts on all of the mouse events that have been fired in the last step.
	 * This will be called as part of the background processing.
	 */
	public static void performEvents(ArrayList<HasMouseEvent> objects){
		for (MouseEvent ev : mouseEvents)
			for (HasMouseEvent obj : objects)
				obj.mouseEvent(ev);
		mouseEvents.clear();
	}
	
}
