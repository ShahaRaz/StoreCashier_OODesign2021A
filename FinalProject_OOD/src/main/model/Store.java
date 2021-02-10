package main.model;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import java.util.*;
import main.interfaces.saleEventListener;

public class Store {
	private String storeName;

	public HashMap<String, Product> productsMap; // <productId,ProductObject>
	private ArrayList<saleEventListener> subscribedCustomers;
	ArrayList<Product> soldProducts;
	ArrayList<Product> inventory; // maybe from file..? needed?

	public Store() {
		this.productsMap = new HashMap<String, Product>();
		soldProducts = new ArrayList<Product>();
		inventory = new ArrayList<Product>();
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setProductsMap(HashMap<String, Product> productsMap) {
		this.productsMap = productsMap;
	}

	public HashMap<String, Product> getProductsMap() {
		return this.productsMap;
	}

	public Product getProductDetails(String id) {
		return productsMap.get(id); // if not exists. return null
	}

	public void addNewProduct(Product p) {
		productsMap.put(p.getBarcode(), p);
		soldProducts.add(p);
	}

	public void orderProducts(String methodOfOrdering) {
		switch (methodOfOrdering) {
//            case "ABup":
//                Collections.sort(productsMap,compareByPID);
		// TODO: 23/01/2021 continue & fixme
		}
	}

	// Implement Observable Pattern, notify all the subscribed customers.
	public void notifyAllCustomers() {
		for (saleEventListener customer : subscribedCustomers) {
			customer.onSaleEvent(this);
		}
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
}
