package Prac2oct27;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Grigory Shaulov
 * FileInputStream/FileOutputStream
 * DataInputStream/DataOutputStream
 **/
public class CES_13a {

    public static void main(String[] args) {
        // create file object
        File f = new File("temp.dat");

        try ( // create DataInputStream with FileInputStream, DataOutputStream with
              // FileOutputStream
              DataOutputStream output = new DataOutputStream(new FileOutputStream(f));
              DataInputStream input = new DataInputStream(new FileInputStream(f));
        ) {

            // write data to file
            output.writeUTF("Shirt");
            output.writeDouble(499.99);
            output.writeChar('$');
            output.writeUTF("Pants");
            output.writeDouble(695.95);
            output.writeChar('$');

            // read data from file
            while (input.available() != 0) {
                String name = input.readUTF();
                double price = input.readDouble();
                char symbol = input.readChar();
                System.out.println(name + " : " + price + symbol);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}