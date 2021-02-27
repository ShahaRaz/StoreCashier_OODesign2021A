package main.model.store;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Product;

import java.util.ArrayList;
import java.util.Map;

public class Cmnd_AddProduct implements Command {
	private static final String TAG = "Cmnd_AddProduct";
	private ArrayList<saleEventListener> subscribedCustomers_ref; // reference
	private Product oldProductInMap = null; // save it in-case we'll overwrite old details of the product in the map
	boolean wasProductInMapB4thisCmnd = false;
	private ArrayList<Product> soldProductsArr_ref; // not listed in the system requirements, but we implement this for
													// possible future use
	private Product product;
	private Map<String, Product> map_ref;
	private FileHandler theFile;
	private int currentMapOrdering;

	public Cmnd_AddProduct(Product p, Map<String, Product> productsMap, ArrayList<Product> soldProductsArr,
			FileHandler theFile, int currentMapOrdering, ArrayList<saleEventListener> subscribedCustomers) {
		this.product = p;
		this.map_ref = productsMap; // reference to the main map
		this.soldProductsArr_ref = soldProductsArr; // A Reference!
		this.theFile = theFile; // A Reference!
		this.currentMapOrdering = currentMapOrdering;
		this.subscribedCustomers_ref = subscribedCustomers;
		this.oldProductInMap = null;
	}

	@Override
	public void execute() {
		// 1. if product is already in map
		if (map_ref.containsKey(product.getBarcode())) {
			// 1. notify that there was
			wasProductInMapB4thisCmnd = true;
			// 2. get the old product from the map
			oldProductInMap = map_ref.get(product.getBarcode());

			/**
			 * handle subscribed costumer removing the OLD customer from
			 */
			if (subscribedCustomers_ref.contains(map_ref.get(product.getBarcode()).getCustomer()))
				subscribedCustomers_ref.remove(map_ref.get(product.getBarcode()).getCustomer());
		} else
			wasProductInMapB4thisCmnd = false;

		// 2. adding the new product to the map
		map_ref.put(product.getBarcode(), product);
		// 3. save the map into the file.
		theFile.saveMapToFile(this.map_ref, this.currentMapOrdering);

		// 4. add Customer to promotions list..
		if (product.getCustomer().getIsAcceptingPromotions())
			subscribedCustomers_ref.add(product.getCustomer());

		soldProductsArr_ref.add(product);// not listed in the system requirements, but we implement this for possible
											// future use

	}

	/**
	 * remove the product from the map (from the file) load the new map from the
	 * file
	 */
	@Override
	public void undo() {
		if (subscribedCustomers_ref.contains(product.getCustomer()))
			subscribedCustomers_ref.remove(product.getCustomer());

		if (wasProductInMapB4thisCmnd) { // we are actually adding the oldProduct
			// 1. adding the old product to the map ( WILL OVER-Write the "new"product
			map_ref.put(product.getBarcode(), oldProductInMap);
			// 2. sav the map to the file
			theFile.saveMapToFile(this.map_ref, this.currentMapOrdering);
			// 3. add costumer to subscribedCustomers
			if (oldProductInMap.getCustomer().getIsAcceptingPromotions())
				subscribedCustomers_ref.add(oldProductInMap.getCustomer());
			// 4. write the map into the file
			theFile.saveMapToFile(this.map_ref, this.currentMapOrdering); // update the map
		} else { // product was not overwritten in execute, simply remove product
					// 1. remove product from file.
			theFile.removeProductFromFile(product);
			// 2. read the file into map
			theFile.readMapFromFile(map_ref, true);
//            //3. read subscribedCustomers from file
//            subscribedCustomers_ref = Store.getListenersFromMap(map_ref);
		}

		soldProductsArr_ref.remove(product);// not listed in the system requirements, but we implement this for possible
											// future use
	}
}
