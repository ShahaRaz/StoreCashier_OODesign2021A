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
		store = Store.getInstance();
		// TODO add here products from file
	}

	public void registerListener(LogicListenable l) {
		allListeners.add(l);
	}

	private void fireProductRemoved(Product p) {
		for (LogicListenable l : allListeners) {
			l.modelRemovedProduct(p);
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

	private void fireGetProduct(Product productFound) {
		for (LogicListenable l : allListeners) {
			l.modelSendProduct(productFound);
		}
	}

	public void addProduct(Product p) {
		//TODO: Register customers as Subscribed (18/2).
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

	public void undoLastAction() {
		String isGood = store.undoLastAction();
		if (isGood.equalsIgnoreCase("UNDO FAILED")) {
			fireOperationFailed("UNDO FAILED", "No Actions to undo");
		} else {
			fireSendMessageToUser("Undo completed!", "product list has been updated");
		}
	}

	public void sendAllProductsToView() {
		/// TODO add here asking for ordering by user,
		// injecting for now..
		if(store.getProductsSet().isEmpty())
			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_ABC_UP));
		fireSendProductsArrToView(store.getProductsSet());
	}

	public void getProduct(String searchMe) {
		Product p = store.getProductDetails(searchMe);
		if (p == null)
			fireOperationFailed("Error, product not in database", "bla");
		else
			fireGetProduct(p);

	}

	public void sendSaleToCustomers() {
		if (store.getSubscribedCustomers().isEmpty())
			fireOperationFailed("Sale FAILED", "No Subscribed Customers");
		else {
			store.notifyAllCustomers();
			fireSendMessageToUser("Sale completed!", "The Sale has been sent!");
		}
	}

	public void saveMemento() {
		store.addMemento();
		fireSendMessageToUser("Saved completed!", "The State has been saved");
	}
	
	public void revertedLastState() {
		String isGood = store.getLastState();
		if (isGood.equalsIgnoreCase("Reverted state FAILED")){
			fireOperationFailed("Reverted FAILED", "No Actions to Reverted");
		} else {
			fireSendMessageToUser("Reverted completed!", "The State has been reverted");
		}
	}
}
