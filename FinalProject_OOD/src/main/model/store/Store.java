package main.model.store;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import java.util.*;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Model;
import main.model.Product;

public class Store {

	public interface KEYS {
		final int ORDER_BY_ABC_UP = 1;
		final int ORDER_BY_ABC_DOWN = 2;
		final int ORDER_BY_INSERT_ORDER = 3;
	}

	private Stack<Command> commandStack = new Stack<>(); // hold all operations

	// Singleton pattern.
	private static Store instance;
	private String storeName;

	protected SortedMap<String, Product> productsMap; // <productId,ProductObject> // treemap// note! will be modified
														// only by using Commands (commandStack)

	protected ArrayList<Product> soldProductsArr; // note! will be modified only by using Commands (commandStack)
	protected ArrayList<saleEventListener> subscribedCustomers;

	private Model model;
	FileHandler theFile;

	private Store(Model model) {
		this.productsMap = Collections.synchronizedSortedMap(new TreeMap<String, Product>());
		soldProductsArr = new ArrayList<Product>(); // am i needed?
		this.model = model;
		theFile = new FileHandler();
	}

	public static Store getInstance(Model model) {
		if (instance == null) // if it is the first init of the store.
			instance = new Store(model);
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

	public Product getProductDetails(String id) {
		if (productsMap.containsKey(id))
			return productsMap.get(id); // if not exists. return null
		else
			return null;
	}

	public void addNewProduct(Product p) {
		// Access only from command
		Cmnd_AddProduct commandAdd = new Cmnd_AddProduct(p, productsMap, soldProductsArr);

		commandStack.add(commandAdd);
		commandAdd.execute();
	}

	public void removeProduct(Product p) {
		Cmnd_removeProduct commandRemove = new Cmnd_removeProduct(p, productsMap, soldProductsArr);
		commandStack.add(commandRemove);
		commandRemove.execute();
	}

	public void undoLastAction() {
		if (commandStack.empty())
			model.fireOperationFailed("Unable to undo action", "UNDO FAILED");
		commandStack.pop().undo(); // popping the last command entered the queue and undoing it.
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
	public static Comparator<String> compareByPID = new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	};

	public static Comparator<Product> compareByTimeEntered = new Comparator<Product>() {
		@Override
		public int compare(Product p1, Product p2) {
			return (int) (p1.getTimeMilis() - p2.getTimeMilis());
		}
	};

	// Inner Comparators for Sorts //
	public static Comparator<HashMap.Entry<String, Product>> compareByPidUp = new Comparator<>() {

		@Override
		public int compare(Map.Entry<String, Product> stringProductEntry, Map.Entry<String, Product> t1) {
			return 0;
		}
	};

	public static Comparator<Map.Entry<String, Product>> compareByPidDown = new Comparator<Map.Entry<String, Product>>() {
		@Override
		public int compare(Map.Entry<String, Product> entry1, Map.Entry<String, Product> entry2) {
//			return entry1.getKey().compareTo(entry2.getKey());
			return (int) entry1.getValue().getTimeMilis() - (int) entry2.getValue().getTimeMilis();
		}

	};

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
