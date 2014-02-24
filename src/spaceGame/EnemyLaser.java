package spaceGame;


import java.awt.*;

import javax.swing.ImageIcon;
import gameMaker.*;


public class EnemyLaser extends Laser {
	
	private static final Image enemyLaserImg = GraphicsController.getImage("enemy laser.png");

	public EnemyLaser(Point p){
		super(p, enemyLaserImg);
	}
	
	
	
	//Overridden methods
	
	

	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Fighter){
			destroy();
			other.destroy();
		}
	}

	@Override
	public void createEvent() {
		super.createEvent();
		getMotion().setVspeed(10);
	}
	
}
