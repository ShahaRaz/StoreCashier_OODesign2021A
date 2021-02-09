package PracticesTill18nov;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Grigory Shaulov 
 * FileInputStream/FileOutputStream
 **/
public class CES_12 {

	public static void main(String[] args) {
		// create file object
		File f = new File("temp.txt");

		try ( // create FileOutputStream and FileInputStream
				FileOutputStream output = new FileOutputStream(f);
				FileInputStream input = new FileInputStream(f);
			) {
			
			//write string to file
			String temp = "I Like Object Oriented Programming!";
			output.write(temp.getBytes());

			//read string from file
			while (input.available() != 0) {
				char ch = (char) input.read();
				if (ch == ' ')
					System.out.println();
				else
					System.out.print(ch);

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
