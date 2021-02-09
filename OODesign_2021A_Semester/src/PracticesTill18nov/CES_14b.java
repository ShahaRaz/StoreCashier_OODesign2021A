package PracticesTill18nov;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CES_14b {

	public static void main(String[] args) {
		File file = new File("temp.obj");
		Product p1 = new Product("Shirt", 499.99, '$');
		Product p2 = new Product("Pants", 695.95, '$');
		Contact c3 = new Contact("Avi", "0545555555", "Client", new Address("Arlozorov 121", "Givataim", "Israel", 8546978));
		Contact c1 = new Contact("Yosi", "0501111111", "Afeka", new Address("Herzel 7", "Tel-Aviv", "Israel", 123456));
		Contact c2 = new Contact("Moshe", "0527777777", "SA", new Address("Mota Gur 1", "Holon", "Israel", 456789));
//		{
//			@Override
//			public String getName() {
//				return "Yosi";
//			}
//		};
		
			

//		Person p = new Person() {
//			public String getName() {
//				return "stam";
//			}
//		};
				
				
//		System.out.println(c1.getName());		
//		System.out.println(c2.getName());//Yosi	
//		System.out.println(c3.getName());		
				
		boolean isAppandable = file.exists();
		ObjectOutputStream oOut = null;
		ObjectInputStream oIn = null;
		try {
			if(isAppandable)
				oOut = new ObjectOutputStream(new FileOutputStream(file, isAppandable)) {
				@Override
				public void writeStreamHeader() {
					//do nothing
					return;
				}
			};
			else
				oOut = new ObjectOutputStream(new FileOutputStream(file));
			
			oIn = new ObjectInputStream(new FileInputStream(file));
			// write objects to file
			oOut.writeUTF("Product");
			oOut.writeInt(2);
			oOut.writeObject(p1);
			oOut.writeObject(p2);

			oOut.writeUTF("Contact");
			oOut.writeInt(3);
			oOut.writeObject(c1);
			oOut.writeObject(c2);
			oOut.writeObject(c3);

			// read objects from file
			while (oIn.available() != 0) {
				String type = oIn.readUTF();
				int size = oIn.readInt();
				System.out.println(size + " " + type + "s:");
				switch (type) {
					case "Product":
						while (size-- > 0) {
							Product p = (Product) oIn.readObject();
							System.out.println(p);
						}
						break;
					case "Contact":
						while (size-- > 0) {
							Contact c = (Contact) oIn.readObject();
							System.out.println(c);
						}
						break;
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
