package space;


import java.awt.*;

import gameMaker.*;
import gameMaker.objects.GameObject;
import gameMaker.visuals.GraphicsController;


public class FighterLaser extends Laser {
	
	private static final Image fighterLaserImg = GraphicsController.getImage("fighter laser.png");

	public FighterLaser(Point p){
		super(p, fighterLaserImg);
	}
	
	
	//Overridden methods
	

	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {EnemyLaser.class};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof EnemyLaser){
			destroy();
			other.destroy();
			new Explosion(getMotion().getPosition());
		}
		else if (other instanceof Enemy){
			destroy();
			other.destroy();
		}
	}

	@Override
	public void createEvent() {
		super.createEvent();
		getMotion().setVspeed(-10);
	}
	
}
