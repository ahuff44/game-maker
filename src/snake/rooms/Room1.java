package snake.rooms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import gameMaker.visuals.GraphicsController;
import snake.*;

public class Room1 implements Room {

	private static final BackgroundType bType = BackgroundType.TILED;
	private static final Image backgroundImage = GraphicsController.getImage("grass.png");
	
	@Override
	public Color backgroundColor() {
		return Color.MAGENTA;
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
		Point snakePt = new Point(80, 160);
		Body tail = new Body(snakePt);
		new Snake(snakePt, tail);
	}

	@Override
	public Dimension getSize() {
		return new Dimension(320, 320);
	}

	@Override
	public Dimension getGrid() {
		return new Dimension(16, 16);
	}

	@Override
	public BackgroundType backgroundType() {
		return bType;
	}

}
