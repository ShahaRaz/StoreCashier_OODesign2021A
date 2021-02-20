package main.model;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product implements Comparable {
	private long timeAdded;

	private String description;
	private String barcode; // MAKAT( Cytological Number )

	private int costToStore;
	private int priceSold;
	private Customer customer;

	public Product(long timeAdded, String description, String barcode, int costToStore, int priceSold,
			Customer customer) {
		// CALL ME ONLY WHEN INSERT FROM FILE (time added already determined)

		this(description, costToStore, priceSold, customer, barcode);
		this.timeAdded = timeAdded;
	}

	public Product(String description, int costToStore, int priceSold, Customer customer, String pID) {
		this.timeAdded = System.currentTimeMillis();
		this.description = description;
		this.costToStore = costToStore;
		this.priceSold = priceSold;
		this.customer = customer;
		this.barcode = pID;
	}

	public Product(String pID) {
		this.barcode = pID;
		this.timeAdded = System.currentTimeMillis();
		this.description = "-1";
		this.costToStore = -1;
		this.priceSold = 0;
		this.customer = null;
	}

//	public Product(Product other) {
//		this.barcode = new SimpleStringProperty(String.copyValueOf(other.getBarcode().toCharArray()));
//		this.timeAdded = System.currentTimeMillis(
//		this.description = new SimpleStringProperty;
//		this.costToStore =
//		this.priceSold =
//		this.customer =
//	}

	public String isValidProduct(Model model) {
		if (this.costToStore < 0 || this.priceSold < 0) {
			return "Price can't be negative";
		}
		// TODO: this.barcode.get().equals("") is unnecessary, already checked in
		// AddProductView, line 306
		// when barcode null, throws Exception.
		if (this.barcode == null || this.barcode.equals("")) {
			return "Product must have Valid Barcode";
		}
		if (this.description.equals("")) {
			return "Product's name can't be empty";
		}

		return ""; // product is ok, has no problems
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
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCostToStore() {
		return costToStore;
	}

	public void setCostToStore(int costToStore) {
		this.costToStore = costToStore;
	}

	public int getPriceSold() {
		return priceSold;
	}

	public void setPriceSold(int priceSold) {
		this.priceSold = priceSold;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "Product [timeAdded=" + timeAdded + ", description=" + description + ", barcode=" + barcode
				+ ", costToStore=" + costToStore + ", priceSold=" + priceSold + ", customer=" + customer + "]";
	}

	@Override
	public int compareTo(Object o) {
		Product other = (Product) o;
		return (int) this.getTimeMilis() - (int) other.getTimeMilis();
	}
}
