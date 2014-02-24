package space;

import java.awt.*;
import gameMaker.*;


public class EnemyLaser extends Laser {
	
	private static final Image enemyLaserImg = GraphicsController.getImage("enemy laser.png");

	public EnemyLaser(Point p){
		super(p, enemyLaserImg);
	}
	
	
	//Overridden methods
	
	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}
	
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
