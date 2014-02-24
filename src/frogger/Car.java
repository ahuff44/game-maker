package frogger;

import java.awt.Image;

import gameMaker.*;

public class Car extends GameObject {

	private static final Image imgCarLeft = GraphicsController.getImage("carLeft.png");
	private static final Image imgCarRight = GraphicsController.getImage("carRight.png");

	public Car(Motion m, boolean goingLeft){
		super(true, m, new Sprite(goingLeft ? imgCarLeft : imgCarRight, 50));
	}
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {}
	
	@Override
	public void createEvent() {
		getMotion().setSpeed(8);
	}

	@Override
	public void destroyEvent() {}

	@Override
	public void intersectBoundaryEvent() {}

	@Override
	public void outsideRoomEvent() {
		destroy();
	}

}
