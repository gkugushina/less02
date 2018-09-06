package less02;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CatM {

	public static void main(String[] args) {
		Owner owner = new Owner("Yaroslaff");
		Cat cat = new Cat("Vitalina",21);
		cat.setOwner(owner);
		System.out.println(cat);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			//FileOutputStream fos = new FileOutputStream("CatCOpy.bin");
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(cat);
			//oos.close();
			//fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		
		try {
			//FileInputStream fis = new FileInputStream("CatCOpy.bin");
			ObjectInputStream ois = new ObjectInputStream(bais);
			Cat cloneCat = (Cat)ois.readObject();
			System.out.println(cloneCat);
			System.out.println("Changing owner name");
			owner.setName("Sergio");
			System.out.println(cat);
			System.out.println(cloneCat);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
