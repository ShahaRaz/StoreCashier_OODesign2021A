package Prac2oct27;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Grigory Shaulov
 * FileInputStream/FileOutputStream
 * BufferedInputStream/BufferedOutputStream
 **/
public class CES_13b {

    public static void main(String[] args) {
        // create file and copyFile object
        File file = new File("temp.dat");
        File copyFile = new File("copyTemp.dat");

        try ( //create BufferedInputStream, BufferedOutputStream, DataInputStream
              BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
              BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(copyFile));
              DataInputStream dIn = new DataInputStream(new FileInputStream(copyFile));
        ) {

            //copy file data bytes to byte array
            byte[] data = new byte[input.available()];
            input.read(data);

            // copy byte array to new file
            output.write(data);
            output.flush();

            // read data from copyFile
            while (dIn.available() != 0) {
                String name = dIn.readUTF();
                double price = dIn.readDouble();
                char symbol = dIn.readChar();
                System.out.println(name + " : " + price + symbol);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}