package spaceGame;

import java.awt.Image;
import java.awt.Point;

import gameMaker.*;

public class Explosion extends GameObject implements HasAlarmEvent{

	private static final Image explosionImg = GraphicsController.getImage("explosion.png");
	private AlarmController alarmController;
	
	public Explosion(Point position) {
		super(true, position, new Sprite(explosionImg, new Point(32, 32)));
	}
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Enemy.class, Fighter.class};
		return (Class<? extends GameObject>[]) list;
	}

	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Enemy || other instanceof Fighter){
			other.destroy();
		}
	}

	@Override
	public void createEvent() {
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 10); // destroy
	}

	@Override
	public void destroyEvent() { }

	@Override
	public void intersectBoundaryEvent() { }

	@Override
	public void outsideRoomEvent() { }
	
	
	@Override
	public AlarmController getAlarmController() {
		return alarmController;
	}

	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0: // destroy
			destroy();
			break;
		}
	}


}
