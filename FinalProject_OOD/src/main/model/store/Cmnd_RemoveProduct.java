package main.model.store;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Product;

import java.util.ArrayList;
import java.util.Map;

public class Cmnd_RemoveProduct implements Command {

    private ArrayList<Product> soldProductsArr_ref; // reference
    private Product product;
    private Map<String, Product> map_ref;
    private FileHandler theFile;
    private int lastMapOrdering;
    private ArrayList<saleEventListener> subscribedCustomers_ref; // reference

    public Cmnd_RemoveProduct(Product product, ArrayList<Product> soldProductsArr_ref,
                              Map<String, Product> map_ref, FileHandler theFile, int currentMapOrdering, ArrayList<saleEventListener> subscribedCustomers) {
        this.product = map_ref.get(product.getBarcode());
        this.map_ref = map_ref; // reference to the main map
        this.soldProductsArr_ref = soldProductsArr_ref; // A Reference!
        this.theFile = theFile; // A Reference!
        this.lastMapOrdering = currentMapOrdering;
        this.subscribedCustomers_ref = subscribedCustomers;
    }


    @Override
    public void execute() {
        // 1. checking if it's the last product
        boolean isLastProduct = (map_ref.size()==1 ? true : false); // if it's the last product, we want to ask the user for new map ordering
        // 2. removing the product from the file
        theFile.removeProductFromFile(product);
        // 3. if it is not the last product, we want to read the updated map from the file.
        if (!isLastProduct) { // not the last product
            theFile.readMapFromFile(map_ref, true); // it's inefficient, but that's what we were asked for.
//        // 4. read subscribedCustomers from file
//            subscribedCustomers_ref = Store.getListenersFromMap(map_ref);

        }
        else{ // it is the last product, make sure that the map is empty.
            map_ref.clear();
        }


        // 4. add Customer to promotions list.
        if (subscribedCustomers_ref.contains(product.getCustomer()))
            subscribedCustomers_ref.remove(product.getCustomer());
        soldProductsArr_ref.remove(product); // not listed in the system requirements, but we implement this for possible future use


    }

    /**
     * add the product back to the map
     * update the file
     */
    @Override
    public void undo() {
        // 1. add the product back to the map
        map_ref.put(product.getBarcode(),product);
        // 2. save the map to the file
        theFile.saveMapToFile(map_ref,this.lastMapOrdering);
        // 3. add Customer to promotions list.
        if(product.getCustomer().getIsAcceptingPromotions())
            subscribedCustomers_ref.add(product.getCustomer());

        soldProductsArr_ref.add(product);// not listed in the system requirements, but we implement this for possible future use


    }


//    // 1. if product is already in map
//        if(map_ref.containsKey(product.getBarcode())) {
//        // 1. notify that there was
//        wasProductInMapB4thisCmnd = true;
//        // 2. get the old product from the map
//        oldProductInMap = map_ref.get(product.getBarcode());
//
//        /**
//         * handle subscribed costumer
//         * removing the OLD customer from
//         */
//        if (subscribedCustomers_ref.contains(map_ref.get(product.getBarcode()).getCustomer()))
//            subscribedCustomers_ref.remove(map_ref.get(product.getBarcode()).getCustomer());
//    }
//        else
//    wasProductInMapB4thisCmnd = false;
//
//    // 2. adding the new product to the map
//        map_ref.put(product.getBarcode(),product);
//    // 3. save the map into the file.
//        theFile.saveMapToFile(this.map_ref, this.currentMapOrdering);
//
//    // 4. add Customer to promotions list..
//        if(product.getCustomer().getIsAcceptingPromotions())
//            subscribedCustomers_ref.add(product.getCustomer());
//
//        soldProductsArr_ref.add(product);// not listed in the system requirements, but we implement this for possible future use

}
