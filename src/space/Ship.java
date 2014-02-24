package space;

import java.awt.*;

import gameMaker.*;
import gameMaker.objects.AlarmController;
import gameMaker.objects.GameObject;
import gameMaker.objects.eventInterfaces.HasAlarmEvent;
import gameMaker.visuals.Animation;

public abstract class Ship extends GameObject implements HasAlarmEvent{
	
	protected AlarmController alarmController;
	
	
	// constructors
	
	
	public Ship(Point p, Image img){
		super(true, p, img);
		getSprite().setCenter(new Point(16, 16));
	}
	
	public Ship(Point p, Animation anim){
		super(true, p, anim);
		getSprite().setCenter(new Point(16, 16));
	}
	
	
	// Overridden methods:
	
	
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
