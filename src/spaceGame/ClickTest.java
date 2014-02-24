package spaceGame;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import gameMaker.*;

public class ClickTest extends GameObject implements HasMouseEvent, HasKeyEvent{

	private static final Image testImg = GraphicsController.getImage("test sprite.png");

	private boolean go = false;
	
	public ClickTest(Point p){
		super(true, p, testImg);
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
		// TODO Auto-generated method stub
		
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

	@Override
	public void keyDown(Integer code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(Integer code) {
		// TODO Auto-generated method stub
		
	}

}
