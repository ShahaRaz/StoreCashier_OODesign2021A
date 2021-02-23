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
	private static final String TAG = "Model";

	public Model() {
		this.allListeners = new ArrayList<>();
		store = Store.getInstance();
		// TODO add here products from file
	}

	public void registerListener(LogicListenable l) {
		allListeners.add(l);
	}

	private void fireProductRemoved() {
		for (LogicListenable l : allListeners) {
			l.modelRemovedProduct();
		}
	}

	private void fireSendMessageToUser(String content) {
		for (LogicListenable l : allListeners) {
			l.modelSendsMessage(content);
		}
	}

//	public void fireProductNotGood(String str) {
//		for (LogicListenable l : allListeners) {
//			l.modelRejectedProduct(str);
//		}
//	}

	private void fireSendProductsArrToView(Set<Map.Entry<String, Product>> products) {
		for (LogicListenable l : allListeners) {
			l.modelSendProductsSet(products);
		}
	}

	public void fireOperationFailed(String elaborate) {
		for (LogicListenable l : allListeners) {
			l.modelFailedOperation(elaborate);
		}
	}

	private void fireGetProduct(Product productFound) {
		for (LogicListenable l : allListeners) {
			l.modelSendProduct(productFound);
		}
	}

	public void addProduct(Product p) {
		// TODO: Register customers as Subscribed (18/2).

		if (store.getProductDetails(p.getBarcode()) == null) { // product isn't yet in hashMap
			String problemsWithProduct = p.isValidProduct(this); // return the first error found
			if (problemsWithProduct.length() != 0) { // no errors found
				fireOperationFailed(problemsWithProduct);
				return;
			}
		}
		store.addNewProduct(p);
		fireSendMessageToUser("The product " + p.getBarcode() + " added!");
	}

	public void removedProduct(Product p) {
		if (store.getProductDetails(p.getBarcode()) == null) {// product not in store.
			fireOperationFailed("product wasn't found");
		} else { // product is in our database
			store.removeProduct(p);
			/* send update to status */
			fireSendMessageToUser("The product " + p.getBarcode() + " removed!");
			/* Clear others fields */
			fireProductRemoved();
		}
		// TODO: Return fireProductRemoved
		// firing a return statement from within the store.
	}

	public void undoLastAction() {
		String isGood = store.undoLastAction();
		if (isGood.equalsIgnoreCase("UNDO FAILED")) {
			fireOperationFailed("No Actions to undo");
		} else {
			fireSendMessageToUser("Undo completed!");
		}
	}

	public void sendAllProductsToView() {
//		fireSendProductsArrToView(store.getProductsSet());
		Set<Map.Entry<String, Product>> products = store.getProductsSet();
		if (products.isEmpty()) {
			fireSendProductsArrToView(products); // here for testing, delete me later
			fireSelectingSortType();
			// TODO remove the next line, and set the map based on the returned value from the user.
//			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_ABC_UP),Store.KEYS.ORDER_BY_ABC_UP);
		}
		fireSendProductsArrToView(products);
	}

	private void fireSelectingSortType() {
		for (LogicListenable l : allListeners) {
			l.modelAskToSelectSorteMethod();
		}
	}

	public void getProduct(String searchMe) {
		Product p = store.getProductDetails(searchMe);
		if (p == null) {
			fireOperationFailed("Product doesn't exist!");
		} else
			fireGetProduct(p);

	}

	public void sendSaleToCustomers() {
		if (store.getSubscribedCustomers().equals(null) || store.getSubscribedCustomers().isEmpty()) {
			fireOperationFailed("No Subscribed Customers");
		} else {
			store.notifyAllCustomers();
			fireSendMessageToUser("The Sale has been sent!");
		}
	}

	public void saveMemento() {
		store.addMemento();
		fireSendMessageToUser("The State has been saved");
	}

	public void revertedLastState() {
		String isGood = store.getLastState();
		if (isGood.equalsIgnoreCase("Reverted state FAILED")) {
			fireOperationFailed("No Actions to Reverted");
		} else {
			fireSendMessageToUser("The State has been reverted");
		}
	}

	public void viewSendSortingKey(int key) {
		/*
		 * TODO initial the sorting key. (18/2) 1 - Ascending Order 2 - Descending Order
		 * 3 - Insertion Order
		 *
		 */

		switch (key) {
		case Store.KEYS.ORDER_BY_ABC_UP:
			System.err.println((TAG + ", viewSendSortingKey: Store.KEYS.ORDER_BY_ABC_UP"));
			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_ABC_UP),Store.KEYS.ORDER_BY_ABC_UP);

			break;
		case Store.KEYS.ORDER_BY_ABC_DOWN:
			System.err.println((TAG + ", viewSendSortingKey: Store.KEYS.ORDER_BY_ABC_DOWN"));
			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_ABC_DOWN),Store.KEYS.ORDER_BY_ABC_DOWN);

			break;
		case Store.KEYS.ORDER_BY_INSERT_ORDER:
			System.err.println((TAG + ", viewSendSortingKey: Store.ORDER_BY_INSERT_ORDER"));
			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_INSERT_ORDER),Store.KEYS.ORDER_BY_INSERT_ORDER);

			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}
