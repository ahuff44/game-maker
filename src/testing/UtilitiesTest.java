package testing;

import java.awt.Point;
import java.util.ArrayList;

import gameMaker.*;
import gameMaker.utilities.MiscUtilities;

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
			results.add("mod("+i+", 4): " + MiscUtilities.mod(i, 4));
		results.add("");
		for (int i = -10; i <= 10; i++)
			results.add("numBounds("+i+", -5, 5): " + MiscUtilities.numBounds(i, -5, 5));
		results.add("");
		results.add("pointDistance(c, n): " + MiscUtilities.pointDistance(c, n));
		results.add("pointDistance(c, e): " + MiscUtilities.pointDistance(c, e));
		results.add("pointDistance(c, s): " + MiscUtilities.pointDistance(c, s));
		results.add("pointDistance(c, w): " + MiscUtilities.pointDistance(c, w));
		results.add("pointDistance(n, n): " + MiscUtilities.pointDistance(n, n));
		results.add("pointDistance(n, w): " + MiscUtilities.pointDistance(n, w));
		results.add("pointDistance(n, e): " + MiscUtilities.pointDistance(n, e));
		results.add("pointDistance(n, s): " + MiscUtilities.pointDistance(n, s));
		results.add("");
		results.add("pointDirection(c, n): " + MiscUtilities.pointDirection(c, n));
		results.add("pointDirection(c, e): " + MiscUtilities.pointDirection(c, e));
		results.add("pointDirection(c, s): " + MiscUtilities.pointDirection(c, s));
		results.add("pointDirection(c, w): " + MiscUtilities.pointDirection(c, w));
		results.add("pointDirection(n, n): " + MiscUtilities.pointDirection(n, n));
		results.add("pointDirection(n, w): " + MiscUtilities.pointDirection(n, w));
		results.add("pointDirection(n, e): " + MiscUtilities.pointDirection(n, e));
		results.add("pointDirection(n, s): " + MiscUtilities.pointDirection(n, s));

		for(String str : results)
			System.out.println(str);

	}

}
