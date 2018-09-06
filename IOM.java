package less02;

import java.io.File;

public class IOM {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\HalynaZ\\eclipse-workspace\\Advanced\\");
System.out.println(file.isDirectory());
System.out.println(file.isFile());
System.out.println(file.getAbsolutePath());
File[] files = file.listFiles();
for(File f:files) {
	System.out.println(f.getName());
}
}

}
