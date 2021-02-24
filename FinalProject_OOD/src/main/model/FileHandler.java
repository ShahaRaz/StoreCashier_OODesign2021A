package main.model;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

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
    private RandomAccessFile raf;
    private static final String FILE_NAME = "products.txt"; // Yes, it ends with .txt while its a binary file. (asked by the professor)
    private static final String TAG = "FileHandler";
    public FileHandler() {
        this.file = new File(FILE_NAME);
        this.isAppendableFile = file.exists();
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")){


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * writes the entire map to the file (while overwriting the existing one)
     *
     * @param theMap - Reference to it
     */
    public void saveMapToFile(SortedMap<String, Product> theMap,int mapOrdering_KEYS) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            if(!writeMapOrderingToFile(mapOrdering_KEYS)) { // if products already in the map, it's already ordered
                System.err.println((TAG + ", saveMapToFile: File is empty, skip writing the ordering"));
                // First 4bytes [0,3] will indicate which ordering are we using
            }
            raf.seek(4); // stepping over the mapOrderingIndicator (int, 4bytes)
//            raf.setLength(4); // empty the file. (overWriting)
            for (Map.Entry<String, Product> pair : theMap.entrySet()) {
                Product tmp = (Product) pair.getValue(); // gets the product
                writeProductToFile(tmp,raf);
//                raf.writeUTF(tmp.getBarcode()); // Barcode
//                raf.writeUTF(tmp.getDescription()); // DESCRIPTION
//                raf.writeInt(tmp.getPriceSold()); // Price sold
//                raf.writeInt(tmp.getCostToStore()); // Cost to store
//                raf.writeLong(tmp.getTimeAdded()); // Time Added
//                // _______ Write Costumer ____
//                raf.writeUTF(tmp.getCustomer().getName()); // customer's name
//                raf.writeUTF(tmp.getCustomer().getMobileNumber()); // customer's mobile num
//                raf.writeBoolean(tmp.getCustomer().getIsAcceptingPromotions()); // customer's is accepting
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void writeProductToFile(Product tmp, RandomAccessFile raf) throws IOException { // todo continue
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

    /**
     * first calling readMapOrdering()!
     *
     * @param theMap
     * @param isClearingMapB4
     */
    public void readMapFromFile(SortedMap<String, Product> theMap, boolean isClearingMapB4) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            if (isClearingMapB4) {
                theMap.clear(); // remove all elements from map
            }
            // _____________________________________________ passing over the readInt
            // NOTE! bytes 0-3 in the file are used for the mapOrder_KEYS,
            //raf.seek(3); // go to begging of file
            System.out.println("File handler, readMapFromFile , raf position is: " + raf.getFilePointer());
            raf.seek(0);
            raf.readInt();

            System.out.println("File handler, readMapFromFile , raf position is: " + raf.getFilePointer());


            // _____________________________________________

            Iterator i = new ConcreteIterator(file);
            while (i.hasNext()) {
                System.err.println((TAG + ", readMapFromFile: "));
                Product p = (Product) i.next();
                theMap.put(p.getBarcode(), p);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int readMapOrdering() {
        System.err.println((TAG + ", readMapOrdering: fileLen is " + file.length()));
        if (file.length() == 0)
            return -1;// -1 means the file is empty

        int theOrderingInTheFile = -1; // if -1, something went wrong, or empty
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(0);
            theOrderingInTheFile = raf.readInt(); // raf's pointer will go to the 4th byte (after reading [0,3]
            System.err.println((TAG + ", readMapFromFile: READ RAF.READINT:" + theOrderingInTheFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return theOrderingInTheFile; // Store.KEYS.ORDER_BY_...
    }

    public boolean writeMapOrderingToFile(int mapOrdering_KEYS) {
        System.err.println((TAG + ", writeMapOrderingToFile: mapOrdering = " + mapOrdering_KEYS));
        if (file.length() != 0)
            return false;// file already contains data, order has already been set.
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(0); // make sure that empty, and pointer on first byte.
            raf.writeInt(mapOrdering_KEYS);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // could not write for any reason
    }

    @Override
    public Iterator<Product> iterator() {
        return new ConcreteIterator(file);
    }

    public void removeProductFromFile(Product product) {
        Iterator i = this.iterator();
        while (i.hasNext()) {
            Product p = (Product) i.next();
            if (p.equals(product)) {
                i.remove();
                System.out.println("\nThe element " + product.getDescription() + " was deleted,  FileHandler 116");
                break;
            }
        }
    }

    public void replaceProductWithOtherVersion(Product product) {
        Iterator i = this.iterator();
        while (i.hasNext()) {
            Product p = (Product) i.next();
            if (p.equals(product)) {
                i.replace(product);
                System.err.println((TAG + ", replaceProductWithOtherVersion: time for replace"));
                break;
            }
        }
    }


    // TODO complete this iterator later
    private class ConcreteIterator implements Iterator<Product> {
        private RandomAccessFile raf_concreteItr;
        private Product lastReturnedProduct;
        private long pointerToB4LastReturnedElement;
//        private int indexOflastProductReturned =-1;


        public ConcreteIterator(File file) {
            try {
                raf_concreteItr = new RandomAccessFile(file, "rw");
                pointerToB4LastReturnedElement = 4; // maybe change me
            } catch (FileNotFoundException e) {
                System.err.println("Unable to open Random Access file in ConcreteIterator");
                e.printStackTrace();
            }

        }

        @Override
        public boolean hasNext() {
            try {
                System.err.println((TAG + ",___ hasNext:\tgetFilePointer: " +raf_concreteItr.getFilePointer() + "\t length: " + raf_concreteItr.length() ));
                return (raf_concreteItr.getFilePointer() < raf_concreteItr.length());
            } catch (IOException e) {
                e.printStackTrace();
                return false; // in case that the iterator doesn't work
            }
        }

        @Override
        public Product next() {
            if (!hasNext())
                throw new NoSuchElementException();
            else { // we have next element!
                try {
                    // _________________________________________
                    pointerToB4LastReturnedElement = raf_concreteItr.getFilePointer();
//                    System.err.println((TAG + ", next: pointerToB4LastReturnedElement = " + pointerToB4LastReturnedElement));

                    if (pointerToB4LastReturnedElement==0) {
                        raf_concreteItr.readInt(); // skipping over the first 4 bytes
                        pointerToB4LastReturnedElement=raf_concreteItr.getFilePointer();
//                        System.err.println((TAG + ", next: AfterReadInt pointerToB4Last = " + pointerToB4LastReturnedElement));
                    }
//                    if (pointerToB4LastReturnedElement == 0)
//                        raf.readInt();

                    // _________________________________________
                    String barcode = raf_concreteItr.readUTF(); // Barcode
                    String desc = raf_concreteItr.readUTF(); // DESCRIPTION
                    int priceSold = raf_concreteItr.readInt(); // Price sold
                    int costToStore = raf_concreteItr.readInt(); // Cost to store
                    long timeAdded = raf_concreteItr.readLong(); // Time Added
                    // _______ Read Costumer ____
                    String cName = raf_concreteItr.readUTF(); // customer's name
                    String cMobileNum = raf_concreteItr.readUTF(); // customer's mobile num
                    boolean cAcceptAds = raf_concreteItr.readBoolean(); // customer's is accepting

                    // creating the product
//                    this.indexOflastProductReturned++;
                    return new Product(timeAdded, desc, barcode,
                            costToStore, priceSold, new Customer(cName, cMobileNum, cAcceptAds));
                } catch (IOException e) {
                    e.printStackTrace();
                    this.lastReturnedProduct = new Product("__failed__ In file Iterator (line 234/142)");
                    return lastReturnedProduct;
                }

            }
        }

        @Override
        public void remove() {
            // delete the last element returned by the iterator
            try {
                if (raf_concreteItr.getFilePointer() == 0L) {
                    throw new IllegalStateException();
                }
                // backup data from pointer to the end of the file
                System.err.println((TAG +  ", remove: " + "\traf.lengh = " + raf_concreteItr.length() + " raf.getFilePointer=" + raf_concreteItr.getFilePointer()));
                byte[] temp = new byte[(int) (raf_concreteItr.length() - raf_concreteItr.getFilePointer())]; // creating buffer
//                System.err.println((TAG + ", remove: bufferSize  = " + (int) (raf_concreteItr.length() - raf_concreteItr.getFilePointer())));
                raf_concreteItr.read(temp); // reading the reset of the file into buffer

                // return to the position b4 last element was read.
//                System.err.println((TAG + ", remove: pointerToB4LastElement: " + pointerToB4LastReturnedElement));
                raf_concreteItr.seek(pointerToB4LastReturnedElement);

                // overWrite over the element we deleted
                raf_concreteItr.write(temp);

//                System.err.println((TAG +  ", remove: " + "\traf.lengh = " + raf_concreteItr.length() + " raf.getFilePointer=" + raf_concreteItr.getFilePointer()));

                //free the memory
                if (raf_concreteItr.getFilePointer() <=4) // we will ask the user for new map ordering
                    raf_concreteItr.setLength(0);
                else { // just deleting the product
                    raf_concreteItr.setLength(raf_concreteItr.getFilePointer());
                }
                System.err.println((TAG + ", remove: file.length is: ") + file.length());

//                this.indexOflastProductReturned=-1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
        public void replace(Product p){
            try {
                if (raf_concreteItr.getFilePointer() == 0L) {
                    throw new IllegalStateException();
                }
                // backup data from pointer to the end of the file
                byte[] temp = new byte[(int) (raf_concreteItr.length() - raf_concreteItr.getFilePointer())]; // creating buffer
                raf_concreteItr.read(temp); // reading the reset of the file into buffer

                // return to the position b4 last element was read.
                raf_concreteItr.seek(pointerToB4LastReturnedElement);

                writeProductToFile(p, raf_concreteItr);

                // overWrite over the element we deleted
                raf_concreteItr.write(temp);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        public void readInt_skip4Bytes() throws IOException {
            raf.readInt();
        }





//        @Override
//        public void remove() {
//            if (last == -1)
//                throw new IllegalStateException();
//            for (int i = last; i < size - 1; i++)
//                a[i] = a[i + 1];
//            a = Arrays.copyOf(a, --size);
//            cur = last;
//            last = -1;
//        }

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

