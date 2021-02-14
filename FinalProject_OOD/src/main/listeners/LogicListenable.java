package main.listeners;

import main.model.Product;

import java.util.ArrayList;
import java.util.Set;

public interface LogicListenable {
    void modelRejectedProduct(Product p, String str);
    void modelAddedProduct(Product p);
	void modelRemovedProduct(Product p);
    void modelSendProductsList(Set<Product> products);
	void notifyProductNotExist(Product p, String string);

    void modelFailedOperation(String errorMassage,String elaborate);

}
