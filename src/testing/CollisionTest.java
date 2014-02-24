package testing;

import java.awt.*;

//TODO theres something wrong with setPos(getPrevPOs()); its throwing an infinte loop

public class CollisionTest {

	public static void main(String[] args) {		
		Rectangle r1 = new Rectangle(-5,-5,10,10);
		Rectangle r2 = new Rectangle(-7,-7,3,3);
		System.out.println("r1: " + r1);
		System.out.println("r2: " + r2);
		System.out.println("intersects: "+r1.intersects(r2));
		System.out.println("intersection: "+r1.intersection(r2));
		System.out.println("intersection is empty?: " + r1.intersection(r2).isEmpty());
		/*for (int x = -1; x <= 10; x++){
			for (int y = -1; y <= 10; y++){
				Point p = new Point(x, y);
				char result = 'O';
				if (r1.contains(p))
					result = 'X';
				System.out.print(result);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(r1);
		r1.add(new Point(15,5));
		System.out.println(r1);*/
	}

}
