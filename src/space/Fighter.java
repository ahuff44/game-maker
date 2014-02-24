package space;

import gameMaker.*;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Fighter extends Ship implements HasKeyEvent, HasMouseEvent{
	
	
	//fields

    private static final Animation goingLeft = new Animation(GraphicsController.getImage("fighter\\goingLeft.png"));
    private static final Animation goingRight = new Animation(GraphicsController.getImage("fighter\\goingRight.png"));
    private static final Animation holdingStill = new Animation(GraphicsController.getImage("fighter\\holdingStill.png"));
	
	private AlarmController alarmController;
	
	
	//constructors
	
	
	public Fighter(Point p){
		super(p, holdingStill);
		alarmController = new AlarmController(this);
	}

	private void startShooting(){
		if (alarmController.getAlarm(0) <= 0){ // if the stream isn't already on
			shoot();
			alarmController.setAlarm(0, 8); // turn on the stream of bullets
		}
	}

	private void stopShooting(){
		alarmController.setAlarm(0, 0); // turn off the stream of bullets	
	}

	private void shoot() {
		new FighterLaser(getMotion().getPosition());
	}
	
	
	//Overridden methods
	

	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) { }	

	@Override
	public void keyDown(Integer code){
		switch (code){
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			getSprite().setAnimation(goingLeft);
			getMotion().changeX(-5);
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			getSprite().setAnimation(goingRight);
			getMotion().changeX(5);
			break;
		}
	}
	
	@Override
	public void keyPressed(Integer code){
		switch (code){
		case KeyEvent.VK_SPACE:
			startShooting();
			break;
		}
	}

	@Override
	public void keyReleased(Integer code){
		switch (code){
		case KeyEvent.VK_SPACE:
			stopShooting();
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			getSprite().setAnimation(holdingStill);
			break;
		}		
	}

	@Override
	public void mouseEvent(MouseEvent ev) {
		switch (ev.getID()){
		case MouseEvent.MOUSE_PRESSED:
			startShooting();
			break;
		case MouseEvent.MOUSE_RELEASED:
			stopShooting();
			break;
		}
	}

	@Override
	public AlarmController getAlarmController() {
		return alarmController;
	}

	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0: // shoot
			shoot();
			alarmController.resetAlarm(alarmId);
			break;
		}
	}
	
}
