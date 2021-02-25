package main.model.store;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Product;

import java.util.ArrayList;
import java.util.SortedMap;

public class Cmnd_AddProduct implements Command{
    private static final String TAG = "Cmnd_AddProduct";
    private ArrayList<saleEventListener> subscribedCustomers_ref; // reference
    private Product oldProductInMap=null; // save it in-case we'll overwrite old details of the product in the map
    boolean wasProductInMapB4thisCmnd=false;
    private ArrayList<Product> soldProductsArr_ref; // not listed in the system requirements, but we implement this for possible future use
    private Product product;
    private SortedMap<String, Product> map_ref;
    private FileHandler theFile;
    private int currentMapOrdering;


    public Cmnd_AddProduct(Product p, SortedMap<String, Product> productsMap, ArrayList<Product> soldProductsArr,
                           FileHandler theFile, int currentMapOrdering, ArrayList<saleEventListener> subscribedCustomers) {
        this.product = p;
        this.map_ref = productsMap; // reference to the main map
        this.soldProductsArr_ref = soldProductsArr; // A Reference!
        this.theFile = theFile; // A Reference!
        this.currentMapOrdering = currentMapOrdering;
        this.subscribedCustomers_ref = subscribedCustomers;
    }

    @Override
    public void execute() {
        if (map_ref==null)
            System.err.println((TAG + ", execute: mapref==null"));
        if(map_ref.containsKey(product.getBarcode())) { // if product is already in map
            wasProductInMapB4thisCmnd = true;
            oldProductInMap = map_ref.get(product.getBarcode());

            /**
             * handle subscribed costumer
             * removing the OLD customer from
             */
            if (subscribedCustomers_ref.contains(map_ref.get(product.getBarcode()).getCustomer()))
                subscribedCustomers_ref.remove(map_ref.get(product.getBarcode()).getCustomer());
        }
        else
            wasProductInMapB4thisCmnd = false;


        map_ref.put(product.getBarcode(),product);
        theFile.saveMapToFile(this.map_ref, this.currentMapOrdering);
//        theFile.addProductToFile(product);

        soldProductsArr_ref.add(product);// not listed in the system requirements, but we implement this for possible future use

        if(product.getCustomer().getIsAcceptingPromotions())
            subscribedCustomers_ref.add(product.getCustomer());

    }

    /**
     * remove the product from the map (from the file)
     * load the new map from the file
     */
    @Override
    public void undo() {
        if (subscribedCustomers_ref.contains(product.getCustomer()))
            subscribedCustomers_ref.remove(product.getCustomer());
        /// TODO: figure out a better, more efficient way to replace a product in the file
        // since deleting and than adding a product is inefficient & will lead to undesired output (the product will be last in the file)
        // and deleting directly from the map is not allowed (i guess), than i need a way to replace the last product returned from the iterator
//        theFile.replaceProductWithOtherVersion(product);
        if(wasProductInMapB4thisCmnd) {
            map_ref.put(product.getBarcode(),oldProductInMap);
            theFile.saveMapToFile(this.map_ref, this.currentMapOrdering);

            if(oldProductInMap.getCustomer().getIsAcceptingPromotions())
                subscribedCustomers_ref.add(oldProductInMap.getCustomer());
//            map_ref.put(oldProductInMap.getBarcode(),oldProductInMap); // as this command should overwrite the old one
//            theFile.removeProductFromFile(product);
//            theFile.addProductToFile(oldProductInMap);
        }
        else{
            theFile.removeProductFromFile(product);
        }

        theFile.readMapFromFile(map_ref,true);

        soldProductsArr_ref.remove(product);// not listed in the system requirements, but we implement this for possible future use

    }
}
