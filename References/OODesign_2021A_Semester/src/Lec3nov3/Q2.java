package Lec3nov3;

import Lec2oct27Streams.Product;

import java.io.*;
import java.util.ArrayList;

/* Write a class of product (name and price)
define array of products in main.
Product[] a = {
	new Product("Bamba", 9),
	new Product("Bisli", 19)
};

Then save all the products into the file. (using ObjectOutputStream)
And finally, read the file content to the console
*/
public class Q2 {
	public static final String F_NAME = "1.txt";

	public Q2() {
		Product[] a = { new Product("Bamba", 9),
				new Product("Bisli", 19), new Product("Kinder", 7),
				new Product("Cola", 12), new Product("Taami", 9) };

		try {

			save(a);
			System.out.println("File saved!");
			System.out.println("\nStart to read the file...");
			Product[] b = read();
			print(b);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void save(Product[] a) throws FileNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(F_NAME));
		for(Product p:a)
			o.writeObject(p);
		o.close();
	}

	public static Product[] read() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Product> lst = new ArrayList<>();
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(F_NAME));
		while(i.available()>0){
			Product p = (Product) i.readObject();
			lst.add(p);
		}
		i.close();
		Product[] a = new Product[lst.size()];
		return lst.toArray(a);
	}

	public static void print(Product[] a) {
		for (Product p : a)
			System.out.println(p);
	}

}