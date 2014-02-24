package space;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import gameMaker.*;


public class SpaceGameControllerObject extends GameObject implements HasKeyEvent{
	
	
	//constructors
	
	
	public SpaceGameControllerObject(){
		super();
	}

	
	// Overridden methods:
	
	
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
	public void keyPressed(Integer code){
		switch (code){
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
//TODO make this work... I worked FOREVER on restart() fxnality and got nowhere...
//		case KeyEvent.VK_ENTER://restart the game
//			GameController.restart();
//			break;
		}
	}
	
	@Override
	public void draw(Graphics g) { } // don't delete this; this object CANNOT draw anything since it has no Sprite

	//unused:
	
	@Override
	public void keyDown(Integer code) {}

	@Override
	public void keyReleased(Integer code) {}
		
	@Override
	public void destroyEvent() {}

	@Override
	public void intersectBoundaryEvent() {}

	@Override
	public void outsideRoomEvent() {}

}
