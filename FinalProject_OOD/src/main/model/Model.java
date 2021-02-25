package main.model;

/*
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */

import main.interfaces.saleEventListener;
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

	private void fireSelectingSortType() {
		for (LogicListenable l : allListeners) {
			l.modelAskToSelectSorteMethod();
		}
	}

	private void fireSendSaleListenersList(ArrayList<saleEventListener> listeners) {
		for (LogicListenable l : allListeners) {
			l.modelSendSaleListeners(listeners);
		}
	}

	public void addProduct(Product p) {
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

	public void removeProduct(Product p) {
		if (store.getProductDetails(p.getBarcode()) == null) {// product not in store.
			fireOperationFailed("product wasn't found");
		} else { // product is in our database
			store.removeProduct(p);
			/* send update to status */
			fireSendMessageToUser("The product " + p.getBarcode() + " removed!");
			/* Clear others fields */
			fireProductRemoved();
		}
	}
	
	public void removeAllProducts() {
		store.removeAllProducts();
		fireSendMessageToUser("The products removed!");
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
		Set<Map.Entry<String, Product>> products = store.getProductsSet();
		if (products.isEmpty()) {
			fireSendProductsArrToView(products); // here for testing, delete me later
			fireSelectingSortType();
		}
		fireSendProductsArrToView(products);
	}

	public void getProduct(String searchMe) {
		Product p = store.getProductDetails(searchMe);
		if (p == null) {
			fireOperationFailed("Product doesn't exist!");
		} else
			fireGetProduct(p);
	}

	public void sendSaleToCustomers() {
		ArrayList<saleEventListener> listeners  = store.getSubscribedCustomers();
		if (listeners.equals(null) || listeners.isEmpty()) {
			fireOperationFailed("No Subscribed Customers");
		} else { // we have customers who accepts promotions
			fireSendMessageToUser("The Sale has been sent!");
			System.err.println((TAG + ", sendSaleToCustomers: Got " + listeners.size() + " Promotion listeners"));
			fireSendSaleListenersList(listeners);
		}
	}



	public void saveMemento() {
		store.addMemento();
		fireSendMessageToUser("The State has been saved");
	}

	public void revertedLastState() {
		String isGood = store.getLastState();
		if (isGood.equals(Store.KEYS.MOMENTO_FAILED_REVERT)) {
			fireOperationFailed("No Actions to Reverted");
		} else {
			fireSendMessageToUser("The State has been reverted");
		}
	}

	public void viewSendSortingKey(int key) {
		/*
		 * 1 - Ascending Order 
		 * 2 - Descending Order
		 * 3 - Insertion Order
		 */
		System.err.println((TAG + ", viewSendSortingKey: key is: " + key));
		switch (key) {
		case Store.KEYS.ORDER_BY_ABC_UP:
			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_ABC_UP),Store.KEYS.ORDER_BY_ABC_UP);
			break;
		case Store.KEYS.ORDER_BY_ABC_DOWN:
			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_ABC_DOWN),Store.KEYS.ORDER_BY_ABC_DOWN);
			break;
		case Store.KEYS.ORDER_BY_INSERT_ORDER:
			store.setProductsMap(Store.getNewEmptyMap(Store.KEYS.ORDER_BY_INSERT_ORDER),Store.KEYS.ORDER_BY_INSERT_ORDER);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}
