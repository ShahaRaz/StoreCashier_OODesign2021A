package main.model;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * references: Lectures_Arnon.03 - 03.11.2020 Q3 (save objects to random access
 * file) Lectures_Arnon.10 - 22.12.2020 Q2 (Iterator & Remove products from
 * collection)
 */
public class FileHandler implements Iterable<Product> {
	private File file;
//	private boolean isAppendableFile;
//	private RandomAccessFile raf;
	private static final String FILE_NAME = "products.txt"; // Yes, it ends with .txt while its a binary file. (asked by
															// the professor)
	private static final String TAG = "FileHandler";

	public FileHandler() {
		this.file = new File(FILE_NAME);
//		this.isAppendableFile = file.exists();

		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {

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
	public void saveMapToFile(Map<String, Product> theMap, int mapOrdering_KEYS) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			writeMapOrderingToFile(mapOrdering_KEYS);// if products already in the map, it's already ordered
			// First 4bytes [0,3] will indicate which ordering are we using

			raf.seek(4); // stepping over the mapOrderingIndicator (int, 4bytes)
			for (Map.Entry<String, Product> pair : theMap.entrySet()) {
				Product tmp = (Product) pair.getValue(); // gets the product
				writeProductToFile(tmp, raf);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeProductToFile(Product tmp, RandomAccessFile raf) throws IOException {
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
	public void readMapFromFile(Map<String, Product> theMap, boolean isClearingMapB4) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			if (isClearingMapB4) {
				theMap.clear(); // remove all elements from map
			}
			// _____________________________________________ passing over the readInt
			// NOTE! bytes 0-3 in the file are used for the mapOrder_KEYS,
			// raf.seek(3); // go to begging of file
			if (file.length() == 0)
				return;

			raf.seek(0);
			raf.readInt();

			Iterator i = new ConcreteIterator(file);
			while (i.hasNext()) {
				Product p = (Product) i.next();
				theMap.put(p.getBarcode(), p);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeAllProducts() {
		Iterator i = new ConcreteIterator(file);

		while (i.hasNext()) {
			Product p = (Product) i.next();
			i.remove();
		}
	}

	public int readMapOrdering() {
		if (file.length() == 0)
			return -1;// -1 means the file is empty

		int theOrderingInTheFile = -1; // if -1, something went wrong, or empty
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.seek(0);
			theOrderingInTheFile = raf.readInt(); // raf's pointer will go to the 4th byte (after reading [0,3]
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return theOrderingInTheFile; // Store.KEYS.ORDER_BY_...
	}

	public boolean writeMapOrderingToFile(int mapOrdering_KEYS) {
		if (!(mapOrdering_KEYS >= 1 && mapOrdering_KEYS <= 3)) {
			System.err.println((TAG + ", writeMapOrderingToFile: Error, ordering has no valid value Applying 1 "));
			mapOrdering_KEYS = 1;
		}
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
				return; // won't hold 2 equal elements in the map anyway.
			}
		}
	}

	private class ConcreteIterator implements Iterator<Product> {
		private RandomAccessFile raf_concreteItr;
		private Product lastReturnedProduct;
		private long pointerToB4LastReturnedElement;

		public ConcreteIterator(File file) {
			try {
				raf_concreteItr = new RandomAccessFile(file, "rw");
				pointerToB4LastReturnedElement = 4; // maybe change me
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		@Override
		public boolean hasNext() {
			try {
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
					pointerToB4LastReturnedElement = raf_concreteItr.getFilePointer();

					if (pointerToB4LastReturnedElement == 0) {
						raf_concreteItr.readInt(); // skipping over the first 4 bytes
						pointerToB4LastReturnedElement = raf_concreteItr.getFilePointer();
					}
					String barcode = raf_concreteItr.readUTF(); // Barcode
					String desc = raf_concreteItr.readUTF(); // DESCRIPTION
					int priceSold = raf_concreteItr.readInt(); // Price sold
					int costToStore = raf_concreteItr.readInt(); // Cost to store
					long timeAdded = raf_concreteItr.readLong(); // Time Added
					// _______ Read Costumer ____
					String cName = raf_concreteItr.readUTF(); // customer's name
					String cMobileNum = raf_concreteItr.readUTF(); // customer's mobile num
					boolean cAcceptAds = raf_concreteItr.readBoolean(); // customer's is accepting

					return new Product(timeAdded, desc, barcode, costToStore, priceSold,
							new Customer(cName, cMobileNum, cAcceptAds));
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
				byte[] temp = new byte[(int) (raf_concreteItr.length() - raf_concreteItr.getFilePointer())]; // creating

				raf_concreteItr.read(temp); // reading the reset of the file into buffer

				// return to the position b4 last element was read.
				raf_concreteItr.seek(pointerToB4LastReturnedElement);

				// overWrite over the element we deleted
				raf_concreteItr.write(temp);

				if (raf_concreteItr.getFilePointer() > 5) { // just deleting the product
					raf_concreteItr.setLength(raf_concreteItr.getFilePointer());
					raf_concreteItr.seek(pointerToB4LastReturnedElement);
				} else { // only the mapOrderingIndicator left in the file
					raf_concreteItr.setLength(0); // we will ask the user for new map ordering
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
