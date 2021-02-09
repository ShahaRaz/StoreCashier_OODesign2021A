package Lec2oct27Streams;

import java.io.*;

public class Q1 {
    // ___________Q1
    // write 100,000 integers to the file.
    // the integers will be in the range of 0 to 100.
    // then read the file.
    // print the duration of all of the above
    // (Use System.currentTimeMillis())
    // Then make all of the above with a buffer,
    // and compare
    public static final String F_NAME = "1.txt";
    public Q1() {
        try {
            long start = System.currentTimeMillis();
            save();
            System.out.println("File saved!");
            System.out.println("\nStart to read the file...");
            read();
            long end = System.currentTimeMillis();
            System.out.println("Total time = " + (end - start));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void save() throws IOException {
            final int MAX = 100_000;
            //DataOutputStream o = new DataOutputStream(new FileOutputStream(F_NAME));
            //DataOutputStream o = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(F_NAME)));

            //FileOutputStream o = new FileOutputStream(F_NAME);
            BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(F_NAME));
            for (int i = 0; i < MAX; i++) {
                int value = i % 101;
                //o.writeInt(value);
                o.write(value);
            }
            o.close();

        }

        public static void read() throws FileNotFoundException, IOException {
            long sum = 0;
            //DataInputStream i = new DataInputStream(new FileInputStream(F_NAME));
            //DataInputStream i = new DataInputStream(new BufferedInputStream(new FileInputStream(F_NAME)));
            //FileInputStream i = new FileInputStream(F_NAME);
            BufferedInputStream i = new BufferedInputStream(new FileInputStream(F_NAME));
            while (i.available() > 0) {
                // int value = i.readInt();
                int value = i.read();
                sum += value;
                //System.out.println(value);
            }
            i.close();
            System.out.println("sum = " + sum);
        }


    }
