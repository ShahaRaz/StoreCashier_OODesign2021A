package main.model;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.util.*;

/**
 * references:
 * Lectures_Arnon.03 - 03.11.2020 Q3 (save objects to random access file)
 * Lectures_Arnon.10 - 22.12.2020 Q2 (Iterator & Remove products from collection)
 */
public class FileHandler implements Iterable<Product> {
    private File file;
    private boolean isAppendableFile;


    public FileHandler() {
        this.file = new File("products.txt");
        this.isAppendableFile = file.exists();
    }

    /**
     * writes the entire map to the file (while overwriting the existing one)
     *
     *
     * @param theMap - Reference to it
     */
    public void saveMapToFile(SortedMap<String, Product> theMap, boolean isClearingMapB4){
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(0); // empty the file.
            for (Map.Entry<String, Product> pair : theMap.entrySet()) {
                Product tmp = (Product) pair.getValue(); // gets the product
                raf.writeUTF(tmp.getBarcode()); // Barcode
                raf.writeUTF(tmp.getDescription()); // DESCRIPTION
                raf.writeInt(tmp.getPriceSold()); // Price sold
                raf.writeInt(tmp.getCostToStore()); // Cost to store
                raf.writeLong(tmp.getTimeAdded()); // Time Added
                // _______ Write Costumer ____
                raf.writeUTF(tmp.getCustomer().getName()); // customer's name
                raf.writeUTF(tmp.getCustomer().getMobileNumber()); // customer's mobile num
                raf.writeBoolean(tmp.getCustomer().getIsAcceptingPromotions()); // customer's is accepting
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMapFromFile(SortedMap<String, Product> theMap, boolean isClearingMapB4) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            if (isClearingMapB4) {
                theMap.clear(); // remove all elements from map
            }
            raf.seek(0); // go to beelining of file
            while (raf.getFilePointer() < raf.length()) {
                String barcode = raf.readUTF(); // Barcode
                String desc = raf.readUTF(); // DESCRIPTION
                int priceSold = raf.readInt(); // Price sold
                int costToStore = raf.readInt(); // Cost to store
                long timeAdded = raf.readLong(); // Time Added
                // _______ Read Costumer ____
                String cName = raf.readUTF(); // customer's name
                String cMobileNum = raf.readUTF(); // customer's mobile num
                boolean cAcceptAds = raf.readBoolean(); // customer's is accepting

                // creating the product
                Product addMe = new Product(timeAdded, desc, barcode,
                        costToStore, priceSold, new Customer(cName, cMobileNum, cAcceptAds));
                // adding the product
                System.out.println("adding: " + addMe.toString());
                theMap.put(addMe.getBarcode(), addMe);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void save(Product[] a) throws FileNotFoundException, IOException {
//
//        try (RandomAccessFile f = new RandomAccessFile(F_NAME, "rw")) {
//            f.setLength(0);
//            for (Product p : a) {
//                f.writeUTF(p.getName());
//                f.writeInt(p.getPrice());
//            }
//        }
//    }

    public void addProductToFile(Product p) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            // TODO implement addProductToFile


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator();
    }


    // TODO complete this iterator later
    private class ConcreteIterator implements Iterator<Product> {
        private int cur = 0; // the element to be retrieved now with next
        private int last = -1; // the element to be removed

        @Override
        public boolean hasNext() {

            return false;
        }

        @Override
        public Product next() {
            if (!hasNext())
                throw new NoSuchElementException();
//            Product tmp = a[cur];
            // last = cur
            // cur++
//            return temp;
            return new Product("blabla", 5, 3, new Customer("hagy", "050", true), "df");

        }

        @Override
        public void remove() {

        }

    }
}


//    // remove string from file
//    public void deleteStrFromFile(String find) {
//        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
//            // create readPointer for reading and writePointer for writing
//            int readPointer = 0, writePointer = 0;
//
//            // array for reading bytes in file
//            byte[] data = new byte[find.length()];
//
//            // while it is not end of file
//            while (raf.read(data) != -1) {
//                // read string bytes
//                String readText = new String(data);
//
//                // if read string need be removed from file
//                if (readText.equals(find)) {
//                    readPointer += find.length();
//                    // if not, rewrite first char and check next string from second char
//                } else {
//                    readPointer++;
//                    raf.seek(writePointer++);
//                    raf.write(readText.charAt(0));
//                }
//                // set file pointer for reading
//                raf.seek(readPointer);
//            }
//            // delete copied string from the end
//            raf.setLength(writePointer);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void deleteStrFromFile2(String find) {
//        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
//            int readPointer = 0;
//            byte[] data = new byte[find.length()];
//            while (raf.read(data) != -1) {
//                String readText = new String(data);
//                if (readText.equals(find)) {
//                    byte[] temp = new byte[(int) (raf.length() - readPointer + find.length())];
//                    raf.read(temp);
//                    raf.setLength(readPointer);
//                    raf.write(temp);
//                } else {
//                    raf.seek(readPointer++);
//                }
//                raf.seek(readPointer);
//            }
//            raf.setLength(readPointer);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void deleteStrFromFile3(String find) {
//        byte[] data = new byte[find.length()];
//        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
//            int read = 0;
//            while (raf.read(data) != -1) {
//                String readText = new String(data);
//                if (find.equals(readText)) {
//                    int i = (int) (raf.getFilePointer() - find.length());
//                    for (int j = (int) raf.getFilePointer(); j < raf.length(); i++, j++) {
//                        raf.seek(j);
//                        int chr = raf.read();
//                        raf.seek(i);
//                        raf.write(chr);
//                    }
//                    raf.setLength(i);
//                }
//                raf.seek(++read);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // read string from file by position and string size
//    public byte[] readStrFromFile(int pos, int size) {
//        byte[] temp = null;
//
//        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
//            // set pointer to read position
//            raf.seek(pos);
//
//            // set size of array for reading
//            // if zero read all strings in right side
//            if (size == 0)
//                temp = new byte[(int) (raf.length() - pos)];
//                // read only size chars
//            else
//                temp = new byte[size];
//
//            // copy chars to array
//            raf.read(temp);
//
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return temp;
//    }
//
//    // add fixed text to file
//    public void addFixedStrToFile(String text) {
//        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
//            // set pointer to end position
//            raf.seek(raf.length());
//            int writeIndex = 0;
//            while (text.length() > writeIndex) {
//                // write char to file
//                raf.write(text.charAt(writeIndex++));
//            }
//            // write spaces
//            raf.write(' ');
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void addStrToFile(String txt, int pos) {
//        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
//            // go to position:
//            raf.seek(pos);
//
//            // backup data from pointer to the end of the file
//            byte[] temp = new byte[(int) (raf.length() - pos)];
//            raf.read(temp);
//
//            // return to the position
//            raf.seek(pos);
//
//            //write new text and backup text to file
//            raf.write((txt.getBytes()));
//            raf.write(temp);
//
//            System.out.println("String \"" + txt + "\" added to the file" + file.getName() + "! :D");
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

