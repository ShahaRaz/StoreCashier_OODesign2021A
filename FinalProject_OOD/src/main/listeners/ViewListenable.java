package main.listeners;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import main.model.Product;

public interface ViewListenable {
	public void viewAskToAddProduct(Product p);
	public void viewAskToRemoveProduct(Product addMe);
	public void ViewAskForProduct(Product removeMe);
	public void viewAskForListOfAllProducts();

}
