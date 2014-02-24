package space;

import java.awt.*;

import gameMaker.*;

public abstract class Ship extends GameObject implements HasAlarmEvent{
	
	protected AlarmController alarmController;
	
	public Ship(Point p, Image img){
		super(true, p, new Sprite(img, new Point(16, 16)));
		System.out.println("creating " + this);
	}
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void createEvent() {
		GameController.debugln(this + ".createEvent()");
	}
	
	@Override
	public void intersectBoundaryEvent() {
		getMotion().gotoPreviousPosition();
	}
	
	@Override
	public void outsideRoomEvent() {
		GameController.debugln(this + " is outside the room.");
		destroy();
	}
	
	@Override
	public void destroyEvent() {
		new Explosion(getMotion().getPosition());
	}
	
}
