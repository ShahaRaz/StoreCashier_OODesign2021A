package main.model;

public class Product {
    private String description;
    private int costToStore;
    private int priceSold;
    private Customer customer;
    private String pID; // MAKAT( Cytological Number )


    public Product(String description, int costToStore, int priceSold, Customer customer, String pID) {
        this.description = description;
        this.costToStore = costToStore;
        this.priceSold = priceSold;
        this.customer = customer;
        this.pID = pID;
    }

    public Product(String pID) {
        this.pID = pID;
        this.description = "-1";
        this.costToStore = -1;
        this.priceSold = -1;
        this.customer = null;
    }

    public String getId() {
        return this.pID;
    }

    public boolean isValidProduct() {
        if (        this.description.equals("-1") ||
                    this.costToStore == -1 ||
                    this.priceSold == -1 ||
                    this.customer == null){
            return false;
        }
        return true;
    }
}
