package space;

import java.awt.Image;
import java.awt.Point;

import gameMaker.*;

public class Explosion extends GameObject implements HasAlarmEvent{

	private static final Image img_1 = GraphicsController.getImage("explosion\\1.png");
	private static final Image img_2 = GraphicsController.getImage("explosion\\2.png");
	private static final Image img_3 = GraphicsController.getImage("explosion\\3.png");
	private static final Image img_4 = GraphicsController.getImage("explosion\\4.png");
	private static final Image img_5 = GraphicsController.getImage("explosion\\5.png");
	private static final Image img_6 = GraphicsController.getImage("explosion\\6.png");

	private AlarmController alarmController;
	private final int LIFE = 10; // how many steps until it calls destroy() on itself
	
	public Explosion(Point position) {
		super(true, position, new Animation(new Image[] {
				img_1, img_2, img_1, img_2, img_3, img_4, img_3, img_4, img_5, img_6
		}));
		getSprite().setCenter(new Point(32, 32));
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
		alarmController.setAlarm(0, LIFE); // destroy
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
