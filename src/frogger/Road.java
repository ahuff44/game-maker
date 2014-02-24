package frogger;

import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import huntingGame.Bird;
import huntingGame.Tiger;

public class Road extends GameObject implements HasAlarmEvent{

	private static final Image roadImg = GraphicsController.getImage("road.png");

	private AlarmController alarmController;
	
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
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 6); //car creation
	}
	
	
	public AlarmController getAlarmController(){
		return alarmController;
	}
	
	
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0://create a car
			createCar();
			alarmController.resetAlarm(alarmId);
			break;
		}
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
