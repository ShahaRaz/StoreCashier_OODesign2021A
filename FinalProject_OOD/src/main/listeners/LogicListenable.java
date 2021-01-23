package main.listeners;

import main.model.Product;

public interface LogicListenable {
    void modelRejectedProduct(Product p, String str);

    void modelAddedProduct(Product p);
}
