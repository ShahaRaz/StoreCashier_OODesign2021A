package PracticesTill18nov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Grigory Shaulov
 * Printwriter/Scanner
 **/
public class CES_11 {
	public static void main(String[] args) {
		// create file object
		File file = new File("temp.txt");
		
		try (// create PrintWriter and Scanner
				PrintWriter output = new PrintWriter(file);
				Scanner input = new Scanner(file);

			){

			//write string to file
			String temp = "I Like Object Oriented Programming!";
			int index = temp.indexOf(" ");
			while (index != -1) {
				output.println(temp.substring(0, index));
				temp = temp.substring(index + 1);
				index = temp.indexOf(" ");
			}
			output.println(temp);
			output.flush();
			
			//read string from file
			while (input.hasNext())
				System.out.println(input.nextLine());
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
