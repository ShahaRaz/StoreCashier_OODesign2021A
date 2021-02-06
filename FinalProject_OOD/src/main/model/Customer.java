package main.model;

import main.interfaces.saleEventListener;

public class Customer implements saleEventListener{
	private String name;
	private String mobileNumber;
	private Boolean isAcceptingPromotions;

	public Customer(String name, String mobileNumber, Boolean isAcceptingPromotions) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.isAcceptingPromotions = isAcceptingPromotions;
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
	public void onSaleEvent(Store store) {
		// TODO: For now, create a console message, need to decide if we want to display with a new window or as a status reference.
		System.out.println(name + "! There is sale on '"+store.getStoreName() + "'");
	}
	
	
}
