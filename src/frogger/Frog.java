package frogger;

import java.awt.*;
import java.awt.event.KeyEvent;

import gameMaker.*;


public class Frog extends GameObject implements HasKeyEvent, HasAlarmEvent {
	
	
	//fields
	
	
	private static final Image frogImg = GraphicsController.getImage("frog.png");
	private AlarmController alarmController;
	private boolean canMove;
	
	
	//constructors
	

	public Frog(Point p){
		super(true, p, frogImg);
	}
	
	
	//Overridden methods

	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Car.class};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Car){
			destroy();
		}
	}

	@Override
	public void keyDown(Integer keyCode){
		Point p;
		switch (keyCode){
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			p = new Point(-32, 0);
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			p = new Point(32, 0);
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			p = new Point(0, -32);
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			p = new Point(0, 32);
			break;
		default: 
			return;
		}
		if (canMove && Utilities.isInsideRoom(getMotion().relativePoint(p))){
			getMotion().changePosition(p);
			canMove = false;
			alarmController.setAlarm(0, 10); //reset canMove
		}
	}
	
	@Override
	public void keyPressed(Integer keyCode) {}
	
	@Override
	public void keyReleased(Integer keyCode) {}
	
	@Override
	public void intersectBoundaryEvent() {
		getMotion().gotoPreviousPosition();
	}
	
	@Override
	public void outsideRoomEvent() {
		destroy();
	}
	
	@Override
	public void createEvent() {
		alarmController = new AlarmController(this);
		canMove = true;
	}
	
	
	@Override
	public AlarmController getAlarmController(){
		return alarmController;
	}
	
	
	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0:// reset canMove
			canMove = true;
			break;
		}
	}
	

	
	@Override
	public void destroyEvent() {}
		
}
