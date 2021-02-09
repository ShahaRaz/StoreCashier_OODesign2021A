package PracticesTill18nov;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_21b {
	final static int FIXED_SIZE = 7;

	public static void main(String[] args) {

		File fixedText = new File("fixedText.txt");
		replaceFixedText(fixedText, "OOP", 2);
		replaceFixedText(fixedText, "Java!", 4);

	}

	private static void replaceFixedText(File fixedText, String text, int index) {
		try (RandomAccessFile raf = new RandomAccessFile(fixedText, "rw")) {
			int pos = (int) (raf.length() <= (index * FIXED_SIZE) ? raf.length() : (index * FIXED_SIZE));

			raf.seek(pos);
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
