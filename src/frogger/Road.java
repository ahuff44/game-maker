package frogger;

import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import gameMaker.objects.AlarmController;
import gameMaker.objects.GameObject;
import gameMaker.objects.Motion;
import gameMaker.objects.eventInterfaces.HasAlarmEvent;
import gameMaker.utilities.MiscUtilities;
import gameMaker.visuals.GraphicsController;
import gameMaker.visuals.Sprite;

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
	public void collisionEvent(GameObject other) {}
	
	@Override
	public void createEvent() {
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 10); //car creation
	}
	
	
	@Override
	public AlarmController getAlarmController(){
		return alarmController;
	}
	
	
	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0://create a car
			createCar();
			alarmController.resetAlarm(alarmId);
			break;
		}
	}
	
	
	@Override
	public void destroyEvent() {}

	@Override
	public void intersectBoundaryEvent() {}

	@Override
	public void outsideRoomEvent() {}
	
	private void createCar() {
		int x, dir;
		int y = ((int) (Math.random() * 4)) * 64;
		int odd_lane = MiscUtilities.choose(new int[] {1, 0});
		if (odd_lane == 1){
			x = 480;
			dir = 180;
			y += 32;
		} else {
			x = 0;
			dir = 0;
		}
		Point creationPoint = getMotion().relativePoint(x, y);
		Motion carMotion = new Motion(creationPoint);
		carMotion.setDirection(dir);
		new Car(carMotion, odd_lane==1);
	}

}
