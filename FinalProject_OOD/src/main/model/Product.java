package main.model;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
	private long timeAdded;

	private SimpleStringProperty description;
	private SimpleStringProperty barcode; // MAKAT( Cytological Number )

	private SimpleIntegerProperty costToStore;
	private SimpleIntegerProperty priceSold;
	private Customer customer;

	public Product(String description, int costToStore, int priceSold, Customer customer, String pID) {
		this.timeAdded = System.currentTimeMillis();
		this.description = new SimpleStringProperty(description);
		this.costToStore = new SimpleIntegerProperty(costToStore);
		this.priceSold = new SimpleIntegerProperty(priceSold);
		this.customer = customer;
		this.barcode = new SimpleStringProperty(pID);
	}

	public Product(String pID) {
		this.barcode = new SimpleStringProperty(pID);
		this.timeAdded = System.currentTimeMillis();
		this.description = new SimpleStringProperty("-1");
		this.costToStore = new SimpleIntegerProperty(-1);
		this.priceSold = new SimpleIntegerProperty(0);
		this.customer = null;
	}

	public boolean isValidProduct(Model model) {
		if (this.costToStore.get() < 0 || this.priceSold.get() < 0) {
			model.fireProductNotGood(this, "Price can't be negative");
			return false;
		}
		if (this.description.get().equals("")) {
			model.fireProductNotGood(this, "Product's name can't be empty");
			return false;
		}
		return true;
		// TODO: Check exist customer
	}

	public long getTimeMilis() {
		return this.timeAdded;
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

	public void setDescription(String description) {
		this.description.set(description);
	}

	public int getCostToStore() {
		return costToStore.get();
	}

	public void setCostToStore(int costToStore) {
		this.costToStore.set(costToStore);
	}

	public int getPriceSold() {
		return priceSold.get();
	}

	public void setPriceSold(int priceSold) {
		this.priceSold.set(priceSold);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getBarcode() {
		return barcode.get();
	}

	public void setBarcode(String barcode) {
		this.barcode.set(barcode);
	}

}
