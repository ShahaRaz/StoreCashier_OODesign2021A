package PracticesTill18nov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_17 {
	public static void main(String[] args) {
		File file = new File("text.txt");

		deleteStrFromFile(file, "Like");
		deleteStrFromFile2(file, "Java");
		deleteStrFromFile3(file, "OOP");
	}

	public static void deleteStrFromFile(File file, String find) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			int readPointer = 0, writePointer = 0;
			byte[] data = new byte[find.length()];
			while (raf.read(data) != -1) {
				String readText = new String(data);
				if (readText.equals(find)) {
					readPointer += find.length();
				} else {
					readPointer++;
					raf.seek(writePointer++);
					raf.write(readText.charAt(0));
				}
				raf.seek(readPointer);
			}
			raf.setLength(writePointer);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteStrFromFile2(File file, String find) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			int readPointer = 0;
			byte[] data = new byte[find.length()];
			while (raf.read(data) != -1) {
				String readText = new String(data);
				if (readText.equals(find)) {
					byte[] temp = new byte[(int) (raf.length() - readPointer + find.length())];
					raf.read(temp);
					raf.setLength(readPointer);
					raf.write(temp);
				} else {
					raf.seek(readPointer++);
				}
				raf.seek(readPointer);
			}
			raf.setLength(readPointer);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteStrFromFile3(File file, String find) {
		byte[] data = new byte[find.length()];
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			int read = 0;
			while (raf.read(data) != -1) {
				String readText = new String(data);
				if (find.equals(readText)) {
					int i = (int) (raf.getFilePointer() - find.length());  // set back writing pointer
					for (int j = (int) raf.getFilePointer(); j < raf.length(); i++, j++) {
						raf.seek(j);
						int chr = raf.read();
						raf.seek(i);
						raf.write(chr);
					}
					raf.setLength(i);
				}
				raf.seek(++read);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
