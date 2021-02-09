package Prac5nov17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CES_18 {
    public CES_18() {
        File file = new File("text.txt");
        int pos = 5;
        addStrToFile(file,"OOP",pos);

    }



    // our goal: we have text file: we want to add a word in the middle of the file.
    //steps;
    // get to the position we want to add to
    // now , we dont want to overwrite the existing text.
    // so we need to shift the entire text
    // so we'll copy the entire suffix (SIUMET)
    // write to the file (by overwriting what ever needed,
    // and then copy back the suffix we copied be4
    private void addStrToFile(File file,String text,int pos){
        try (RandomAccessFile raf = new RandomAccessFile(file,text)){
            raf.seek(pos);
            byte[] temp = new byte[(int)(raf.length()-pos)];
            raf.read(temp); // reading all file, and in the end the pointer is in the end
            raf.seek(pos);
            raf.write(text.getBytes());
            raf.write(temp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
