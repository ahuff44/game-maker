package space;

import java.awt.*;
import gameMaker.*;


public class Enemy extends Ship{
	
	
	//fields
	
	
	private static final Image enemyImg = GraphicsController.getImage("enemy.png");

	
	//constructors
	
	
	public Enemy(Point p){
		super(p, enemyImg);
	}
	
	private void shoot(){
		new EnemyLaser(getMotion().getPosition());
	}
	

	//Overridden methods
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Enemy.class};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Enemy){
			//System.out.println("en-en coll");
			//System.out.println("pos: "+getPosition() + "\nprevpos: " + getPreviousPosition());
			getMotion().gotoPreviousPosition();
			getMotion().scaleHspeed(-1);
		}
	}

	@Override
	public void createEvent(){
		super.createEvent();
		int[] speeds = {-3, 3};
		getMotion().setHspeed(Utilities.choose(speeds));
		
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 35+Utilities.choose(new int[] {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5}));//shoot
	}
	
	@Override
	public void intersectBoundaryEvent() {
		super.intersectBoundaryEvent();
		getMotion().scaleHspeed(-1);
	}
	

	@Override
	public AlarmController getAlarmController() {
		return alarmController;
	}

	@Override
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0: //shoot
			shoot();
			alarmController.resetAlarm(alarmId);
			break;
		}
	}
	
}
