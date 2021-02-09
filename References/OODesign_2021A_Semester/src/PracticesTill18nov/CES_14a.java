package PracticesTill18nov;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CES_14a {

	public static void main(String[] args) {
		File file = new File("temp.obj");
		Product p1 = new Product("Shirt", 499.99, '$');
		Product p2 = new Product("Pants", 695.95, '$');
		try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file));
				ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file))) {
			
			//write objects to file
			oOut.writeInt(2);
			oOut.writeObject(p1);
			oOut.writeObject(p2);
			
			//read objects from file
			while(oIn.available() != 0) {
				int size = oIn.readInt();
				while(size-- > 0) {
					Product p = (Product) oIn.readObject();
					System.out.println(p);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
