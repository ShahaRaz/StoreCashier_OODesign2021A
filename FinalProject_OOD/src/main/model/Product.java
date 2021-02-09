package main.model;

public class Product {
	private long timeAdded;
	private String description;
	private int costToStore;
	private int priceSold;
	private Customer customer;
	private String pID; // MAKAT( Cytological Number )

	public Product(String description, int costToStore, int priceSold, Customer customer, String pID) {
		this.timeAdded = System.currentTimeMillis();
		this.description = description;
		this.costToStore = costToStore;
		this.priceSold = priceSold;
		this.customer = customer;
		this.pID = pID;
	}

	public Product(String pID) {
		this.timeAdded = System.currentTimeMillis();
		this.pID = pID;
		this.description = "-1";
		this.costToStore = -1;
		this.priceSold = 0;
		this.customer = null;
	}

	public String getId() {
		return this.pID;
	}

	public boolean isValidProduct(Model model) {
		if (this.costToStore < 0 || this.priceSold < 0) {
			model.fireProductNotGood(this, "Price can't be negative");
			return false;
		}
		if (this.description.equals("")) {
			model.fireProductNotGood(this, "Product's name can't be empty");
			return false;
		}
		return true;
		// TODO: Check exist customer
	}

	public long getTimeMilis() {
		return this.timeAdded;
	}
}
