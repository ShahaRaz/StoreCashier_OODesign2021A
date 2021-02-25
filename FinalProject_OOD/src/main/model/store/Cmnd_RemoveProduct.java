package main.model.store;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Product;

import java.util.ArrayList;
import java.util.SortedMap;

public class Cmnd_RemoveProduct implements Command {

    private ArrayList<Product> soldProductsArr_ref; // reference
    private Product product;
    private SortedMap<String, Product> map_ref;
    private FileHandler theFile;
    private int lastMapOrdering;
    private ArrayList<saleEventListener> subscribedCustomers_ref; // reference

    public Cmnd_RemoveProduct(Product product, ArrayList<Product> soldProductsArr_ref,
                              SortedMap<String, Product> map_ref, FileHandler theFile, int currentMapOrdering, ArrayList<saleEventListener> subscribedCustomers) {
        this.product = map_ref.get(product.getBarcode());
        this.map_ref = map_ref; // reference to the main map
        this.soldProductsArr_ref = soldProductsArr_ref; // A Reference!
        this.theFile = theFile; // A Reference!
        this.lastMapOrdering = currentMapOrdering;
        this.subscribedCustomers_ref = subscribedCustomers;
    }


    @Override
    public void execute() {
        boolean isLastProduct = (map_ref.size()==1 ? true : false); // if it's the last product, we want to ask the user for new map ordering
        theFile.removeProductFromFile(product);
        if (!isLastProduct) // not the last product
            theFile.readMapFromFile(map_ref,true); // it's inefficient, but that's what we were asked for.
        map_ref.remove(product.getBarcode());


        soldProductsArr_ref.remove(product); // not listed in the system requirements, but we implement this for possible future use

        // saleListeners
        if (subscribedCustomers_ref.contains(product.getCustomer()))
            subscribedCustomers_ref.remove(product.getCustomer());

    }

    /**
     * add the product back to the map
     * update the file
     */
    @Override
    public void undo() {
        map_ref.put(product.getBarcode(),product);
        theFile.saveMapToFile(map_ref,this.lastMapOrdering);

        soldProductsArr_ref.add(product);// not listed in the system requirements, but we implement this for possible future use

        // saleListeners
        if(product.getCustomer().getIsAcceptingPromotions())
            subscribedCustomers_ref.add(product.getCustomer());

    }
}
