package spaceGame;


import java.awt.*;

import javax.swing.ImageIcon;
import gameMaker.*;


public class FighterLaser extends Laser {
	
	private static final Image fighterLaserImg = GraphicsController.getImage("fighter laser.png");

	public FighterLaser(Point p){
		super(p, fighterLaserImg);
	}
	
	
	
	//Overridden methods
	
	

	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Enemy){
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
