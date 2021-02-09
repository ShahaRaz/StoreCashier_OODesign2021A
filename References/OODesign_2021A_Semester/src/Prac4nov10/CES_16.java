package Prac4nov10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
// copy of 15 and go on ( 16:25)
public class CES_16 {

    public CES_16(){
        File file = new File("text.text");
        cleanFile(file);
        writeStrToFile(file,"I Lke Java And OOP!",10);
        deleteStrFromFile(file,"Java");
    }

    private void deleteStrFromFile(File file, String find) {
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            int readPointer =0,writePointer=0;
            byte[] data = new byte[find.length()];
            while (raf.read(data) != -1) {
                String readText = new String(data);
                if(find.equalsIgnoreCase(readText)){
                    readPointer+=find.length();
                }
                else{
                    readPointer++;
                    writePointer++;
                    raf.seek(writePointer);//  raf.seek(writePointer++);
                    raf.write(readPointer);




                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
