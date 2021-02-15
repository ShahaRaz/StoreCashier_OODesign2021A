package main.model;

/*
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */

import main.listeners.LogicListenable;
import main.model.store.Store;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Model {
	private Store store;
	private ArrayList<LogicListenable> allListeners;

	public Model() {
		this.allListeners = new ArrayList<>();
		store = Store.getInstance(this);
		// TODO add here products from file
	}

	public void registerListener(LogicListenable l) {
		allListeners.add(l);
	}

	public void addProduct(Product p) {
		if (store.getProductDetails(p.getBarcode()) == null) { // product isn't yet in hashMap
			String problemsWithProduct = p.isValidProduct(this); // return the first error found
			if (problemsWithProduct.length() == 0) { // no errors found
				store.addNewProduct(p);
				fireProductAdded(p);
			} else {
				fireProductNotGood(p, problemsWithProduct);
			}
		} else { // note! (got here if) product is already in map already, which means it's valid
			store.addNewProduct(p);
			fireProductAdded(p);
		}
	}

	public void removedProduct(Product p) {
		if (store.getProductDetails(p.getBarcode()) == null) {// product not in store.
			fireOperationFailed("No product with such id", "product wasn't found");
		}
		else { // product is in our database
			store.removeProduct(p);
			fireProductRemoved(p);
		}
		// TODO: Return fireProductRemoved
		// firing a return statement from within the store.
	}

	private void fireProductRemoved(Product p) {
		for (LogicListenable l : allListeners) {
			l.modelRemovedProduct(p);
		}
	}

	public void undoLastAction() {
		store.undoLastAction();
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

//	private void fireSendProductsArrToView(ArrayList<Product> products) {
//		for (LogicListenable l : allListeners) {
//			l.modelSendProductsList(products);
//		}
//	}

	private void fireSendProductsArrToView(Set<Map.Entry<String, Product>> products) {
		for (LogicListenable l : allListeners) {
			l.modelSendProductsList(products);
		}
	}

	public void fireOperationFailed(String errorMassage, String elaborate) {
		for (LogicListenable l : allListeners) {
			l.modelFailedOperation(errorMassage, elaborate);
		}
	}

	public void sendAllProductsToView() {
		fireSendProductsArrToView(store.getProductsSet());
	}

	public void getProduct(String searchMe) {
		fireGetProduct(searchMe);
	}

	private void fireGetProduct(String id) {
		for (LogicListenable l : allListeners) {
			if (store.getProductDetails(id) != null)
				l.modelSendProduct(store.getProductDetails(id));
		}
	}
}
