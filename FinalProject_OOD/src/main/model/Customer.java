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
}
