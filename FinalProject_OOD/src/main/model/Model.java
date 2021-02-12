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
import main.model.store.Store;

import java.util.ArrayList;

public class Model {
	private Store store;
	private ArrayList<LogicListenable> allListeners;

	public Model() {
		this.allListeners = new ArrayList<>();
		store = Store.getInstance(this);
	}

	public void registerListener(LogicListenable l) {
		allListeners.add(l);
	}

	public void addProduct(Product p) {
		if (store.getProductDetails(p.getBarcode()) == null) { // product isn't yet in hashMap
			String problemsWithProduct = p.isValidProduct(this); //return the first error found
			if (problemsWithProduct.length()==0) { // no errors found
				store.addNewProduct(p);
				fireProductAdded(p);
			} else {
				fireProductNotGood(p, problemsWithProduct);
			}
		}
		else { //note! (got here if) product is already in map already, which means it's valid
			store.addNewProduct(p);
			fireProductAdded(p);
		}
	}

	public void removedProduct(Product p) {
		if (store.getProductDetails(p.getBarcode())==null) // product not in store.
		store.removeProduct(p);
		// firing a return statement from within the store.
	}

	public void undoLastAction(){
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

	private void fireSendProductsArrToView(ArrayList<Product> products){
		for (LogicListenable l : allListeners) {
			l.modelSendProductsList(products);
		}
	}


	public void fireOperationFailed(String errorMassage, String elaborate) {
		for (LogicListenable l : allListeners) {
			l.modelFailedOperation(errorMassage,elaborate);
		}
	}
}
