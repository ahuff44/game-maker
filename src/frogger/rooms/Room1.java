package frogger.rooms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import frogger.*;

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
		new Frog(new Point(32 * 8, 32 * 19));
		new Road(new Point(0, 32 * 10));
	}

	@Override
	public Dimension getSize() {
		return new Dimension(32 * 15, 32 * 20);
	}

	@Override
	public Dimension getGrid() {
		return new Dimension(32, 32);
	}

	@Override
	public BackgroundType backgroundType() {
		return bType;
	}

}
