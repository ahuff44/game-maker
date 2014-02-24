package frogger;

import java.awt.Image;

import gameMaker.*;

public class Car extends GameObject {

	private static final Image carImg = GraphicsController.getImage("head.gif");

	public Car(Motion m){
		super(true, m, new Sprite(carImg, 50));
	}
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Car.class};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		//if (other instanceof Car)
		//	destroy();
	}

	@Override
	public void createEvent() {
		getMotion().setSpeed(8);
	}

	@Override
	public void destroyEvent() {
		// create an explosion
		
	}

	@Override
	public void intersectBoundaryEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outsideRoomEvent() {
		destroy();
	}

}
