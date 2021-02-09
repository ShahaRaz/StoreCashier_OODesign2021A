package Lec3nov3;

//same as Q2asHeMarks but with... ???? Try with resources

import Lec2oct27Streams.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Q3asHeMarks {

    public static final String F_NAME = "1.txt";

    public Q3asHeMarks() {
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
