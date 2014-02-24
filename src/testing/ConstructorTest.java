package testing;

import java.awt.Color;

public class ConstructorTest {

	public ConstructorTest(){
		this(Color.blue);
	}
	public ConstructorTest(Color c) {
		Color d = c;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
