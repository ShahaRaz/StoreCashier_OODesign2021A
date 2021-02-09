package Lec3nov3;

// 1. Save the products to the file
// 2. Read the file content to the console
// 3. Scan the file, and each product with price >=10 will get discount
// 		modify the file (UPDATE, not writing a new file) of 10%.
// 4. Read the new file to the console

import Lec2oct27Streams.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class Q2asHeMarks {

    public static final String F_NAME = "1.txt";

    public Q2asHeMarks() {
        Product[] a = { new Product("Bamba", 9), new Product("Bisli", 19), new Product("Kinder", 7),
                new Product("Cola", 12), new Product("Taami", 9) };

        try {

            save(a);
            System.out.println("File saved!");
            System.out.println("\nStart to read the file...");
            Product[] b = read();
            print(b);

            System.out.println("\nStart now to scan the file for discount...");
            update();
            Product[] a1 = read();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void save(Product[] a) throws FileNotFoundException, IOException {
    RandomAccessFile io = new RandomAccessFile(F_NAME,"rw");
    io.setLength(0); // make sure that the file is empty
    for (Product p:a){
        io.writeUTF(p.getName());
        io.writeInt(p.getPrice());
        }
    io.close();
    }


    public static Product[] read() throws FileNotFoundException, IOException {
        ArrayList<Product> lst = new ArrayList<>();
        RandomAccessFile io = new RandomAccessFile(F_NAME,"r");
        while(io.getFilePointer()<io.length()){
            Product p = new Product(io.readUTF(),io.readInt());
            lst.add(p);
        }
        io.close();
        Product[] a = new Product[lst.size()];
        return lst.toArray(a);
    }

    public static void print(Product[] a) {
        for (Product p : a)
            System.out.println(p);

    }

    private static void update() throws FileNotFoundException,IOException {
        final int MINFORDISCOUNT =10;
        final float DISCOUNT = 0.9f;
        RandomAccessFile io = new RandomAccessFile(F_NAME,"rw");
        while(io.getFilePointer()<io.length()){
            io.readUTF(); // just to get the pointer after
            int price = io.readInt();
            long pointer = io.getFilePointer();
            if(price>=MINFORDISCOUNT){
                price = (int)(price* DISCOUNT);
                io.seek(pointer);
                io.writeInt(price);
            }
        }
        io.close();
    }

}
