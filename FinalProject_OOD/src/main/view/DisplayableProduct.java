package main.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import main.model.Customer;
import main.model.Product;

public class DisplayableProduct {
	private long timeAdded;

	private SimpleStringProperty description;
	private SimpleStringProperty barcode; // MAKAT( Cytological Number )

	private SimpleIntegerProperty costToStore;
	private SimpleIntegerProperty priceSold;
	private Customer customer;

	public DisplayableProduct(Product p) {
		this.timeAdded = p.getTimeAdded();
		this.description = new SimpleStringProperty(p.getDescription());
		;
		this.barcode = new SimpleStringProperty(p.getBarcode());
		this.costToStore = new SimpleIntegerProperty(p.getCostToStore());
		this.priceSold = new SimpleIntegerProperty(p.getPriceSold());
		this.customer = p.getCustomer();
	}

	public long getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(long timeAdded) {
		this.timeAdded = timeAdded;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(SimpleStringProperty description) {
		this.description = description;
	}

	public String getBarcode() {
		return barcode.get();
	}

	public void setBarcode(SimpleStringProperty barcode) {
		this.barcode = barcode;
	}

	public int getCostToStore() {
		return costToStore.get();
	}

	public void setCostToStore(SimpleIntegerProperty costToStore) {
		this.costToStore = costToStore;
	}

	public int getPriceSold() {
		return priceSold.get();
	}

	public void setPriceSold(SimpleIntegerProperty priceSold) {
		this.priceSold = priceSold;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
