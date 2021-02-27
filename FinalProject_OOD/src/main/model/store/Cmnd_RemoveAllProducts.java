package main.model.store;

import java.util.ArrayList;
import java.util.Map;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Product;

public class Cmnd_RemoveAllProducts implements Command {
    private Map<String, Product> map_ref;
    private Map<String, Product> copyOfMap;
    
    private int currentMapOrdering;
    
    private ArrayList<Product> soldProductsArr_ref; // reference
    private ArrayList<Product> copyOfProductsArr; // reference
    
    private FileHandler theFile; // reference
    
    private ArrayList<saleEventListener> subscribedCustomers_ref; // reference
    private ArrayList<saleEventListener> copyOf_subscribedCustomers;

    public Cmnd_RemoveAllProducts(Map<String, Product> map_ref, int currentMapOrdering, ArrayList<Product> soldProductsArr_ref,
                                  FileHandler theFile, ArrayList<saleEventListener> subscribedCustomers) {
        this.map_ref = map_ref;
        this.currentMapOrdering = currentMapOrdering;
        this.soldProductsArr_ref = soldProductsArr_ref; // Arrays.copy.......
        this.theFile = theFile;
        this.subscribedCustomers_ref = subscribedCustomers;
    }

    @Override
    public void execute() {
    	System.out.println(map_ref + "\nCMND REMOVEALL 35");
        // 1. create a copy of the map
        copyOfMap = Store.copyMap(map_ref,currentMapOrdering); // create a copy of the map
        // 2. clearing the map
        map_ref.clear();
        // 3. copying array of sold products
        copyOfProductsArr = Store.copyArray(soldProductsArr_ref,soldProductsArr_ref.size());
        // 4. clearing the sold products map
        soldProductsArr_ref.clear();
        // 5. remove all products from the file
        theFile.removeAllProducts();
        // 6. copy subscribedCustomers Array
        this.copyOf_subscribedCustomers = Store.copySaleListenersList(subscribedCustomers_ref);
        // 7. clearing all subscribedCustomers
        subscribedCustomers_ref.clear();

//        if(oldProductInMap.getCustomer().getIsAcceptingPromotions())
//            subscribedCustomers_ref.add(oldProductInMap.getCustomer());
    }

    @Override
    public void undo() {
    	System.out.println(copyOfMap + "\nCMND REMOVEALL 57");
        // 1. restore the map
        map_ref = copyOfMap;
        // 2. restore the SoldProducts
        soldProductsArr_ref = copyOfProductsArr;
        // 3. save map to the file
        theFile.saveMapToFile(copyOfMap,currentMapOrdering); // file
        // 4. restore subscribedCustomers array
        subscribedCustomers_ref = copyOf_subscribedCustomers;
    }
}
