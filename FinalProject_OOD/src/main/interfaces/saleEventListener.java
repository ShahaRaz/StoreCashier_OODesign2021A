package main.interfaces;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

import main.model.store.Store;

public interface saleEventListener {
	void onSaleEvent(Store store);
}
