package PracticesTill18nov;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_18 {
	public static void main(String[] args) {
		File file = new File("text.txt");

		addStrToFile(file, "OOP", 0);
	}

	private static void addStrToFile(File file, String text, int pos) {
		try(RandomAccessFile raf = new RandomAccessFile(file, text)){
			raf.seek(pos);
			byte[] temp = new byte[(int) (raf.length()-pos)];
			raf.read(temp);
			raf.seek(pos);
			raf.write(text.getBytes());
			raf.write(temp);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	
	}


}
