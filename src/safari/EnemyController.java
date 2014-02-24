package safari;

import java.awt.*;
import java.awt.event.KeyEvent;

import gameMaker.*;

public class EnemyController extends GameObject implements HasAlarmEvent, HasKeyEvent{

	private static final String SPRITE_ADDRESS = null;
	
	private AlarmController alarmController;
	private static int score;
	
	public EnemyController() {
		super(false, new Point(0, 0));
		score = 0;
	}
		
	public void createEvent() {
		alarmController = new AlarmController(this);
		alarmController.setAlarm(0, 50);
	}
	
	
	public AlarmController getAlarmController(){
		return alarmController;
	}


	
	public void alarmEvent(int alarmId) {
		switch (alarmId){
		case 0: // spawn enemies
			int numTigers = Utilities.choose(new int[] {3,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			int numBirds = Utilities.choose(new int[] {3,3,2,1,1,1,1,1,0});
			for (int ii = 0; ii < numTigers; ++ii){
				new Tiger(Utilities.randomPoint(GameController.getRoomRectangle()));
			}
			for (int ii = 0; ii < numBirds; ++ii){
				new Bird(Utilities.randomPoint(GameController.getRoomRectangle()));
			}
			int timeUntilNextSpawn = alarmController.getLastValue(alarmId) - 1; // The enemies come quicker each time!
			if (timeUntilNextSpawn < 5){
				timeUntilNextSpawn = 5;
			}
			alarmController.setAlarm(0, timeUntilNextSpawn);
			break;
		}
	}

	public void keyPressed(Integer code){
		switch (code){
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_ENTER: // restart the game
			GameController.restart();
			break;
		}
	}

	public void draw(Graphics g){
		g.drawString("Score: " + score, 10, 24);
	}
	
	public static void changeScore(int sChange){
		score += sChange;
	}
	
	
	
	//unused:
	
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}

	public void collisionEvent(GameObject other) {}
	public void destroyEvent() {}
	public void intersectBoundaryEvent() {}
	public void outsideRoomEvent() {}
	public void keyDown(Integer code) {}
	public void keyReleased(Integer code) {}

}
