package main.model.store;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import java.util.*;
import java.util.Map.Entry;

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

	protected SortedMap<String, Product> productsMap; // <productId,ProductObject> // treemap// note! will be modified
														// only by using Commands (commandStack)

	protected ArrayList<Product> soldProductsArr; // note! will be modified only by using Commands (commandStack)
	protected ArrayList<saleEventListener> subscribedCustomers = new ArrayList<>(); // Add for now initial.

	protected FileHandler theFile;

	private Store() {
		this.productsMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>());
		this.soldProductsArr = new ArrayList<Product>(); // am i needed?
		this.theFile = new FileHandler();
		theFile.readMapFromFile(this.productsMap, true);
	}

	public static Store getInstance() {
		if (instance == null) // if it is the first init of the store.
			instance = new Store();
		return instance;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setProductsMap(TreeMap<String, Product> productsMap) {
		this.productsMap = productsMap;
	}

	public Set<Map.Entry<String, Product>> getProductsSet() {
		/// TODO: Create a copy of this set, and move it to the controller.
		return this.productsMap.entrySet();
	}

	public ArrayList<saleEventListener> getSubscribedCustomers() {
		return subscribedCustomers;
	}

	public Product getProductDetails(String id) {
		if (id == null) {
			System.err.println(" String ID IS NULL!");
			return null;
		} else {
			System.out.println(productsMap.containsKey(id) + " Store, 76, ID is: " + id);
			if (productsMap.containsKey(id))
				return productsMap.get(id); // if not exists. return null
			else
				return null;
		}
	}

	public void addNewProduct(Product p) {
		// Access only from command
		Cmnd_AddProduct commandAdd = new Cmnd_AddProduct(p, productsMap, soldProductsArr, theFile);
		commandStack.add(commandAdd);
		commandAdd.execute();
		theFile.saveMapToFile(this.productsMap, true);
	}

	public void removeProduct(Product p) {
		Cmnd_removeProduct commandRemove = new Cmnd_removeProduct(p, soldProductsArr, productsMap, theFile);
		commandStack.add(commandRemove);
		commandRemove.execute();
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
//	};
	public static Comparator<Map.Entry<String, Product>> compareByPidDown = (entry1,
			entry2) -> (int) entry1.getValue().getTimeMilis() - (int) entry2.getValue().getTimeMilis();

	public static class Memento {
		private Stack<Command> commandStack = new Stack<>(); // hold all operations
		// Singleton pattern.
		private static Store instance = getInstance();
		private String storeName;
		protected SortedMap<String, Product> productsMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>()); // <productId,ProductObject> // treemap// note! will be
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

//	public static Comparator<Product> compareByTimeEntered = new Comparator<Product>() {
//		@Override
//		public int compare(Product p1, Product p2) {
//			return (int) (p1.getTimeMilis() - p2.getTimeMilis());
//		}
//	};

}

//
//	// Sort the list
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
//		public int compare(Map.Entry<String, Integer> o1,
//				Map.Entry<String, Integer> o2)
//		{
//			return (o1.getValue()).compareTo(o2.getValue());
//		}
//	});

//    public static Comparator<Flight> compareByDepDate = new Comparator<Flight>() {
//        @Override
//        public int compare(Flight o1, Flight o2) {
//            String date1 = o1.getDate().toString();
//            String date2 = o2.getDate().toString();
//
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            int res = 0;
//            try {
//                res = sdf.parse(date1).compareTo(sdf.parse(date2));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            if (res != 0)
//                return res;
//            return o1.getDepTime().compareTo(o2.getDepTime());
//
//        }
//    };

//	public void orderProducts(int methodOfOrdering) {
//		switch (methodOfOrdering) {
//			case KEYS.ORDER_BY_ABC_UP:
//				Collections.sort(productsMap, new Comparator<HashMap.Entry<String,Product>>() {
//					@Override
//					public int compare(Map.Entry<String, Product> stringProductEntry, Map.Entry<String, Product> t1) {
//						return 0;
//					}
//
//
//				});
//
////		 TODO: 23/01/2021 continue & fixme
//		}
//	}

//
//	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
//	{
//		// Create a list from elements of HashMap
//		List<Map.Entry<String, Integer> > list =
//				new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
//
//		// Sort the list
//		Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
//			public int compare(Map.Entry<String, Integer> o1,
//							   Map.Entry<String, Integer> o2)
//			{
//				return (o1.getValue()).compareTo(o2.getValue());
//			}
//		});
//
//		// put data from sorted list to hashmap
//		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
//		for (Map.Entry<String, Integer> aa : list) {
//			temp.put(aa.getKey(), aa.getValue());
//		}
//		return temp;
//	}
//
//	// Driver Code
//	public static void main(String[] args)
//	{
//
//		HashMap<String, Integer> hm = new HashMap<String, Integer>();
//
//		// enter data into hashmap
//		hm.put("Math", 98);
//		hm.put("Data Structure", 85);
//		hm.put("Database", 91);
//		hm.put("Java", 95);
//		hm.put("Operating System", 79);
//		hm.put("Networking", 80);
//		Map<String, Integer> hm1 = sortByValue(hm);
//
//		// print the sorted hashmap
//		for (Map.Entry<String, Integer> en : hm1.entrySet()) {
//			System.out.println("Key = " + en.getKey() +
//					", Value = " + en.getValue());
//		}
//	}
