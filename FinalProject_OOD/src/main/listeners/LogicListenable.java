package main.listeners;

import main.model.Product;

import java.util.ArrayList;

public interface LogicListenable {
    void modelRejectedProduct(Product p, String str);
    void modelAddedProduct(Product p);
<<<<<<< HEAD
	void modelRemovedProduct(Product p);
    
=======

    void modelSendProductsList(ArrayList<Product> products);

>>>>>>> refs/remotes/origin/LogicBranch
}
