package main.listeners;

import main.model.Product;

import java.util.Map;
import java.util.Set;

public interface LogicListenable {
	void modelRejectedProduct(Product p, String str);

	void modelAddedProduct(Product p);

	void modelRemovedProduct(Product p);

	void modelSendProductsList(Set<Map.Entry<String, Product>> products); /////

	void notifyProductNotExist(Product p, String string);

	void modelFailedOperation(String errorMassage, String elaborate);

	void modelSendProduct(Product productDetails);

	void modelSendsMessage(String headline, String Content);

	void viewAskForListOfAllProducts();
}
