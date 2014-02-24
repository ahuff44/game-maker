package frogger;

import java.awt.Image;
import java.awt.Point;

import gameMaker.*;

public class Road extends GameObject{

	private static final Image roadImg = GraphicsController.getImage("road.png");

	private Alarm carAlarm;
	
	public Road(Point p){
		super(false, p, new Sprite(roadImg, 100));
	}
	
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
		carAlarm = new Alarm(this) {
			
			@Override
			public void run(){
				createCar();
				reset();
			}
			
		};
		AlarmController.setAlarm(carAlarm, 6);	
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
	
	private void createCar() {
		int x = Utilities.choose(new int[] {0, 480});
		int y = ((int) (Math.random() * 8)) * 32;

		int dir = 0;
		if (x == 480)
			dir = 180;
		
		Motion carMotion = new Motion(getMotion().relativePoint(x, y));
		carMotion.setDirection(dir);
		new Car(carMotion);
	}

}
