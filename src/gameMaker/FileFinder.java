package gameMaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileFinder {
	
	private static ArrayList<File> files;
	
	/**
	 * @param fileLocation
	 * @return an ArrayList of Class objects whose .class files are stored within the package packageName
	 */
	public static ArrayList<File> getFiles(String fileLocation, String... suffixes) throws FileNotFoundException {
		return getFiles(new File(fileLocation), suffixes);
	}
	
	public static ArrayList<File> getFiles(File parentFile, String... suffixes) throws FileNotFoundException {
		files = new ArrayList<File>();
		if (!parentFile.exists())
			throw new FileNotFoundException(parentFile + " not found");
		
		addFiles(parentFile);
		
		ArrayList<File> wantedFiles = new ArrayList<File>(files.size());
		for (File file : files)
			for (String suffix : suffixes)
				if (file.toString().endsWith(suffix))
					wantedFiles.add(file);
		
		wantedFiles.trimToSize();
		return wantedFiles;
	}

	//recursive
	private static void addFiles(File file){
		if (file.isFile())
			files.add(file);
		else if (file.isDirectory())
			for (File subfile : file.listFiles())
				addFiles(subfile);
	}
	
	/**
	 * Returns whether c1 is a class derived from c2.
	 * @param c1 The child class to check
	 * @param c2 The parent class to check
	 * @return whether c1 is a class derived from c2
	 */
	public static boolean isSubclass(Class<?> c1, Class<?> c2){
		while (!(c1 == Object.class || c1 == c2))
			c1 = c1.getSuperclass();
		return (c1 != Object.class || c2 == Object.class);
		//TODO check this out; see if it works: c1.isAssignableFrom(c2); and vice versa
	}
	
}