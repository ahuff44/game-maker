package space;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

import gameMaker.*;
import gameMaker.objects.GameObject;
import gameMaker.objects.eventInterfaces.HasKeyEvent;
import gameMaker.objects.eventInterfaces.HasMouseEvent;
import gameMaker.visuals.GraphicsController;

public class ClickTest extends GameObject implements HasMouseEvent, HasKeyEvent{

	private static final Image testImg = GraphicsController.getImage("test sprite.png");

	private boolean go = false;
	
	public ClickTest(Point p){
		super(true, p, testImg);
	}
		
	@Override
	public void mouseEvent(MouseEvent ev) {
		if (go)
			System.out.println("   Hierarchy point: " + ev.getPoint());
	}

	@Override
	public void keyPressed(Integer code){
		go = !go;
		System.out.println("    point showing enabled: " + go);
	}
	
	
	//unused:

	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}

	@Override
	public void collisionEvent(GameObject other) {}

	@Override
	public void createEvent() {}

	@Override
	public void destroyEvent() {}

	@Override
	public void intersectBoundaryEvent() {}
	
	@Override
	public void outsideRoomEvent() {}

	@Override
	public void keyDown(Integer code) {}

	@Override
	public void keyReleased(Integer code) {}

}
