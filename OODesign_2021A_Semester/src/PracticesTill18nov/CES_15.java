package PracticesTill18nov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_15 {

	public static void main(String[] args) {
		File file = new File("text.txt");
		cleanFile(file);
	}

	private static void cleanFile(File file) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.setLength(0);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
