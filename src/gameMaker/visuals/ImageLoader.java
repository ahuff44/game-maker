package gameMaker.visuals;

import gameMaker.utilities.FileFinder;
import gameMaker.utilities.Printer;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

public class ImageLoader {
	
	static HashMap<String, Image> images = new HashMap<String, Image>();
	
	public static void preload(String parentDirectory) throws FileNotFoundException{
		if (!parentDirectory.endsWith("/")){
			parentDirectory = parentDirectory + '/';
		}
		ArrayList<File> classFiles = FileFinder.getFiles(parentDirectory, ".bmp", ".png", ".jpeg", ".bmp");
		for (File file : classFiles){
			String key = file.toString();
			key = key.substring(parentDirectory.length());
			Image img = load(file);
			images.put(key, img);
		}
	}
	
	public static Image load(File imgFile){
		try {
			return (new ImageIcon(imgFile.toURI().toURL())).getImage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image get(String imageName) throws FileNotFoundException{
		Image img = images.get(imageName);
		if (img == null){
			throw new FileNotFoundException(imageName + " was not found");
		} else {
			return img;
		}
	}
	
	/**
	 * A debugging method that prints out all image keys.
	 */
	public static void printImageKeys(){
		for (Entry<String, Image> e : images.entrySet()){
			Printer.println(" ", e.getKey());
		}
	}
	
}
