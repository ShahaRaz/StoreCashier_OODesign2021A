package main.model.store;

import main.interfaces.saleEventListener;
import main.model.FileHandler;
import main.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedMap;

public class Cmnd_RemoveAllProducts implements Command {
    private SortedMap<String, Product> map_ref;

    private SortedMap<String, Product> copyOfMap;
    private int currentMapOrdering;
    private ArrayList<Product> soldProductsArr_ref; // reference
    private ArrayList<Product> copyOfProductsArr; // reference
    private FileHandler theFile; // reference
    private ArrayList<saleEventListener> subscribedCustomers_ref; // reference


    public Cmnd_RemoveAllProducts(SortedMap<String, Product> map_ref, int currentMapOrdering, ArrayList<Product> soldProductsArr_ref,
                                  FileHandler theFile, ArrayList<saleEventListener> subscribedCustomers) {
        this.map_ref = map_ref;
        this.currentMapOrdering = currentMapOrdering;
        this.soldProductsArr_ref = soldProductsArr_ref; // Arrays.copy.......
        this.theFile = theFile;
        this.subscribedCustomers_ref = subscribedCustomers;

    }

    @Override
    public void execute() {

        // handle map

        copyOfMap = Store.copyMap(map_ref,currentMapOrdering); // create a copy of the map
        map_ref.clear();
        // handle arraylist
        copyOfProductsArr = Store.copyArray(soldProductsArr_ref,soldProductsArr_ref.size());
        soldProductsArr_ref.clear();

        theFile.removeAllProducts();
    }

    @Override
    public void undo() {

        theFile.saveMapToFile(copyOfMap,currentMapOrdering); // file
//        theFile.readMapFromFile(map_ref,true); // map from file
        map_ref=copyOfMap;
        soldProductsArr_ref = copyOfProductsArr;
    }
}
