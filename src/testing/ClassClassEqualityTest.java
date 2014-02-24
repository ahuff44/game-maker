package testing;

public class ClassClassEqualityTest {

	public ClassClassEqualityTest(){
		System.out.println("getClass(): " + getClass());
		if (getClass().equals(ClassClassEqualityTest.class))
			System.out.println("yay");
		else
			System.out.println("ohno :( try .equals();");
	}
	
	public static void main(String[] args) {
		System.out.println("started\n");
		new ClassClassEqualityTest();
		System.out.println("\nended");
	}
}
