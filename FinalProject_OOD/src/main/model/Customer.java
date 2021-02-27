package main.model;

import main.interfaces.saleEventListener;
import main.model.store.Store;

public class Customer implements saleEventListener {
	private String name;
	private String mobileNumber;
	private Boolean isAcceptingPromotions;

	public Customer(String name) {
		this.name = name;
		this.mobileNumber = "0";
		this.isAcceptingPromotions = false;
	}

	public Customer(String name, String mobileNumber, Boolean isAcceptingPromotions) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.isAcceptingPromotions = isAcceptingPromotions;
	}

	// Copy Constructor
	public Customer(Customer other) {
		this.name = new String(other.getName());
		this.mobileNumber = new String(other.getMobileNumber());
		this.isAcceptingPromotions = other.isAcceptingPromotions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Boolean getIsAcceptingPromotions() {
		return isAcceptingPromotions;
	}

	public void setIsAcceptingPromotions(Boolean isAcceptingPromotions) {
		this.isAcceptingPromotions = isAcceptingPromotions;
	}

	// Implement the Observable interface.
	@Override
	public String announceGotPromotion() {
		// For now, create a console message, need to decide if we want to display with
		// a new window or as a status reference.
		return (name + ": i got the sale message from '" + Store.KEYS.STORE_NAME + "'");
	}

}
