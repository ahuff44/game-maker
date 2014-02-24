package spaceGame;

import java.awt.Image;
import java.awt.Point;

import gameMaker.*;

public class Explosion extends GameObject implements HasAlarmEvent{

	private static final Image explosionImg = GraphicsController.getImage("explosion.png");

	public Explosion(Point position) {
		super(true, position, new Sprite(explosionImg, new Point(32, 32)));
	}
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}

	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Enemy)
			other.destroy();
	}

	@Override
	public void createEvent() {
		Alarm destroy = new Alarm(this){

			@Override
			public void run() {
				destroyAlarm();
				destroy();
			}
			
		};
		AlarmController.setAlarm(destroy, 45);//turn on the stream of bullets
	}

	protected void destroyAlarm() {
		destroy();
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

}
