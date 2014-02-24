package gameMaker;

import java.awt.*;

/**
 * Rooms really should be static classes. But the interface syntax was yelling at me. :(
 * TODO make this an abstract class?
 * @author Andrew
 *
 */
public interface Room {

	static enum BackgroundType {TILED, SCALED, NEITHER};

	/**
	 * Here you would load all of the objects in this room and execute any code.
	 */
	void load();
	
	/**
	 * Here you would execute clean-up operations to close this room before it is destroyed.
	 */
	void close();
	
	/**
	 * @return the Dimension of this room
	 */
	Dimension getSize();
	
	/**
	 * @return the Dimension of the grid of this room
	 */
	Dimension getGrid();

	/**
	 * @return the desired background color for this room, or null if no color is specified
	 */
	Color backgroundColor();
	
	/**
	 * @return the desired background color for this room, or null if no color is specified
	 */
	Image backgroundImage();

	/**
	 * @return what type of transformation should be applied to the background image
	 */
	BackgroundType backgroundType();
	
}
