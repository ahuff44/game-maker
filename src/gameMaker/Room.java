package gameMaker;

import java.awt.*;

/**
 * Basically, a Room represents one screen of gameplay.
 * The concept of Room s hasn't really been fleshed out in the project yet, so it might not make a lot of sense to you.
 * If you've used YoyoGames' GameMaker, then I can tell you that my Rooms are based off their rooms
 * 
 * TODO make this an base class, and enforce it so that Rooms must be singletons
 * @author Andrew
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
