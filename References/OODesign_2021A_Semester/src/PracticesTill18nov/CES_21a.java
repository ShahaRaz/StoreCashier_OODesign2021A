package PracticesTill18nov;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_21a {
	final static int FIXED_SIZE = 7;

	public static void main(String[] args) {
		File fixedText = new File("fixedText.txt");
		addFixedText(fixedText, "I");
		addFixedText(fixedText, "Like");
		addFixedText(fixedText, "Java");
		addFixedText(fixedText, "and");
		addFixedText(fixedText, "OOP!");
	}

	private static void addFixedText(File fixedText, String text) {
		try (RandomAccessFile raf = new RandomAccessFile(fixedText, "rw")) {
			raf.seek(raf.length());
			int writePointer = 0;
			while (writePointer < FIXED_SIZE && text.length() > writePointer) {
				raf.write(text.charAt(writePointer++));
			}

			while (writePointer++ < FIXED_SIZE) {
				raf.write(' ');
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


}
