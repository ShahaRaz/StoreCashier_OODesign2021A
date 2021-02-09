package PracticesTill18nov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_16 {

	public static void main(String[] args) {
		File file = new File("text.txt");
		writeStrToFile(file, "I Like Java And OOP!", 0);
	}

	private static void writeStrToFile(File file, String text, int pos) {
		try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
			raf.seek(pos);
			raf.write(text.getBytes());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}		
	}

}
