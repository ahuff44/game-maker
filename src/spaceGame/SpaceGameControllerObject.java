package spaceGame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import gameMaker.*;


public class SpaceGameControllerObject extends GameObject implements HasKeyEvent{
	
	
	
	//fields
	
	
	
	private static final String SPRITE_ADDRESS = null;

	
	
	//constructors
	
	
	public SpaceGameControllerObject(){
		super();
	}

	
	
	//Overridden methods
	
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}

	@Override
	public void collisionEvent(GameObject other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createEvent() {
		GameController.debugln("SpaceGameControllerObject.createEvent");
	}

	@Override
	public void destroyEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void intersectBoundaryEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void outsideRoomEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(Integer code){
		switch (code){
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_ENTER://restart the game
			GameController.restart();
			break;
		}
	}

	//unused:
	
	@Override
	public void keyDown(Integer code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(Integer code) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) { }

}