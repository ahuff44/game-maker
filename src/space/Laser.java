package space;

import java.awt.*;

import gameMaker.*;
import gameMaker.objects.GameObject;
import gameMaker.visuals.Sprite;

public abstract class Laser extends GameObject{
	
	public Laser(Point p, Image img){
		super(true, p, new Sprite(img, new Point(3, 16)));
	}
	
	
	// Overridden methods:
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Ship.class};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void createEvent() {
		GameController.debugln(this + ".createEvent()");
	}
	

	@Override
	public void destroyEvent() {
		GameController.debugln(this + ".destroyEvent()");
	}
	
	@Override
	public void outsideRoomEvent() {
		destroy();
	}	

	@Override
	public void intersectBoundaryEvent() { }
	
}
