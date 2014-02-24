package space.rooms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import space.*;

public class Room1 implements Room {

	private static final BackgroundType bType = BackgroundType.SCALED;
	private static final Image backgroundImage = GraphicsController.getImage("spaceBackground.png");
	
	@Override
	public Color backgroundColor() {
		return Color.GREEN;
	}

	@Override
	public Image backgroundImage() {
		return backgroundImage;
	}

	@Override
	public void close() {
		System.out.println(this + " is closing.");
	}
	
	@Override
	public void load() {
		new SpaceGameControllerObject();
		new Fighter(new Point(320, 438));
		int[][] coords = {{1,1},
						  {7,1},
						  {13,1},
						  {19,1},
						  {6,2},
						  {11,2},
						  {14,2},
						  {8,3},
						  {17,3},
						  {14,3}};
		for (int[] coord : coords){
			new Enemy(new Point(32 * coord[0], 32 * coord[1]));
		}
	}

	@Override
	public Dimension getSize(){
		return new Dimension(640, 480);
	}
	
	@Override
	public Dimension getGrid() {
		return new Dimension(32, 32);
	}
	
	@Override
	public BackgroundType backgroundType(){
		return bType;
	}

}
