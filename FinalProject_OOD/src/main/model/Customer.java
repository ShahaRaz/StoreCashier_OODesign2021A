package main.model;

public class Customer {
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
	
	
}
