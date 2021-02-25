package main.model.store;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Product;

public class Store {
	private static final String TAG = "Store";

	public interface KEYS {
		final int ORDER_BY_ABC_UP = 1;
		final int ORDER_BY_ABC_DOWN = 2;
		final int ORDER_BY_INSERT_ORDER = 3;
	}

	private Stack<Command> commandStack = new Stack<>(); // hold all operations
	private Stack<Memento> mementoStack = new Stack<>();

	// Singleton pattern.
	private static Store instance;
	private String storeName = "The Store";
	protected ArrayList<Product> soldProductsArr; // note! will be modified only by using Commands (commandStack)
	protected ArrayList<saleEventListener> subscribedCustomers = new ArrayList<>(); // Add for now initial.

	private int currentMapOrdering;
	protected SortedMap<String, Product> productsMap; // <productId,ProductObject> // treemap// note! will be modified
	// only by using Commands (commandStack)

//    protected ArrayList<Product> soldProductsArr; // note! will be modified only by using Commands (commandStack)
//    //// soldProductsArr - not listed in the system requirements, but we implement this for possible future use
//    protected ArrayList<saleEventListener> subscribedCustomers;

	protected FileHandler theFile;

	private Store() {
		/**
		 * 1. check if the file is empty 2a. if it is, ask the user for the order he
		 * wants for the products then create a new map with the wanted order
		 *
		 * 2b. if it contains map...???? how would we know which kind of map is in the
		 * file?? maybe save an integer b4 the map, that holds one of the values from
		 * KEYS.SORT_BY??
		 *
		 */
		this.theFile = new FileHandler();

		// Read map from file:
		currentMapOrdering = theFile.readMapOrdering(); // KEYS.ORDER_BY..
		if (currentMapOrdering == -1) { // Note! -1 means that the file is Empty
//			this.productsMap = null;
			this.productsMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>());
			// ask user for map order technique
			// will be called when view asks for the map
		} else { // _____________________ INIT PRODUCT MAP FROM FILE
					// _____________________________
			this.productsMap = getNewEmptyMap(currentMapOrdering);
			theFile.readMapFromFile(productsMap, true);
		}

//        this.productsMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>());
//        currentMapOrdering = KEYS.ORDER_BY_ABC_UP; // TODO change me and the line above to be dynamic

		this.soldProductsArr = new ArrayList<Product>(); // am i needed?

//        theFile.readMapFromFile(this.productsMap, true);
//		if (this.productsMap.isEmpty())
//			askUserForMapOrder
	}

	public static Store getInstance() {
		if (instance == null) // if it is the first init of the store.
			instance = new Store();
		return instance;
	}

	public String getStoreName() {
		return storeName;
	}

	public Set<Map.Entry<String, Product>> getProductsSet() {
		/// TODO: Create a copy of this set, and move it to the controller.
//        SortedMap<String, Product> aCopyOfLocalMap = copyMap(this.productsMap, this.currentMapOrdering);
		return this.productsMap.entrySet();
	}

	public ArrayList<saleEventListener> getSubscribedCustomers() {
		return subscribedCustomers;
	}

	public void setProductsMap(SortedMap<String, Product> productsMap,int currentMapOrdering) {
		this.productsMap = productsMap;
		this.currentMapOrdering = currentMapOrdering;
	}

	public static SortedMap<String, Product> getNewEmptyMap(int mapKind_KEYS) {
		SortedMap<String, Product> newMap; // LinkedHashMap cannot be cast to class SortedMap
		switch (mapKind_KEYS) {
		case KEYS.ORDER_BY_ABC_UP:
			newMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>());// default comparator.
			break;
		case KEYS.ORDER_BY_ABC_DOWN:
			newMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>(new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s2.compareTo(s1); // reversed order
				}
			}));
			break;
		case KEYS.ORDER_BY_INSERT_ORDER:
			newMap = (SortedMap<String, Product>) new LinkedHashMap<String, Product>(); // insertion order
			break;
		default:
			System.err.println((TAG + ", getNewEmptyMap: \t" + "Choose map ordering by Store.KEYS.ORDER_BY_... \nselected ABC_UP by default."));
			newMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>());
		}
		return newMap;
	}

	public static SortedMap<String, Product> copyMap(SortedMap<String, Product> source, int mapKind_KEYS) {
		SortedMap<String, Product> newCopy = getNewEmptyMap(mapKind_KEYS);
		if (source==null)
			System.err.println((TAG + ", copyMap: source.entryset=nul!! "));
		for (Map.Entry<String, Product> pair : source.entrySet()) {
			if (pair != null) {
				Product newDupOfProduct = new Product(pair.getValue()); // a new hard copy ( NOT REFERENCE )
				newCopy.put(newDupOfProduct.getBarcode(), newDupOfProduct);
			}
		}
		return newCopy;
	}
	public static ArrayList<Product> copyArray(ArrayList<Product> source, int numOfElementsToCopy) {
		ArrayList<Product> newCopy = new ArrayList<>();
		for (int i=0; i<numOfElementsToCopy ; i++) {
			if (source.get(i) != null) {
				Product newDupOfProduct = new Product(source.get(i)); // a new hard copy ( NOT REFERENCE )
				newCopy.add(newDupOfProduct);
			}
		}
		return newCopy;
	}




	public Product getProductDetails(String id) {
		if (id == null) {
			System.err.println(" String ID IS NULL!");
			return null;
		} else {
			if (productsMap.containsKey(id))
				return productsMap.get(id); // if not exists. return null
			else
				return null;
		}
	}

	public void addNewProduct(Product p) {
		// Access only from command
		Cmnd_AddProduct commandAdd = new Cmnd_AddProduct(p, productsMap, soldProductsArr, theFile, currentMapOrdering,subscribedCustomers);
		commandStack.add(commandAdd);
		commandAdd.execute();

	}

	public void removeProduct(Product p) {
		Cmnd_RemoveProduct commandRemove = new Cmnd_RemoveProduct(p, soldProductsArr, productsMap, theFile,
				currentMapOrdering,subscribedCustomers);
		commandStack.add(commandRemove);
		commandRemove.execute();
		theFile.removeProductFromFile(p); // TODO: move me into command
	}

	public void removeAllProducts() {
		Cmnd_RemoveAllProducts cmnd_removeAllProducts = new Cmnd_RemoveAllProducts // TODO for now sending reference, later check how to deal with it.
				(this.productsMap,this.currentMapOrdering,this.soldProductsArr,theFile,subscribedCustomers);
		commandStack.add(cmnd_removeAllProducts);
		cmnd_removeAllProducts.execute();


//		Cmnd_removeProduct commandRemove = new Cmnd_removeProduct(p, soldProductsArr, productsMap, theFile,
//				currentMapOrdering);
//		commandStack.add(commandRemove);
//		commandRemove.execute();
//		theFile.removeProductFromFile(p);
	}

	public String undoLastAction() {
		if (commandStack.empty()) {
			return "UNDO FAILED";
		} else {
			commandStack.pop().undo(); // popping the last command entered the queue and undoing it.
			return "Successfully reverted last action";
		}
	}

	// Adding the current state to Stack.
	public void addMemento() {
		mementoStack.add(createMemento());
	}

	// Reverted state.
	public String getLastState() {
		if (mementoStack.isEmpty())
			return "Reverted state FAILED";
		else {
			setMemento(mementoStack.pop());
			return "Successfully reverted state";
		}
	}

	// Creating new state.
	private Memento createMemento() {
		return new Memento(commandStack, storeName, productsMap, soldProductsArr, subscribedCustomers, theFile);
	}

	// Return the saved state.
	private void setMemento(Memento m) {
		commandStack = m.getCommandStack();
		storeName = m.getStoreName();
		productsMap = m.getProductsMap();
		soldProductsArr = m.getSoldProductsArr();
		subscribedCustomers = m.getSubscribedCustomers();
		theFile = m.getTheFile();
	}

	// Implement Observable Pattern, notify all the subscribed customers.
	public void notifyAllCustomers() {
		for (saleEventListener customer : subscribedCustomers) {
			customer.onSaleEvent(this);
		}
	}

	public static class Memento {
		private Stack<Command> commandStack = new Stack<>(); // hold all operations
		// Singleton pattern.
		private static Store instance = getInstance();
		private String storeName;
		protected SortedMap<String, Product> productsMap = Collections
				.synchronizedSortedMap(new TreeMap<String, Product>()); // <productId,ProductObject> // treemap// note!
																		// will be
		// modified
		// only by using Commands (commandStack)
		protected ArrayList<Product> soldProductsArr; // note! will be modified only by using Commands (commandStack)
		protected ArrayList<saleEventListener> subscribedCustomers;
		protected FileHandler theFile;

		public Memento(Stack<Command> commandStack, String storeName, SortedMap<String, Product> productsMap,
				ArrayList<Product> soldProductsArr, ArrayList<saleEventListener> subscribedCustomers,
				FileHandler theFile) {

			Collections.copy(commandStack, this.commandStack);
			this.storeName = String.copyValueOf(storeName.toCharArray());
			initMap(productsMap.entrySet());// Copy one by one.
			this.soldProductsArr = new ArrayList<Product>(soldProductsArr);
//			this.subscribedCustomers = new ArrayList<saleEventListener>(subscribedCustomers);
			this.theFile = theFile;
		}

		private void initMap(Set<Entry<String, Product>> productsMap) {
			for (Map.Entry<String, Product> p : productsMap) {
				if (p != null) {
					this.productsMap.put(p.getKey(), p.getValue());
				}
			}
		}

		private SortedMap<String, Product> copyMap(Set<Entry<String, Product>> source, int mapKind) {
			SortedMap<String, Product> newCopy;
			switch (mapKind) {
			case KEYS.ORDER_BY_ABC_UP:
				newCopy = Collections.synchronizedSortedMap(new TreeMap<String, Product>());
				break;
			case KEYS.ORDER_BY_ABC_DOWN:
				newCopy = Collections.synchronizedSortedMap(new TreeMap<String, Product>(new Comparator<String>() {
					@Override
					public int compare(String s1, String s2) {
						return s2.compareTo(s1);
					}
				}));
				break;
			case KEYS.ORDER_BY_INSERT_ORDER:
				newCopy = (SortedMap<String, Product>) new LinkedHashMap<String, Product>();
				break;
			default:
				System.err.println("Choose map ordering by Store.KEYS.ORDER_BY_... \nselected ABC_UP by default.");
				newCopy = Collections.synchronizedSortedMap(new TreeMap<String, Product>());
			}

			for (Map.Entry<String, Product> p : source) {
				if (p != null) {
					newCopy.put(p.getKey(), p.getValue());
				}
			}
			return newCopy;
		}

		public Stack<Command> getCommandStack() {
			return commandStack;
		}

		public void setCommandStack(Stack<Command> commandStack) {
			this.commandStack = commandStack;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public SortedMap<String, Product> getProductsMap() {
			return productsMap;
		}

		public void setProductsMap(SortedMap<String, Product> productsMap) {
			this.productsMap = productsMap;
		}

		public ArrayList<Product> getSoldProductsArr() {
			return soldProductsArr;
		}

		public void setSoldProductsArr(ArrayList<Product> soldProductsArr) {
			this.soldProductsArr = soldProductsArr;
		}

		public ArrayList<saleEventListener> getSubscribedCustomers() {
			return subscribedCustomers;
		}

		public void setSubscribedCustomers(ArrayList<saleEventListener> subscribedCustomers) {
			this.subscribedCustomers = subscribedCustomers;
		}

		public FileHandler getTheFile() {
			return theFile;
		}

		public void setTheFile(FileHandler theFile) {
			this.theFile = theFile;
		}

	}

	// public static Comparator<Product> compareByTimeEntered = new
	// Comparator<Product>() {
//		@Override
//		public int compare(Product p1, Product p2) {
//			return (int) (p1.getTimeMilis() - p2.getTimeMilis());
//		}
//	};
	public void orderProducts(int methodOfOrdering) {
//		//////// Lec6 1:24:00 Comparator for TreeSet (Very similar to Comparator for TreeMap)
//		//// Lec6 20:00 how to comparator works 23:30
//		/// lec 6 53:00
//		// lec6 1:18:00
//		// inside the TreeMap 'add' method, it already inserts the organs in sorted way!
//		switch (methodOfOrdering) {
//			case KEYS.ORDER_BY_ABC_UP:
//				Collections.sort(productsMap, compareByPidUp);
//				break;
//			case KEYS.ORDER_BY_ABC_DOWN:
//
//				break;
//
//			case KEYS.ORDER_BY_INSERT_ORDER:
//
//				break;
//
////		 TODO: 23/01/2021 continue & fixme
//		}
	}


	// Inner Comparators for Sorts //
	public static Comparator<String> compareByPID = (s1, s2) -> s1.compareTo(s2);
	public static Comparator<Product> compareByTimeEntered = (p1, p2) -> (int) (p1.getTimeMilis() - p2.getTimeMilis());

	// Inner Comparators for Sorts //
	public static Comparator<HashMap.Entry<String, Product>> compareByPidUp = new Comparator<>() {

		@Override
		public int compare(Map.Entry<String, Product> stringProductEntry, Map.Entry<String, Product> t1) {
			return 0;
		}
	};

//	public static Comparator<Map.Entry<String, Product>> compareByPidDown = new Comparator<Map.Entry<String, Product>>() {
//		@Override
//		public int compare(Map.Entry<String, Product> entry1, Map.Entry<String, Product> entry2) {
////			return entry1.getKey().compareTo(entry2.getKey());
//			return (int) entry1.getValue().getTimeMilis() - (int) entry2.getValue().getTimeMilis();
//		}
//
//	public static Comparator<Map.Entry<String, Product>> compareByPidDown = (entry1,
//			entry2) -> (int) entry1.getValue().getTimeMilis() - (int) entry2.getValue().getTimeMilis();

}