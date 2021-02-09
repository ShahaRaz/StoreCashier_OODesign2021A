package Prac4nov10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_15 {

    public CES_15(){
        File file = new File("text.text");
        cleanFile(file);
        writeStrToFile(file,"I Lke Java And OOP!",10);
    }

    private static void writeStrToFile(File file, String text, int pos) {
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            raf.seek(pos);
            raf.write(text.getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleanFile(File file) {
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            raf.setLength(1); // destroy all the bytes to the right of the pointer  [01010101] -> setLenght(1) -> [0]


        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
