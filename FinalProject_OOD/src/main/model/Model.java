package main.model;
/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

/*
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */

import main.listeners.LogicListenable;

import java.util.ArrayList;

public class Model {
	private Store store;
	private ArrayList<LogicListenable> allListeners;

	public Model() {
		this.allListeners = new ArrayList<>();
		store = Store.getInstance();
	}

	public void registerListener(LogicListenable l) {
		allListeners.add(l);
	}

	public void addProduct(Product p) {
		if (store.getProductDetails(p.getBarcode()) == null) { // product isn't yet in hashMap
			if (p.isValidProduct(this)) {
				store.addNewProduct(p);
				fireProductAdded(p);
			} else {
				fireProductNotGood(p, "Insert All Product's fields");
			}
			return;
		}
		// note! (got here if) product is already in hashmap already, which means it's
		// valid
		store.addNewProduct(p);
		fireProductAdded(p);

	}

	private void fireProductAdded(Product p) {
		for (LogicListenable l : allListeners) {
			l.modelAddedProduct(p);
		}
	}

	public void fireProductNotGood(Product p, String str) {
		for (LogicListenable l : allListeners) {
			l.modelRejectedProduct(p, str);
		}
	}

	public void removedProduct(Product p) {
//		if (store.productsMap.get(p.getBarcose()).equals(null)) {
//			for (LogicListenable l : allListeners) {
//				l.notifyProductNotExist(p, "The product " + p.getBarcose() + " not exist!");
//			}
//		} else {
		for (LogicListenable l : allListeners) {
			l.modelRemovedProduct(p);
		}

//		}
	}

	private void fireSendProductsArrToView(ArrayList<Product> products) {
		for (LogicListenable l : allListeners) {
			l.modelSendProductsList(products);
		}
	}

	private void fireSaleForCustomer() {
		for (LogicListenable l : allListeners) {
			store.notifyAllCustomers();
		}
	}

}
