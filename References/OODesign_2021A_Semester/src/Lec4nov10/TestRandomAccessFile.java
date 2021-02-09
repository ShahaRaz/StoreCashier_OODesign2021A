package Lec4nov10;

import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class TestRandomAccessFile
{ public static void main(String[] args) 
		throws IOException, ClassNotFoundException 
  { // Create a random access file
	final int IntLength = Integer.SIZE/8;
	System.out.println("Integer length is " + IntLength);
	File io = new File("inout.dat");
    RandomAccessFile inout = new RandomAccessFile(io, "rw");
    // Clear the file to destroy the old contents if exists
    inout.setLength(0);
    for (int i = 0; i < 200; i++) // Write new integers to the file
      inout.writeInt(i);
    // Display the current length of the file
    System.out.println("Current file length is " + inout.length());
    // Retrieve the first number
    inout.seek(0); // Move the file pointer to the beginning
    System.out.println("The first number is " + inout.readInt());
    // Retrieve the second number
    inout.seek(1 * IntLength); /* Move the file pointer to the second number.
      the pointer is there after the last read, so inout.seek(1 * 4) is
      not necessary in this case. */    
    System.out.println("The second number is " + inout.readInt());
    // Retrieve the tenth number
    inout.seek(9 * IntLength); // Move the file pointer to the tenth number
    System.out.println("The tenth number is " + inout.readInt());
    inout.writeInt(555); // Modify the eleventh number
    // Append a new number
    inout.seek(inout.length()); // Move the file pointer to the end
    inout.writeInt(999);
    // Display the new length
    System.out.println("The new length is " + inout.length());
    // Retrieve the new eleventh number
    inout.seek(10 * IntLength); //Move the file pointer to the eleventh number
    System.out.println("The eleventh number is " + inout.readInt());
    inout.close();
   }
}
 