package main.listeners;

import main.model.Product;

public interface ViewListenable {
    public void viewAskToAddProduct(Product p);

	public void viewAskToRemoveProduct(Product addMe);

}
