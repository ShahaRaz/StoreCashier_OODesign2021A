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
        this.timeAdded = timeAdded;
        this.description =  new SimpleStringProperty(p.getDescription());;
        this.barcode = new SimpleStringProperty(p.getBarcode());
        this.costToStore = new SimpleIntegerProperty(p.getCostToStore());
        this.priceSold = new SimpleIntegerProperty(p.getPriceSold());
        this.customer = customer;
    }
//    private long timeAdded;
//
//    private SimpleStringProperty description;
//    private SimpleStringProperty barcode; // MAKAT( Cytological Number )
//
//    private SimpleIntegerProperty costToStore;
//    private SimpleIntegerProperty priceSold;
//    private Customer customer;

}
