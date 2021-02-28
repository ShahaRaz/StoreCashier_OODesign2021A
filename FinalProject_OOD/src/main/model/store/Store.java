package main.model.store;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import main.interfaces.saleEventListener;
import main.model.Customer;
import main.model.FileHandler;
import main.model.Product;

public class Store {
	private static final String TAG = "Store";

	public interface KEYS {
		final String STORE_NAME = "atGadi's";
		final String MOMENTO_FAILED_REVERT = "Reverted state FAILED";

		final int ORDER_BY_ABC_UP = 1;
		final int ORDER_BY_ABC_DOWN = 2;
		final int ORDER_BY_INSERT_ORDER = 3;
	}

	private Stack<Command> commandStack = new Stack<>(); // hold all operations
	private Stack<Memento> mementoStack = new Stack<>();

	// Singleton pattern.
	private static Store instance;
	private String storeName = KEYS.STORE_NAME;
	protected ArrayList<Product> soldProductsArr; // note! will be modified only by using Commands (commandStack)
	protected ArrayList<saleEventListener> subscribedCustomers; // Add for now initial.

	private int currentMapOrdering;
	protected Map<String, Product> productsMap; // <productId,ProductObject> // treemap// note! will be modified
	// only by using Commands (commandStack)

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
		this.subscribedCustomers = new ArrayList<>();
		this.theFile = new FileHandler();

		// Read map from file:
		currentMapOrdering = theFile.readMapOrdering(); // KEYS.ORDER_BY..
		if (currentMapOrdering == -1) { // Note! -1 means that the file is Empty
			this.productsMap = null;
//			this.productsMap = Collections.synchronizedMap(new TreeMap<String, Product>());
			// ask user for map order technique
			// will be called when view asks for the map
		} else { // _____________________ INIT PRODUCT MAP FROM FILE
			// _____________________________
			this.productsMap = getNewEmptyMap(currentMapOrdering);
			theFile.readMapFromFile(productsMap, true);
			// 3. read subscribedCustomers from file
			subscribedCustomers = getListenersFromMap(productsMap,null);
		}
		this.soldProductsArr = new ArrayList<Product>(); // am i needed?
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
		return this.productsMap.entrySet();
	}

	// Implement Observable Pattern, notify all the subscribed customers.
	public ArrayList<saleEventListener> getSubscribedCustomers() {
		return subscribedCustomers;
	}

	public void setProductsMap(Map<String, Product> productsMap, int currentMapOrdering) {
		this.productsMap = productsMap;
		this.currentMapOrdering = currentMapOrdering;
	}

	public static Map<String, Product> getNewEmptyMap(int mapKind_KEYS) {
		Map<String, Product> newMap; // LinkedHashMap cannot be cast to class Map
		switch (mapKind_KEYS) {
		case KEYS.ORDER_BY_ABC_UP:
			newMap = Collections.synchronizedMap(new TreeMap<String, Product>());// default comparator.
			break;
		case KEYS.ORDER_BY_ABC_DOWN:
			newMap = Collections.synchronizedMap(new TreeMap<String, Product>(new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s2.compareTo(s1); // reversed order
				}
			}));
			break;
		case KEYS.ORDER_BY_INSERT_ORDER:
			newMap = (Map<String, Product>) new LinkedHashMap<String, Product>(); // insertion order
			break;
		default:
			System.err.println((TAG + ", getNewEmptyMap: \t" + "selected ABC_UP by default."));
			newMap = Collections.synchronizedMap(new TreeMap<String, Product>());
		}
		return newMap;
	}

	/**
	 * creating a deep copy of the map
	 *
	 * @param source
	 * @param mapKind_KEYS
	 * @return a complete new instance of map sorted by mapKind & holds new clones
	 *         of the source instances
	 */
	public static Map<String, Product> copyMap(Map<String, Product> source, int mapKind_KEYS) {
		Map<String, Product> newCopy = getNewEmptyMap(mapKind_KEYS); // NEW MAP
		// Filling up the map using copy constructors (DEEP COPY, Customer as-well)
		if (source == null)
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
		for (int i = 0; i < numOfElementsToCopy; i++) {
			if (source.get(i) != null) {
				Product newDupOfProduct = new Product(source.get(i)); // a new hard copy ( NOT REFERENCE )
				newCopy.add(newDupOfProduct);
			}
		}
		return newCopy;
	}

	public Product getProductDetails(String id) {
		if (id == null) {
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
		Cmnd_AddProduct commandAdd = new Cmnd_AddProduct(p, productsMap, soldProductsArr, theFile, currentMapOrdering,
				subscribedCustomers);
		commandStack.add(commandAdd);
		commandAdd.execute();

	}

	public void removeProduct(Product p) {
		Cmnd_RemoveProduct commandRemove = new Cmnd_RemoveProduct(p, soldProductsArr, productsMap, theFile,
				currentMapOrdering, subscribedCustomers);
		commandStack.add(commandRemove);
		commandRemove.execute();
	}

	public void removeAllProducts() {
		Cmnd_RemoveAllProducts cmnd_removeAllProducts = new Cmnd_RemoveAllProducts(this.productsMap,
				this.currentMapOrdering, this.soldProductsArr, theFile, subscribedCustomers);
		commandStack.add(cmnd_removeAllProducts);
		cmnd_removeAllProducts.execute();
	}

	public String undoLastAction() {
		if (commandStack.empty()) {
			return "UNDO FAILED";
		} else {
			commandStack.pop().undo(); // popping the last command entered the queue and undoing it.
			System.err.println((TAG + ", undoLastAction: promotionListeners = " + this.subscribedCustomers.size()));
			return "Successfully reverted last action";
		}
	}

	public static ArrayList<Product> copyProductsArr(ArrayList<Product> source) {
		ArrayList<Product> newCopy = new ArrayList<>();
		Product tmpProduct;
		for (Product p : source) {
			tmpProduct = new Product(p); // Copy Constructor, deep copping
			newCopy.add(tmpProduct);
		}
		return newCopy;
	}

	public static ArrayList<saleEventListener> copySaleListenersList(ArrayList<saleEventListener> source) {
		ArrayList<saleEventListener> newCopy = new ArrayList<>();
		Customer tmpCustomer;
		for (saleEventListener c : source) {
			tmpCustomer = new Customer((Customer) c); // Copy Constructor, deep copping
			newCopy.add(tmpCustomer);
		}
		return newCopy;
	}

	public static ArrayList<saleEventListener> getListenersFromMap(Map<String, Product> theMap,ArrayList<saleEventListener> passNullForNewCopy) {
		ArrayList<saleEventListener> result;
		if (passNullForNewCopy == null) {
			result = new ArrayList<saleEventListener>();
		}
		else{
			result = passNullForNewCopy;
		}
		for (Map.Entry<String, Product> pair : theMap.entrySet()) {
			if (pair != null) {
				saleEventListener tmpCustomer = (Customer) (pair.getValue().getCustomer()); // a new hard copy ( NOT
																							// REFERENCE )
				if (((Customer) tmpCustomer).getIsAcceptingPromotions()) {
					result.add(tmpCustomer);
				}
			}
		}
		return result;
	}

	// ________________________________________ MEMENTO
	// ________________________________________
	// Adding the current state to Stack.
	public void addMemento() {
		mementoStack.add(createMemento());
	}

	// Reverted state.
	public String getLastState() {
		if (mementoStack.isEmpty())
			return KEYS.MOMENTO_FAILED_REVERT;
		else { // popping the last saved memento.
			setMemento(mementoStack.pop());
			return "Successfully reverted state";
		}
	}

	// Creating new state.
	private Memento createMemento() {
		return new Memento(commandStack, storeName, productsMap, soldProductsArr, subscribedCustomers, theFile,
				currentMapOrdering);
	}

	// Return to the saved state.
	private void setMemento(Memento m) {
		commandStack = m.getCommandStack();
		storeName = m.getStoreName();
		productsMap = m.getProductsMap();
		soldProductsArr = m.getSoldProductsArr();
		subscribedCustomers = m.getSubscribedCustomers();
		theFile = m.getTheFile();
		/**
		 * _____ holding on to the last memento _____ 1. setMemento ( Set the state ) 2.
		 * immediately create a new memento(hard copy) from the state that just been set
		 */
		if (mementoStack.size() == 0)
			addMemento();
	}

	public static class Memento {
		private Stack<Command> commandStack = new Stack<>(); // hold all operations
		// Singleton pattern.
		private static Store instance = getInstance();
		private String storeName;
		protected Map<String, Product> productsMap = Collections.synchronizedMap(new TreeMap<String, Product>()); // <productId,ProductObject>
																													// treemap
																													// note!
		// will be modified
		// only by using Commands (commandStack)
		protected ArrayList<Product> soldProductsArr; // note! will be modified only by using Commands (commandStack)
		protected ArrayList<saleEventListener> subscribedCustomers;
		protected FileHandler theFile;
		private int currentMapOrdering;

		public Memento(Stack<Command> commandStack, String storeName, Map<String, Product> productsMap,
				ArrayList<Product> soldProductsArr, ArrayList<saleEventListener> subscribedCustomers,
				FileHandler theFile, int currentMapOrdering) {

			Collections.copy(commandStack, this.commandStack); // shallow copy. commandStack
			this.storeName = String.copyValueOf(storeName.toCharArray());// storeName
			this.soldProductsArr = Store.copyProductsArr(soldProductsArr);// soldProductsArr
			this.subscribedCustomers = Store.copySaleListenersList(subscribedCustomers);// subscribed Customers
			this.theFile = theFile;// not copping(avoiding filling up the storage) file
			this.productsMap = Store.copyMap(productsMap, currentMapOrdering);// deep map
			this.currentMapOrdering = currentMapOrdering;
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

		public Map<String, Product> getProductsMap() {
			return productsMap;
		}

		public void setProductsMap(Map<String, Product> productsMap) {
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
}