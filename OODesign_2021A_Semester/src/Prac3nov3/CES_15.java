package Prac3nov3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_15 {
    public CES_15() {
        File file = new File("text.txt");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        cleanFile(file);
        writeStringToFile(file, "I Love Java and OOP!", 0);
        deleteStringFromFile(file, "Java");
        addStrToFile(file,"Java",1);
    }

    private void addStrToFile(File file, String text, int pos) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(pos); // go to position
            byte[] temp = new byte[(int) (raf.length()-pos)]; // store the data from pointer to the end
             raf.read(temp);
            raf.seek(pos);//return to position
            raf.write(text.getBytes());// write text and backup to text file
            raf.write(temp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void cleanFile(File file) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(0);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStringToFile(File file, String data, int pos) // the method overrides
    {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(pos);
            raf.write(data.getBytes());
            raf.setLength(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteStringFromFile(File file, String text) {
        //1. we'll seek for the word in the inside the file
        byte[] data = new byte[text.length()];
        int writePointer = 0;
        int readPointer = 0;

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            while (raf.read() != -1) {
                if (text.equalsIgnoreCase(new String(data))) {
                    readPointer += text.length();
                } else {
                    readPointer++;
                    raf.seek(writePointer++);
                    raf.write((new String(data).charAt(0) + "").getBytes());

                }
                raf.seek(readPointer);
            }
            raf.setLength(writePointer);
            System.out.println("String \"" + text + "  after deleting:\"");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
