package space;

import gameMaker.*;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;


public class Fighter extends Ship implements HasKeyEvent{
	
	
	//fields

	
	private static final Image fighterImg = GraphicsController.getImage("fighter.png");
	private AlarmController alarmController;
	
	
	//constructors
	
	
	public Fighter(Point p){
		super(p, fighterImg);
		alarmController = new AlarmController(this);
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
	public void keyPressed(Integer code){
		switch (code){
		case KeyEvent.VK_SPACE:
			shoot();
			alarmController.setAlarm(0, 10);//turn on the stream of bullets
			break;
		}
	}
	
	@Override
	public void keyDown(Integer code){
		switch (code){
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			getMotion().changeX(-5);
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			getMotion().changeX(5);
			break;
		}
	}
	
	@Override
	public void keyReleased(Integer code){
		switch (code){
		case KeyEvent.VK_SPACE:
			alarmController.setAlarm(0, 0);//turn off the stream of bullets
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
		case 0: // turn on the stream of bullets
			shoot();
			alarmController.resetAlarm(alarmId);
			break;
		}
	}
}
