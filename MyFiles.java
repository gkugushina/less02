package less02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MyFiles {
	File file = new File("C:\\");

	public String getPath() {
		return file.getAbsolutePath();
	}

	public void setPath(String path) {
		file = new File(path);
	}

	public boolean isDirectory(File file) {
		return file.isDirectory();
	}

	public boolean isPathToDirectory(String path) {
		return (new File(path)).isDirectory();
	}

	public boolean isFile(File file) {
		return file.isFile();
	}

	public File[] list() {
		return file.listFiles();
	}

	public ArrayList<File> sort() {
		File[] sortfiles = file.listFiles();
		ArrayList<File> directories = new ArrayList<File>();
		ArrayList<File> fileS = new ArrayList<File>();
		for (File f : sortfiles) {
			if (isDirectory(f)) {
				directories.add(f);
			} else {
				fileS.add(f);
			}
		}
		directories.addAll(fileS);
		return directories;
	}

	public String currentDirectory() {
		return file.getName();
	}

	public boolean isTextFile(String path) {
		return (path.endsWith(".txt"));
	}
	
	public boolean isImageFile(String path) {
		return (path.endsWith(".png")||path.endsWith(".jpg"));
	}

	public boolean isRoot(String path) {
		return path.equals("C:\\");
	}
	
	public void copy(String path) {
		/*Path getpath = Paths.get(path);
		Path whereTo = Paths.get("C:\\Users\\HalynaZ\\for copies\\newcopy.txt");
		try {
			Files.copy(getpath, whereTo);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*Path getpath = Paths.get(path);
		File file = new File("C:\\Users\\HalynaZ\\for copies\\copycopy.txt");
		
		try {
			OutputStream temp=new FileOutputStream(file);
			Files.copy(getpath, temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
