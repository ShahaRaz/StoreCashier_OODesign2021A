package main.model;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileHandler {
	private File file = new File("products.txt");

	// remove string from file
	public void deleteStrFromFile(File file, String find) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			// create readPointer for reading and writePointer for writing
			int readPointer = 0, writePointer = 0;

			// array for reading bytes in file
			byte[] data = new byte[find.length()];

			// while it is not end of file
			while (raf.read(data) != -1) {
				// read string bytes
				String readText = new String(data);

				// if read string need be removed from file
				if (readText.equals(find)) {
					readPointer += find.length();
					// if not, rewrite first char and check next string from second char
				} else {
					readPointer++;
					raf.seek(writePointer++);
					raf.write(readText.charAt(0));
				}
				// set file pointer for reading
				raf.seek(readPointer);
			}
			// delete copied string from the end
			raf.setLength(writePointer);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteStrFromFile2(File file, String find) {
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
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteStrFromFile3(File file, String find) {
		byte[] data = new byte[find.length()];
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			int read = 0;
			while (raf.read(data) != -1) {
				String readText = new String(data);
				if (find.equals(readText)) {
					int i = (int) (raf.getFilePointer() - find.length());
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

	// read string from file by position and string size
	public byte[] readStrFromFile(File file, int pos, int size) {
		byte[] temp = null;

		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			// set pointer to read position
			raf.seek(pos);

			// set size of array for reading
			// if zero read all strings in right side
			if (size == 0)
				temp = new byte[(int) (raf.length() - pos)];
			// read only size chars
			else
				temp = new byte[size];

			// copy chars to array
			raf.read(temp);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return temp;
	}

	// add fixed text to file
	public void addFixedStrToFile(File file, String text) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			// set pointer to end position
			raf.seek(raf.length());
			int writeIndex = 0;
			while (text.length() > writeIndex) {
				// write char to file
				raf.write(text.charAt(writeIndex++));
			}
			// write spaces
			raf.write(' ');
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
