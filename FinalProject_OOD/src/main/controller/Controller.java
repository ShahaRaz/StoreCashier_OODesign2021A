package main.controller;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 * */

import main.listeners.LogicListenable;
import main.listeners.ViewListenable;
import main.model.Model;
import main.model.Product;
import main.view.View;
import java.util.Map;
import java.util.Set;

public class Controller implements ViewListenable, LogicListenable {
	private Model theModel;
	private View theView;

	public Controller(Model m, View v) {
		theModel = m;
		theView = v;

		theModel.registerListener(this);
		theView.registerListener(this); // throws

		// Send list to view after registerListener.
		theView.fireListOfProducts();
	}

	// View wants to add product.
	@Override
	public void viewAskToAddProduct(Product p) {
		theModel.addProduct(p);
	}

	// View wants to remove product.
	@Override
	public void viewAskToRemoveProduct(Product p) {
		theModel.removeProduct(p);
	}

	// Model return a massage: The produce removed.
	@Override
	public void modelRemovedProduct() {
		theView.clearFieldsAfterRemoved();
	}

	@Override
	public void modelSendProductsSet(Set<Map.Entry<String, Product>> products) {
		theView.nofityProductsArrived(products);
	}

	@Override
	public void modelSendsMessage(String content) { 
		/**
		 * send message to the user
		 *
		 * use cases: announce successful undo (headline will contain the word "Undo")
		 * check by: if(headline.containsIgnoreCase("Undo") popup(headline,message)
		 * fireAskForProductsList
		 *
		 */
		theView.notifyNewMessageFromModel(content);

	}

	@Override
	public void viewAskForProduct(String searchMe) {
		theModel.getProduct(searchMe);
	}

	@Override
	public void viewAskForListOfAllProducts() {
		theModel.sendAllProductsToView();
	}

	@Override
	public void viewAskForUndo() {
		theModel.undoLastAction();
	}

	@Override
	public void modelSendProduct(Product productDetails) {
		theView.getProductFromModel(productDetails);
	}

	@Override
	public void viewAskToSendSale() {
		theModel.sendSaleToCustomers();
	}

	@Override
	public void viewAskToSave() {
		theModel.saveMemento();
	}

	@Override
	public void viewAskToReverse() {
		theModel.revertedLastState();
	}

	@Override
	public void modelFailedOperation(String elaborate) {//String errorMassage, 
		/**
		 * notify user that operation faild Operations like:
		 *
		 * UNDO or any general thing..
		 *
		 *
		 *
		 *
		 * in elaborate: extra data (for internal use) my hold tokens or other things
		 * that needs to be passed from model to view
		 *
		 *
		 */
		// failed
		theView.notifyFailedOperation(elaborate);
	}

	@Override
	public void modelAskToSelectSorteMethod() {
		theView.getSorteWindow();
	}
	
	@Override
	public void viewSendSortingMethod(int key) {
		theModel.viewSendSortingKey(key);
	}

	@Override
	public void viewAskToRemoveAllProducts() {
		theModel.removeAllProducts();
	}

}
