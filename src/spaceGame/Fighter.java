package spaceGame;

import gameMaker.*;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;


public class Fighter extends Ship implements HasKeyEvent{
	
	
	
	//fields
	

	
	private static final Image fighterImg = GraphicsController.getImage("fighter.png");
	
	private Alarm shoot;
	
	
	
	//constructors
	
	
	
	public Fighter(Point p){
		super(p, fighterImg);
		shoot = new Alarm(this){

			@Override
			public void run() {
				shoot();
				reset();
			}
			
		};
	}
	
	
	
	//Overridden methods
	
	

	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof ClickTest){
			getMotion().gotoPreviousPosition();
		}
	}	

	public void keyPressed(Integer code){
		switch (code){
		case KeyEvent.VK_SPACE:
			shoot();
			AlarmController.setAlarm(shoot, 10);//turn on the stream of bullets
			break;
		}
	}
	
	public void keyDown(Integer code){
		switch (code){
		case KeyEvent.VK_LEFT:
			getMotion().changeX(-5);
			break;
		case KeyEvent.VK_RIGHT:
			getMotion().changeX(5);
			break;
		}
	}
	
	public void keyReleased(Integer code){
		switch (code){
		case KeyEvent.VK_SPACE:
			AlarmController.setAlarm(shoot, 0);//turn off the stream of bullets
			break;
		}		
	}
	
	private void shoot() {
		new FighterLaser(getMotion().getPosition());
	}
	
}
