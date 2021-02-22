package main.listeners;

import main.model.Product;

import java.util.Map;
import java.util.Set;

public interface LogicListenable {

	void modelRemovedProduct();

	void modelSendProductsSet(Set<Map.Entry<String, Product>> products); 

	void modelFailedOperation(String elaborate);//String errorMassage, 

	void modelSendProduct(Product productDetails);

	void modelSendsMessage(String Content);//String headline, 

	void viewAskForListOfAllProducts();

	void modelAskToSelectSorteMethod();
}
