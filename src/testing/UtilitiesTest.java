package testing;

import java.awt.Point;
import java.util.ArrayList;

import gameMaker.*;

public class UtilitiesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point c = new Point(0, 0);
		Point n = new Point(0, -1);
		Point e = new Point(1, 0);
		Point s = new Point(0, 1);
		Point w = new Point(-1, 0);
		ArrayList<String> results = new ArrayList<String>();
		for (int i = -10; i <= 10; i++)
			results.add("mod("+i+", 4): " + Utilities.mod(i, 4));
		results.add("");
		for (int i = -10; i <= 10; i++)
			results.add("numBounds("+i+", -5, 5): " + Utilities.numBounds(i, -5, 5));
		results.add("");
		results.add("pointDistance(c, n): " + Utilities.pointDistance(c, n));
		results.add("pointDistance(c, e): " + Utilities.pointDistance(c, e));
		results.add("pointDistance(c, s): " + Utilities.pointDistance(c, s));
		results.add("pointDistance(c, w): " + Utilities.pointDistance(c, w));
		results.add("pointDistance(n, n): " + Utilities.pointDistance(n, n));
		results.add("pointDistance(n, w): " + Utilities.pointDistance(n, w));
		results.add("pointDistance(n, e): " + Utilities.pointDistance(n, e));
		results.add("pointDistance(n, s): " + Utilities.pointDistance(n, s));
		results.add("");
		results.add("pointDirection(c, n): " + Utilities.pointDirection(c, n));
		results.add("pointDirection(c, e): " + Utilities.pointDirection(c, e));
		results.add("pointDirection(c, s): " + Utilities.pointDirection(c, s));
		results.add("pointDirection(c, w): " + Utilities.pointDirection(c, w));
		results.add("pointDirection(n, n): " + Utilities.pointDirection(n, n));
		results.add("pointDirection(n, w): " + Utilities.pointDirection(n, w));
		results.add("pointDirection(n, e): " + Utilities.pointDirection(n, e));
		results.add("pointDirection(n, s): " + Utilities.pointDirection(n, s));

		for(String str : results)
			System.out.println(str);

	}

}
