package gameMaker.utilities;

public class Printer {

	private static int indent = 0;
	private static boolean on = true;

	/**
	 * @return the old value
	 */
	public boolean turnOn(){
		boolean old = on;
		on = true;
		return old;
	}

	/**
	 * @return the old value
	 */
	public boolean turnOff(){
		boolean old = on;
		on = false;
		return old;
	}
	
	/**
	 * @return the *new* value
	 */
	public boolean toggleOn(){
		on = !on;
		return on;
	}
	
	public static int addIndent(){
		return ++indent;
	}
	
	public static int decrementIndent(){
		if (--indent < 0)
			indent = 0;
		return indent;
	}	

	public static int getIndent(){
		return indent;
	}

	public static String getIndentStr(){
		StringBuffer indentStr = new StringBuffer(indent);
		for (int i = 0; i < indent; i++)
			indentStr.append(' ');	
		return indentStr.toString();
	}
	
	public static String createString(Object... objs){
		StringBuffer str = new StringBuffer("");
		str.append(getIndentStr());
		for (Object obj : objs)
			str.append(obj);
		return str.toString();
	}

	public static void print(Object... objs){
		if (on)
			System.out.print(createString(objs));
	}
	
	public static void println(Object... objs){
		if (on)
			System.out.println(createString(objs));
	}

}
