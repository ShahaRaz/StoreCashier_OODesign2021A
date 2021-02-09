package PracticesTill18nov;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_19 {
	public static void main(String[] args) {
		File file = new File("text.txt");

		byte[] data = readStrFromFile(file, 4, 6);
		System.out.println(new String(data));
		data = readFileData(file);
		System.out.println(new String(data));

	}

	private static byte[] readFileData(File file) {
		return readStrFromFile(file, 0, 0);
	}

	private static byte[] readStrFromFile(File file, int pos, int size) {
		byte[] temp = new byte[size];
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			if (size == 0)
				temp = new byte[(int) (raf.length() - pos)];
			raf.seek(pos);
			raf.read(temp);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return temp;
	}

}
