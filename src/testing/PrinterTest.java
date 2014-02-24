package testing;

import gameMaker.Printer;

public class PrinterTest {

	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		char c = 'c';

		Printer.println(c, a, " ", b, "", b);
		//test indent
	}

}
