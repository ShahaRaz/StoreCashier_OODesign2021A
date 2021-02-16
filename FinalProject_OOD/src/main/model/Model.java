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
				return;
			}
		} else { // note! (got here if) product is already in map already, which means it's valid
			store.addNewProduct(p);
			fireProductAdded(p);
		}
	}

	public void removedProduct(Product p) {
		if (store.getProductDetails(p.getBarcode()) == null) {// product not in store.
			fireOperationFailed("No product with such id", "product wasn't found");
		} else { // product is in our database
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
		String isGood = store.undoLastAction();
		if (isGood.equalsIgnoreCase("UNDO FAILED")) {
			fireOperationFailed("UNDO FAILED", "No Actions to undo");
		} else {
			fireSendMessageToUser("Undo completed!", "product list has been updated");
		}
	}

	private void fireSendMessageToUser(String headline, String content) {

		for (LogicListenable l : allListeners) {
			l.modelSendsMessage(headline, content);
		}
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

	private void fireSendProductsArrToView(Set<Map.Entry<String, Product>> products) {
//		Set<Map.Entry<String, Product>> aCopy =
//
//		Collections.copy(aCopy,products);
		for (LogicListenable l : allListeners) {
			l.modelSendProductsSet(products);
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
		Product p = store.getProductDetails(searchMe);
		if (p == null)
			fireOperationFailed("Error, product not in database", "bla");
		else
			fireGetProduct(p);

	}

	private void fireGetProduct(Product productFound) {
		for (LogicListenable l : allListeners) {
			l.modelSendProduct(productFound);
		}
	}
}
