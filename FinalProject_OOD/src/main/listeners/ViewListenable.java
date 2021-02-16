package main.listeners;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import main.model.Product;

public interface ViewListenable {
	public void viewAskToAddProduct(Product p);

	public void viewAskToRemoveProduct(Product addMe);

	public void viewAskForProduct(String removeMe);

	public void viewAskForListOfAllProducts();

	public void viewAskForUndo();

}
