package gameMaker;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * @depreciated
 * taken from <url>http://www.javaworld.com/javaworld/javatips/jw-javatip113.html?page=2</url>
 *
 */

//dang it. stupid thing doesn't work.

public class SubclassFinder {

	private Class superclass;
	
	public SubclassFinder(Class superclass){
		this.superclass = superclass;
	}
	
	public ArrayList<Class> find(String pckgname) {
		// Code from JWhich
	    // Translate the package name into an absolute path
	    String name = new String(pckgname);
	    if (!name.startsWith("/")) {
	        name = "/" + name;
	    }        
	    name = name.replace('.', '/');
	    
	    // Get a File object for the package
	    URL url = superclass.getClass().getResource(name);
	    File directory = new File(url.getFile());

	    if (directory.exists()) {
	        // Get the list of the files contained in the package
	        String[] files = directory.list();
	        ArrayList<Class> classes = new ArrayList<Class>();
	        for (int i = 0; i < files.length; i++) {
	            // we are only interested in .class files
	            if (files[i].endsWith(".class")) {
	                //remove the .class extension and add it to the result list
	                String classname = files[i].substring(0, files[i].length() - 6);
		            Class<GameObject> c;
		            GameObject gc;
					try {
						c = (Class<GameObject>) Class.forName(pckgname+"."+classname);
						gc = (GameObject) c.newInstance();
						if (superclass.isInstance(gc)){
							System.out.println(superclass+" is inctanceof " + gc);
			                classes.add(c);
						}
					} catch (ClassNotFoundException e) {
						// Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	        return classes;
	    }
	    return null;
	}
	
}
