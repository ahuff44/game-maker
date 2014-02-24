package gameMaker.utilities;

import gameMaker.GameController;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MiscUtilities {

	private MiscUtilities(){
		//prevents user from creating an instance of MiscUtilities
	}
	
	/**
	 * Chooses a random point within a Rectangle
	 * 
	 * @param rect
	 * @return A random point within the given rectangle
	 */
	public static Point randomPoint(Rectangle rect){
		int x = (int) (Math.random() * (rect.width - 64) + 32);
		int y = (int) (Math.random() * (rect.height - 64) + 32);
		return new Point(x, y);
	}
	/**
	 * Returns the distance in pixels from a to b.
	 * @param a the first point
	 * @param b the second point
	 * @return the distance in pixels from a to b
	 */
	public static double pointDistance(Point a, Point b){
		return Math.hypot(a.x - b.x, b.y - a.y);
	}

	/**
	 * Returns the direction in degrees from a to b.
	 * @param a the first point
	 * @param b the second point
	 * @return the direction in degrees from a to b
	 */
	public static double pointDirection(Point a, Point b){
		return Math.toDegrees( Math.atan2(a.y - b.y, b.x - a.x) );//this is (a-b, b-a) because of the orientation of java's coordinate system
	}

	/**
	 * This is a custom mod (%) function that works for negative numbers
	 */
	public static double mod(double num, int mod) {
		if (num < 0)
			num = mod - ((-num) % mod);
		return num % mod;
	}

	/**
	 * Basically: 
	 * if (num < low)
	 * <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;return low;
	 * <br>
	 * if (num > high)
	 * <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;return high;
	 * <br>
	 * return num;
	 * @param num the number to put in bounds
	 * @param low the lower bound
	 * @param high the higher bound
	 * @return num kept with the bounds of low and high
	 */
	public static double numBounds(double num, double low, double high) {
		return Math.min(Math.max(num, low), high);
	}

	public static <T> T choose(ArrayList<T> list){
		return list.get((int)(Math.random() * list.size()));
	}
	
	public static int choose(int[] list){
		return list[(int)(Math.random() * list.length)];
	}

	public static double choose(double[] list){
		return list[(int)(Math.random() * list.length)];
	}
	
	/*public static Object choose(Object... list){
		return list[(int)(Math.random() * list.length)];
	}*/
	
	public static int mouseX(){
		return mousePosition().x;
	}
	
	public static int mouseY(){
		return mousePosition().y;
	}
	
	public static Point mousePosition(){
		return GameController.getMainGame().getMousePosition();
	}

	public static void drawCircle(Graphics g, int x, int y, int r) {
		g.drawOval(x - r, y - r, r * 2, r * 2);
	}
	
	public static void fillCircle(Graphics g, int x, int y, int r) {
		g.fillOval(x - r, y - r, r * 2, r * 2);
	}

	public static void drawDot(Graphics g, Point pos) {
		MiscUtilities.fillCircle(g, pos.x, pos.y, 2);
		MiscUtilities.drawCircle(g, pos.x, pos.y, 5);		
		MiscUtilities.drawCircle(g, pos.x, pos.y, 6);
	}

	public static void drawGrid(Graphics g){
		int w = GameController.getRoom().getSize().width;
		int h = GameController.getRoom().getSize().height;
		int gw = GameController.getRoom().getGrid().width;
		int gh = GameController.getRoom().getGrid().height;
		for(int x = 0; x <= w; x += gw)
			g.drawLine(x, 0, x, h);
		for(int y = 0; y <= h; y += gh)
			g.drawLine(0, y, w, y);
	}

	public static boolean isSnappedToGrid(Point position) {
		return isSnappedToGrid(position, GameController.getRoom().getGrid());
	}

	public static boolean isSnappedToGrid(Point position, Dimension dimension) {
		double a = ((double)position.x) / dimension.width;
		double b = ((double)position.y) / dimension.height;
		return ((a == (int) a) && (b == (int) b));
	}
	

	//   TODO Motion needs to be more careful and user .clone() MUCH more
	public static Point snapToGrid(Point position) {
		return snapToGrid(position, GameController.getRoom().getGrid());
	}
	public static Point snapToGrid(Point position, Dimension dimension) {
		int new_x, new_y;
		new_x = (int)(((int)(position.x / dimension.getWidth())) * dimension.getWidth());
		new_y = (int)(((int)(position.y / dimension.getHeight())) * dimension.getHeight());
		return new Point(new_x, new_y);
	}
	
	public static boolean isInsideRoom(Point p){
		Dimension d = GameController.getRoom().getSize();
		return (new Rectangle(d)).contains(p);
	}
		
}
